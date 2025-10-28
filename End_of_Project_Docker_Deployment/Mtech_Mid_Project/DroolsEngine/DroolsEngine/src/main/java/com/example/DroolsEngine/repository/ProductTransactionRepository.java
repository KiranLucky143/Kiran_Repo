package com.example.DroolsEngine.repository;

import com.example.DroolsEngine.model.ProductTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTransactionRepository extends JpaRepository<ProductTransaction, Long> {
    Page<ProductTransaction> findByProductApplication_AppId(Long appId, Pageable pageable);
}
