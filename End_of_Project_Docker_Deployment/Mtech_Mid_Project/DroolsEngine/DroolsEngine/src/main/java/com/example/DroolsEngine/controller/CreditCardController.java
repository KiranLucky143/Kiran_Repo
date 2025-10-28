package com.example.DroolsEngine.controller;

import com.example.DroolsEngine.model.CreditCardApplication;
import com.example.DroolsEngine.model.CreditCardTransaction;
import com.example.DroolsEngine.repository.CreditCardApplicationRepository;
import com.example.DroolsEngine.repository.CreditCardTransactionRepository;
import com.example.DroolsEngine.service.DroolsEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/card")
public class CreditCardController {

    @Autowired
    private CreditCardApplicationRepository cardRepo;

    @Autowired
    private CreditCardTransactionRepository transactionRepo;

    @Autowired
    private DroolsEngineService droolsService;

    // 🧾 Show Credit Card Application Form
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("card", new CreditCardApplication());
        return "card-form";
    }

    // 🧮 Submit and Evaluate Application
    @PostMapping("/submit")
    public String submitApplication(@ModelAttribute("card") CreditCardApplication card, Model model) {

        // ✅ Always use the same first application (single appId)
        CreditCardApplication application = cardRepo.findAll().stream().findFirst().orElse(null);

        if (application == null) {
            long randomAppId = (long) (Math.random() * 9_000_000_000L) + 1_000_000_000L;
            application = new CreditCardApplication();
            application.setAppId(randomAppId);
        }

        // Update fields
        application.setApplicantName(card.getApplicantName());
        application.setDateOfBirth(card.getDateOfBirth());
        application.setPanNumber(card.getPanNumber());
        application.setAadhaarNumber(card.getAadhaarNumber());
        application.setPhone(card.getPhone());
        application.setEmail(card.getEmail());
        application.setAddress(card.getAddress());
        application.setEmploymentType(card.getEmploymentType());
        application.setIncome(card.getIncome());
        application.setCibilScore(card.getCibilScore());

        // Evaluate via Drools
        CreditCardApplication evaluated = droolsService.evaluateCreditCard(application);
        cardRepo.save(evaluated);

        // ✅ Create new transaction
        CreditCardTransaction transaction = new CreditCardTransaction();
        transaction.setCreditCardApplication(evaluated);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAmount(evaluated.getIncome());
        transaction.setDescription("Card Application Submitted");

        // Save to get DB-generated ID
        CreditCardTransaction savedTransaction = transactionRepo.save(transaction);

        // Generate 8-digit transaction ID
        long transactionId = 10_000_000L + (savedTransaction.getId() % 90_000_000L);
        savedTransaction.setTransactionId(transactionId);
        transactionRepo.save(savedTransaction);

        model.addAttribute("card", evaluated);
        return "card-result";
    }

    // 📋 Show all applications
    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("cards", cardRepo.findAll(Sort.by("appId").descending()));
        return "card-list";
    }

    // 🔍 View one application
    @GetMapping("/{id}")
    public String viewCard(@PathVariable Long id, Model model) {
        CreditCardApplication card = cardRepo.findById(id).orElse(null);
        if (card == null) {
            model.addAttribute("error", "Application not found!");
            return "error-page";
        }
        model.addAttribute("card", card);
        return "card-result";
    }

    // 💳 Transactions with pagination + sorting
    @GetMapping("/{appId}/transactions")
    public String viewTransactions(
            @PathVariable Long appId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "transactionDate") String sortField,
            @RequestParam(defaultValue = "desc") String sortDir,
            Model model) {

        CreditCardApplication application = cardRepo.findById(appId).orElse(null);
        if (application == null) {
            model.addAttribute("error", "Application not found!");
            return "error-page";
        }

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, 5, sort);
        Page<CreditCardTransaction> transactionsPage =
                transactionRepo.findByCreditCardApplication_AppId(appId, pageable);

        model.addAttribute("transactionsPage", transactionsPage);
        model.addAttribute("appId", appId);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", transactionsPage.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "card-transactions";
    }
}
