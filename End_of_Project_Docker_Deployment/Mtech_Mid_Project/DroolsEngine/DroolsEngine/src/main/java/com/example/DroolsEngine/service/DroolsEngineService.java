package com.example.DroolsEngine.service;

import com.example.DroolsEngine.model.*;
import com.example.DroolsEngine.repository.LoanApplicationRepository;
import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.KieBase;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
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

    // -------------------------------------------------------------------
    // 🔹 CREDIT CARD EVALUATION (existing)
    // -------------------------------------------------------------------
    public CreditCardApplication evaluateCreditCard(CreditCardApplication application) {

        // Normalize & validate
        if (application.getEmploymentType() == null) application.setEmploymentType("");
        else application.setEmploymentType(application.getEmploymentType().trim());

        if (application.getDecision() == null || application.getDecision().isBlank())
            application.setDecision("PENDING");

        if (application.getIncome() == null || application.getIncome() < 0)
            application.setIncome(0.0);

        if (application.getCibilScore() == null || application.getCibilScore() < 0)
            application.setCibilScore(0);

        System.out.println(">>> [Drools Input]: " + application);

        List<RuleDefinition> rules = ruleFetcherService.fetchRules();
        KieHelper kieHelper = buildKieHelper(rules);

        KieBase kieBase = kieHelper.build();
        KieSession kieSession = kieBase.newKieSession();
        kieSession.insert(application);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println("✅ Card Decision: " + application.getDecision());
        System.out.println("✅ Card Type: " + application.getCardType());

        return application;
    }

    // -------------------------------------------------------------------
    // 🔹 LOAN EVALUATION (debug-enabled)
    // -------------------------------------------------------------------
    public LoanApplication evaluateLoan(LoanApplication application) {

        System.out.println("========== [LOAN EVALUATION STARTED] ==========");
        System.out.println("▶ Application ID: " + application.getAppId());
        System.out.println("▶ Applicant: " + application.getApplicantName());
        System.out.println("▶ DOB: " + application.getDateOfBirth());
        System.out.println("▶ Age (computed): " + application.getAge());
        System.out.println("▶ Income: " + application.getIncome());
        System.out.println("▶ LoanAmount: " + application.getLoanAmount());
        System.out.println("▶ CreditScore: " + application.getCreditScore());
        System.out.println("▶ EmploymentType: " + application.getEmploymentType());
        System.out.println("▶ Initial Decision: " + application.getDecision());

        List<RuleDefinition> rules = ruleFetcherService.fetchRules();
        System.out.println("🧩 Rules fetched from DB: " + rules.size());
        rules.forEach(r -> System.out.println("   - " + r.getRuleName() + " (" + r.getRuleType() + ") Active=" + r.isActive()));

        KieHelper kieHelper = buildKieHelper(rules);
        KieBase kieBase = kieHelper.build();
        KieSession kieSession = kieBase.newKieSession();

        // 🔍 Add listener to log which rules fire
        kieSession.addEventListener(new DefaultAgendaEventListener() {
            @Override
            public void afterMatchFired(AfterMatchFiredEvent event) {
                System.out.println("🔥 Rule Fired: " + event.getMatch().getRule().getName());
                System.out.println("    → Facts: " + event.getMatch().getObjects());
            }
        });
        System.out.println("Before Inserting Fact:");
        System.out.println("DOB type: " +
                (application.getDateOfBirth() == null ? "null" : application.getDateOfBirth().getClass()));
        System.out.println("Age: " + application.getAge());



        System.out.println("🚀 Inserting fact into KieSession: " + application.getClass().getName());
        kieSession.insert(application);

        int fired = kieSession.fireAllRules();
        System.out.println("✅ Total Rules Fired: " + fired);

        kieSession.dispose();

        System.out.println("🏁 Final Decision: " + application.getDecision());
        System.out.println("========== [LOAN EVALUATION ENDED] ==========\n");

        return application;
    }

    // -------------------------------------------------------------------
    // 🔹 PRODUCT EVALUATION (NEW)
    // -------------------------------------------------------------------
    public Product evaluateProduct(Product product) {
        if (product.getDecision() == null || product.getDecision().isBlank()) {
            product.setDecision("PENDING");
        }

        List<RuleDefinition> rules = ruleFetcherService.fetchRules();
        KieHelper kieHelper = new KieHelper();

        for (RuleDefinition rule : rules) {
            if (!rule.isActive()) continue;
            try {
                if ("DRL".equalsIgnoreCase(rule.getRuleType().name()) && rule.getRuleContent() != null) {
                    kieHelper.addContent(rule.getRuleContent(), ResourceType.DRL);
                } else if ("DMN".equalsIgnoreCase(rule.getRuleType().name()) && rule.getRuleFile() != null) {
                    byte[] decoded = Base64.getDecoder().decode(rule.getRuleFile());
                    kieHelper.addContent(new String(decoded), ResourceType.DMN);
                }
            } catch (Exception e) {
                System.err.println("⚠ Error loading rule: " + rule.getRuleName());
                e.printStackTrace();
            }
        }

        KieBase kieBase = kieHelper.build();
        KieSession kieSession = kieBase.newKieSession();
        kieSession.insert(product);
        int fired = kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println("✅ Fired rules: " + fired);
        System.out.println("✅ Product Offer: " + product.getOfferName());
        System.out.println("✅ Decision: " + product.getDecision());

        return product;
    }


    // -------------------------------------------------------------------
    // 🔹 Shared helper for building KieHelper with rules
    // -------------------------------------------------------------------
    private KieHelper buildKieHelper(List<RuleDefinition> rules) {
        KieHelper kieHelper = new KieHelper();

        for (RuleDefinition rule : rules) {
            if (!rule.isActive()) continue;

            try {
                if ("DRL".equalsIgnoreCase(rule.getRuleType().name())
                        && rule.getRuleContent() != null
                        && !rule.getRuleContent().isBlank()) {

                    kieHelper.addContent(rule.getRuleContent().trim(), ResourceType.DRL);

                } else if ("DMN".equalsIgnoreCase(rule.getRuleType().name())
                        && rule.getRuleFile() != null) {

                    byte[] decoded = Base64.getDecoder().decode(rule.getRuleFile());
                    kieHelper.addContent(new String(decoded), ResourceType.DMN);
                }

            } catch (Exception e) {
                System.err.println("⚠ Error loading rule: " + rule.getRuleName());
                e.printStackTrace();
            }
        }
        return kieHelper;
}
}