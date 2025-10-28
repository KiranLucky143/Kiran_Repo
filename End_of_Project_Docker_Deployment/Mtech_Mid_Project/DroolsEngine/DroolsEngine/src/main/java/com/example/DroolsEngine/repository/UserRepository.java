package com.example.DroolsEngine.repository;

import com.example.DroolsEngine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);


    List<User> findByUsernameContainingIgnoreCase(String keyword);
}
