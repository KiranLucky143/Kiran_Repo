package com.example.DroolsEngine.repository;

import com.example.DroolsEngine.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, String> {
}

