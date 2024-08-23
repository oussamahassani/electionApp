package com.VotingManagementSystem.models.election;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "election")
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long election_id;
    private String name;
    private String descriptions;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "election")
    @JsonIgnore
    private Set<Candidate> candidates = new HashSet<>();

}
