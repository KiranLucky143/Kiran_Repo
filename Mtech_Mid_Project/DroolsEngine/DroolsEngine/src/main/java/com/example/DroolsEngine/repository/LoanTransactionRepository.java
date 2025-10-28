package com.example.DroolsEngine.repository;

import com.example.DroolsEngine.model.LoanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, Long> {
    List<LoanTransaction> findByLoanApplication_AppId(String appId);
}
