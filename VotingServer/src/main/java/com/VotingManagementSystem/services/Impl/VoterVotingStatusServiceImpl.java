package com.VotingManagementSystem.services.Impl;

import com.VotingManagementSystem.models.User;
import com.VotingManagementSystem.models.election.Election;
import com.VotingManagementSystem.models.election.VoterVotingStatus;
import com.VotingManagementSystem.repositories.ElectionRepository;
import com.VotingManagementSystem.repositories.VoterVotingStatusRepository;
import com.VotingManagementSystem.services.VoterVotingStatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VoterVotingStatusServiceImpl implements VoterVotingStatusService {

    @Autowired
    private VoterVotingStatusRepository voterVotingStatusRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Override
    public VoterVotingStatus addVoterVotingStatus(VoterVotingStatus voterVotingStatus) {
        return voterVotingStatusRepository.save(voterVotingStatus);
    }

    @Transactional
    public void removeAllVoterVotingStatusByElection(Long eid) {
        Election election = electionRepository.findById(eid).orElse(null);

        if (election != null) {
            Set<VoterVotingStatus> voterVotingStatusList = voterVotingStatusRepository.findAllByElection(election);

            // Use iterator to avoid ConcurrentModificationException
            Iterator<VoterVotingStatus> iterator = voterVotingStatusList.iterator();
            while (iterator.hasNext()) {
                VoterVotingStatus voterVotingStatus = iterator.next();
                iterator.remove();
                voterVotingStatusRepository.deleteByElection(election);
            }

            electionRepository.save(election);
        }
    }

    @Override
    public boolean getVoterVotingStatus(User user, Election election) {
       List<VoterVotingStatus> voterVotingStatuses = voterVotingStatusRepository.findAll();
       List<VoterVotingStatus> temp = voterVotingStatuses.stream().filter(vs -> vs.getUser()==user && vs.getElection() ==election).toList();
       return !temp.isEmpty();
    }

    @Override
    public List<VoterVotingStatus> getAll() {
        return new ArrayList<>(voterVotingStatusRepository.findAll());
    }

}
