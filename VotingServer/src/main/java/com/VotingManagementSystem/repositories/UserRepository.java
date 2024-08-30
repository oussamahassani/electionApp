package com.VotingManagementSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.VotingManagementSystem.models.User;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

	@Query(value = "SELECT YEAR(created_date) AS year, MONTH(created_date) AS month, COUNT(*) AS count FROM user GROUP BY YEAR(created_date), MONTH(created_date) ORDER BY year, month" , nativeQuery = true)

	List<Object[]> countUserByDate();
	
	@Query(value="select * from user c order by user_id   DESC  Limit 3 ", nativeQuery = true)

	List<User> findlastThreeUser();

	User findByEmailAndCin(String email, String cin);
}
