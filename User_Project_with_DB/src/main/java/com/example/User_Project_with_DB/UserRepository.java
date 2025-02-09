package com.example.User_Project_with_DB;


import com.example.User_Project_with_DB.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

