package com.example.DroolsEngine.controller;

import com.example.DroolsEngine.model.LoanApplication;
import com.example.DroolsEngine.model.LoanTransaction;
import com.example.DroolsEngine.repository.LoanApplicationRepository;
import com.example.DroolsEngine.repository.LoanTransactionRepository;
import com.example.DroolsEngine.service.DroolsEngineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loan")
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

    // 📝 Show loan form
    @GetMapping({"/form", "/loan-form"})
    public String showForm(Model model) {

        // Check if there is any existing application
        LoanApplication existing = loanRepo.findAll().stream().findFirst().orElse(null);

        if (existing == null) {
            // No existing application → create new and assign unique appId
            existing = new LoanApplication();
            existing.setAppId("APP-" + System.currentTimeMillis());
        }

        model.addAttribute("loan", existing);
        return "loan-form";
    }


    // 🧮 Submit loan form
    @PostMapping("/submit")
    public String submitForm(@ModelAttribute("loan") LoanApplication loan, Model model) {

        // Check if this application already exists
        LoanApplication existingApp = loanRepo.findById(loan.getAppId()).orElse(null);

        LoanApplication evaluated;
        if (existingApp != null) {
            // Use existing app to preserve previous transactions
            existingApp.setApplicantName(loan.getApplicantName());
            existingApp.setDateOfBirth(loan.getDateOfBirth());
            existingApp.setPanNumber(loan.getPanNumber());
            existingApp.setAadhaarNumber(loan.getAadhaarNumber());
            existingApp.setEmail(loan.getEmail());
            existingApp.setPhone(loan.getPhone());
            existingApp.setAddress(loan.getAddress());
            existingApp.setEmploymentType(loan.getEmploymentType());
            existingApp.setIncome(loan.getIncome());
            existingApp.setLoanAmount(loan.getLoanAmount());
            existingApp.setLoanTenureMonths(loan.getLoanTenureMonths());
            existingApp.setCreditScore(loan.getCreditScore());

            // ✅ Reset decision before re-evaluating
            existingApp.setDecision("PENDING");


            // Evaluate rules on updated application
            evaluated = droolsEngineService.evaluateLoan(existingApp);
        } else {
            // New application
            evaluated = droolsEngineService.evaluateLoan(loan);
        }

        // Create new transaction
        LoanTransaction tx = new LoanTransaction(evaluated);
        long tx_id = 10_000_000L + (long)(Math.random() * 90_000_000L);
        tx.setId(tx_id);
        evaluated.addTransaction(tx);

        // Save updated application (with old + new transactions)
        loanRepo.save(evaluated);

        model.addAttribute("loan", evaluated);
        return "loan-result";
    }


    // 📋 List all loan applications
    @GetMapping("/all")
    public String listLoans(Model model) {
        model.addAttribute("applications", loanRepo.findAll(Sort.by("appId").descending()));
        return "loan-list";
    }

    // 💳 View transactions (paginated & sortable)
    @GetMapping("/{appId}/transactions")
    public String listTransactions(@PathVariable String appId,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "timestamp") String sortField,
                                   @RequestParam(defaultValue = "desc") String sortDir,
                                   Model model) {

        LoanApplication application = loanRepo.findById(appId).orElse(null);
        if (application == null) {
            model.addAttribute("error", "Application not found!");
            return "error-page";
        }

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, 5, sort);
        Page<LoanTransaction> transactionsPage =
                txRepo.findByLoanApplication_AppId(appId, pageable);

        model.addAttribute("transactionsPage", transactionsPage);
        model.addAttribute("appId", appId);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", transactionsPage.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "loan-transactions";
    }
}
