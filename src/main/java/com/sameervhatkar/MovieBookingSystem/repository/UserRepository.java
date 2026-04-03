package com.sameervhatkar.MovieBookingSystem.repository;

import com.sameervhatkar.MovieBookingSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName(String name);
}
