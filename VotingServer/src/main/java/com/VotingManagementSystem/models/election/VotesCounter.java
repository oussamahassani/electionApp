package com.VotingManagementSystem.models.election;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "votesCounter")
public class VotesCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long votesCounterId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Election election;

    @ManyToOne(fetch = FetchType.EAGER)
    private Candidate candidate;

    private long count;
    
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();
}
