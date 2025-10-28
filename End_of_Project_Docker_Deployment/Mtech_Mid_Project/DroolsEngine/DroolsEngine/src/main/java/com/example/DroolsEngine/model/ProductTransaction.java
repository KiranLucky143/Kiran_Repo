package com.example.DroolsEngine.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_transaction")
public class ProductTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // DB PK

    @Column(unique = true)
    private Long transactionId; // business 8-digit transaction reference

    private String productName;
    private String bankName;
    private String creditCardType;
    private String paymentType;
    private String offerName;
    private String decision;

    private LocalDateTime timestamp;

    private Double purchaseAmount;
    private Double cashbackAmount;
    private Double finalAmount;

    // Add corresponding getters & setters
    public Double getPurchaseAmount() { return purchaseAmount; }
    public void setPurchaseAmount(Double purchaseAmount) { this.purchaseAmount = purchaseAmount; }

    public Double getCashbackAmount() { return cashbackAmount; }
    public void setCashbackAmount(Double cashbackAmount) { this.cashbackAmount = cashbackAmount; }

    public Double getFinalAmount() { return finalAmount; }
    public void setFinalAmount(Double finalAmount) { this.finalAmount = finalAmount; }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id", referencedColumnName = "appId", nullable = false)
    private ProductApplication productApplication;

    public ProductTransaction() {
        this.timestamp = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTransactionId() { return transactionId; }
    public void setTransactionId(Long transactionId) { this.transactionId = transactionId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getCreditCardType() { return creditCardType; }
    public void setCreditCardType(String creditCardType) { this.creditCardType = creditCardType; }

    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }

    public String getOfferName() { return offerName; }
    public void setOfferName(String offerName) { this.offerName = offerName; }

    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public ProductApplication getProductApplication() { return productApplication; }
    public void setProductApplication(ProductApplication productApplication) { this.productApplication = productApplication; }
}
