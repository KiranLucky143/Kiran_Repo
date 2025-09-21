package com.example.DroolsEngine.service;

import com.example.DroolsEngine.model.LoanApplication;
import com.example.DroolsEngine.model.RuleDefinition;
import com.example.DroolsEngine.repository.LoanApplicationRepository;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class DroolsEngineService {

    private final RuleFetcherService ruleFetcherService;
    private final LoanApplicationRepository loanAppRepo;

    public DroolsEngineService(RuleFetcherService ruleFetcherService,
                               LoanApplicationRepository loanAppRepo) {
        this.ruleFetcherService = ruleFetcherService;
        this.loanAppRepo = loanAppRepo;
    }

    /**
     * Evaluate loan rules and update the application decision
     * (does NOT create transactions)
     */
    public LoanApplication evaluateLoan(LoanApplication application) {

        // Load rules dynamically
        List<RuleDefinition> rules = ruleFetcherService.fetchRules();
        KieHelper kieHelper = new KieHelper();

        for (RuleDefinition rule : rules) {
            if (!rule.isActive()) continue;

            if ("DRL".equalsIgnoreCase(rule.getRuleType().name()) &&
                    rule.getRuleContent() != null &&
                    !rule.getRuleContent().isBlank()) {
                kieHelper.addContent(rule.getRuleContent().trim(), ResourceType.DRL);
            } else if ("DMN".equalsIgnoreCase(rule.getRuleType().name()) &&
                    rule.getRuleFile() != null) {
                try {
                    byte[] decoded = Base64.getDecoder().decode(rule.getRuleFile());
                    kieHelper.addContent(new String(decoded), ResourceType.DMN);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Fire rules
        KieBase kieBase = kieHelper.build();
        KieSession kieSession = kieBase.newKieSession();
        kieSession.insert(application);
        kieSession.fireAllRules();
        kieSession.dispose();

        // Return evaluated application (decision updated)
        return application;
    }
}
