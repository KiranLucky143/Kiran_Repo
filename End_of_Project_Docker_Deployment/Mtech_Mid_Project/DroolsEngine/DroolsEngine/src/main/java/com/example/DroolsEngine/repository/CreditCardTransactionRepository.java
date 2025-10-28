package com.example.DroolsEngine.repository;

import com.example.DroolsEngine.model.CreditCardTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardTransactionRepository extends JpaRepository<CreditCardTransaction, Long> {

    // Pagination query by application ID
    Page<CreditCardTransaction> findByCreditCardApplication_AppId(Long appId, Pageable pageable);

}
