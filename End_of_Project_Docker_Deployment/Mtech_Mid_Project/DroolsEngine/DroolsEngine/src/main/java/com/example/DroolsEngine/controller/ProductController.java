package com.example.DroolsEngine.controller;

import com.example.DroolsEngine.model.Product;
import com.example.DroolsEngine.model.ProductApplication;
import com.example.DroolsEngine.model.ProductTransaction;
import com.example.DroolsEngine.repository.ProductApplicationRepository;
import com.example.DroolsEngine.repository.ProductTransactionRepository;
import com.example.DroolsEngine.service.DroolsEngineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final DroolsEngineService droolsEngineService;
    private final ProductApplicationRepository productAppRepo;
    private final ProductTransactionRepository productTxRepo;

    public ProductController(DroolsEngineService droolsEngineService,
                             ProductApplicationRepository productAppRepo,
                             ProductTransactionRepository productTxRepo) {
        this.droolsEngineService = droolsEngineService;
        this.productAppRepo = productAppRepo;
        this.productTxRepo = productTxRepo;
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute Product product, Model model) {
        // Evaluate product
        Product evaluated = droolsEngineService.evaluateProduct(product);

        // Fetch or create ProductApplication
        ProductApplication app = productAppRepo.findAll().stream().findFirst().orElseGet(() -> {
            long appId = (long)(Math.random() * 9_000_000_000L) + 1_000_000_000L;
            ProductApplication pa = new ProductApplication(appId);
            return productAppRepo.save(pa);
        });

        app.setLastProductName(evaluated.getProductName());
        productAppRepo.save(app);

        // Save transaction
        ProductTransaction tx = new ProductTransaction();
        tx.setProductName(evaluated.getProductName());
        tx.setBankName(evaluated.getBankName());
        tx.setCreditCardType(evaluated.getCreditCardType());
        tx.setPaymentType(evaluated.getPaymentType());
        tx.setOfferName(evaluated.getOfferName());
        tx.setDecision(evaluated.getDecision());
        tx.setPurchaseAmount(evaluated.getPurchaseAmount());
        tx.setCashbackAmount(evaluated.getCashbackAmount());
        tx.setFinalAmount(evaluated.getFinalAmount());
        tx.setTimestamp(LocalDateTime.now());
        tx.setProductApplication(app);

        ProductTransaction savedTx = productTxRepo.save(tx);
        savedTx.setTransactionId(10_000_000L + (savedTx.getId() % 90_000_000L));
        productTxRepo.save(savedTx);

        model.addAttribute("product", evaluated);
        model.addAttribute("appId", app.getAppId());
        model.addAttribute("transactionId", savedTx.getTransactionId());

        return "product-result";
    }

    @GetMapping("/{appId}/transactions")
    public String viewTransactions(@PathVariable Long appId,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "timestamp") String sortField,
                                   @RequestParam(defaultValue = "desc") String sortDir,
                                   Model model) {

        Optional<ProductApplication> optionalApp = productAppRepo.findById(appId);
        if (optionalApp.isEmpty()) {
            model.addAttribute("error", "Application not found!");
            return "error-page";
        }
        ProductApplication app = optionalApp.get();

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, 5, sort); // 5 per page
        Page<ProductTransaction> txPage = productTxRepo.findByProductApplication_AppId(appId, pageable);

        model.addAttribute("transactionsPage", txPage);
        model.addAttribute("appId", appId);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", txPage.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "product-transactions";
    }

    @GetMapping("/all")
    public String listAllProducts(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "timestamp") String sortField,
                                  @RequestParam(defaultValue = "desc") String sortDir,
                                  Model model) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, 10, sort); // 10 per page
        Page<ProductTransaction> txPage = productTxRepo.findAll(pageable);

        model.addAttribute("productsPage", txPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", txPage.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "product-list";
    }
}
