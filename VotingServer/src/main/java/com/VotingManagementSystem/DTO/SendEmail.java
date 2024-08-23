package com.VotingManagementSystem.DTO;

import java.util.Set;

import com.VotingManagementSystem.models.UserRole;
import com.VotingManagementSystem.models.VoterVerification;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendEmail {

	private String email ;
	private String message;
	private String name;
	private String phone;
}
