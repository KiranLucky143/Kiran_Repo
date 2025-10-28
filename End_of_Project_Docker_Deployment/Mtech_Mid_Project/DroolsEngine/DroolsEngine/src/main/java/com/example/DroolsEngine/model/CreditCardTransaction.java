package com.example.DroolsEngine.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name = "credit_card_transaction")
public class CreditCardTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // DB Primary Key (Auto Increment)

    @Column(unique = true, nullable = false)
    private Long transactionId; // Business Transaction ID (8 digits)

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    private Double amount;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id", referencedColumnName = "appId", nullable = false)
    private CreditCardApplication creditCardApplication;

    // 🔹 Automatically generate 8-digit transactionId before insert
    @PrePersist
    public void prePersist() {
        if (transactionId == null) {
            Random random = new Random();
            this.transactionId = 10_000_000L + random.nextInt(90_000_000);
        }
        if (transactionDate == null) {
            this.transactionDate = LocalDateTime.now();
        }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTransactionId() { return transactionId; }
    public void setTransactionId(Long transactionId) { this.transactionId = transactionId; }

    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public CreditCardApplication getCreditCardApplication() { return creditCardApplication; }
    public void setCreditCardApplication(CreditCardApplication creditCardApplication) {
        this.creditCardApplication = creditCardApplication;
    }
}
