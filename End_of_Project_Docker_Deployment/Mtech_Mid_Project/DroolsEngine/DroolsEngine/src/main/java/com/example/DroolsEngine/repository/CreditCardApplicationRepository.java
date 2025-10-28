package com.example.DroolsEngine.repository;

import com.example.DroolsEngine.model.CreditCardApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardApplicationRepository extends JpaRepository<CreditCardApplication, Long> {
}
