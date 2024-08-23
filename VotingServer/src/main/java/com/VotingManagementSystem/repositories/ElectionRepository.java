package com.VotingManagementSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.VotingManagementSystem.models.election.Election;

public interface ElectionRepository extends JpaRepository<Election,Long> {

	@Query(value="select * from election c order by election_id  DESC  Limit 3 ", nativeQuery = true)

	List<Election> findlastThreeElections();
}
