package com.VotingManagementSystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long phone;
    private String address;
    private String gouvernerat;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    //user has many roles
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private VoterVerification verification;

}

