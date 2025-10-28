package com.example.DroolsEngine.repository;

import com.example.DroolsEngine.model.LoanTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, Long> {

    // ✅ Old method (no pagination)
    // List<LoanTransaction> findByLoanApplication_AppId(String appId);

    // ✅ New method with pagination
    Page<LoanTransaction> findByLoanApplication_AppId(String appId, Pageable pageable);
}
