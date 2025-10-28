package com.example.DroolsEngine.model;

public class Product {

    private String productName;
    private Double purchaseAmount;
    private Double cashbackAmount;
    private Double finalAmount;

    private String bankName;
    private String creditCardType; // e.g., ICICI VISA
    private String paymentType;    // COD, UPI, Credit Card, Debit Card, Wallet
    private String offerName;
    private String decision;       // PENDING / ELIGIBLE / REJECTED

    public Product() {}

    // Getters & Setters
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Double getPurchaseAmount() { return purchaseAmount; }
    public void setPurchaseAmount(Double purchaseAmount) { this.purchaseAmount = purchaseAmount; }

    public Double getCashbackAmount() { return cashbackAmount; }
    public void setCashbackAmount(Double cashbackAmount) { this.cashbackAmount = cashbackAmount; }

    public Double getFinalAmount() { return finalAmount; }
    public void setFinalAmount(Double finalAmount) { this.finalAmount = finalAmount; }

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
}
