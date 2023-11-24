package com.candidate.test.repository;

import com.candidate.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByUserName(String userName);

    @Query("select u from User u where u.userName=?1")
    User validateUserName(String userName);
}
