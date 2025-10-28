package com.example.rulemanager.repository;

import com.example.rulemanager.model.RuleDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RuleRepository extends JpaRepository<RuleDefinition, Long> {
    //  Custom query: fetch only active rules
    List<RuleDefinition> findByActiveTrue();
}
