package com.example.DroolsEngine.controller;

import com.example.DroolsEngine.model.LoanApplication;
import com.example.DroolsEngine.model.LoanTransaction;
import com.example.DroolsEngine.repository.LoanApplicationRepository;
import com.example.DroolsEngine.repository.LoanTransactionRepository;
import com.example.DroolsEngine.service.DroolsEngineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class LoanUIController {

    private final DroolsEngineService droolsEngineService;
    private final LoanApplicationRepository loanRepo;
    private final LoanTransactionRepository txRepo;

    public LoanUIController(DroolsEngineService droolsEngineService,
                            LoanApplicationRepository loanRepo,
                            LoanTransactionRepository txRepo) {
        this.droolsEngineService = droolsEngineService;
        this.loanRepo = loanRepo;
        this.txRepo = txRepo;
    }

    // Show loan form
    @GetMapping("/loan-form")
    public String showForm(Model model) {
        LoanApplication existing = loanRepo.findAll().stream().findFirst().orElse(new LoanApplication());
        model.addAttribute("loan", existing);
        return "loan-form";
    }

    // Submit loan form
    @PostMapping("/loan/submit")
    public String submitForm(@RequestParam String applicantName,
                             @RequestParam String dateOfBirth,
                             @RequestParam String panNumber,
                             @RequestParam String aadhaarNumber,
                             @RequestParam String email,
                             @RequestParam String phone,
                             @RequestParam String address,
                             @RequestParam String employmentType,
                             @RequestParam double income,
                             @RequestParam double loanAmount,
                             @RequestParam Integer loanTenureMonths,
                             @RequestParam Integer creditScore,
                             Model model) {

        // Fetch or create application
        LoanApplication application = loanRepo.findAll().stream().findFirst().orElseGet(() -> {
            String appId = String.valueOf((int) (Math.random() * 90000000) + 10000000);
            return new LoanApplication(appId, income, loanAmount);
        });

        // Update fields
        application.setApplicantName(applicantName);
        application.setDateOfBirth(LocalDate.parse(dateOfBirth));
        application.setPanNumber(panNumber);
        application.setAadhaarNumber(aadhaarNumber);
        application.setEmail(email);
        application.setPhone(phone);
        application.setAddress(address);
        application.setEmploymentType(employmentType);
        application.setIncome(income);
        application.setLoanAmount(loanAmount);
        application.setLoanTenureMonths(loanTenureMonths);
        application.setCreditScore(creditScore);

        // Evaluate loan rules
        LoanApplication evaluated = droolsEngineService.evaluateLoan(application);

        // Create **one transaction per submission**
        LoanTransaction tx = new LoanTransaction(evaluated);
        evaluated.addTransaction(tx);

        // Save application with new transaction
        loanRepo.save(evaluated);

        model.addAttribute("loan", evaluated);
        return "loan-result";
    }

    // List all loan applications
    @GetMapping("/loan/all")
    public String listLoans(Model model) {
        model.addAttribute("applications", loanRepo.findAll());
        return "loan-list";
    }

    // List transactions for a loan
    @GetMapping("/loan/{appId}/transactions")
    public String listTransactions(@PathVariable String appId, Model model) {
        List<LoanTransaction> transactions = txRepo.findByLoanApplication_AppId(appId);
        model.addAttribute("transactions", transactions);
        model.addAttribute("appId", appId);
        return "loan-transactions";
    }
}
