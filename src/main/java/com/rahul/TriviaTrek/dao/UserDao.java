package com.rahul.TriviaTrek.dao;

import com.rahul.TriviaTrek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
//    @Query(value = "SELECT * FROM user_table WHERE email=:email LIMIT 1", nativeQuery = true)
//    User findByEmail(String email);
    Optional<User> findByEmail(String email);
}
