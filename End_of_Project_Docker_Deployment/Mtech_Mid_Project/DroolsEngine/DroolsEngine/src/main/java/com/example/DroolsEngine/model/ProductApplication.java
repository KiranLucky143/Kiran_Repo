package com.example.DroolsEngine.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_application")
public class ProductApplication {

    @Id
    private Long appId; // 10-digit application id (shared)

    // optional: keep last submitted product name / summary
    private String lastProductName;

    @OneToMany(mappedBy = "productApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductTransaction> transactions = new ArrayList<>();

    public ProductApplication() {}


    public ProductApplication(Long appId) {
        this.appId = appId;
    }

    public Long getAppId() { return appId; }
    public void setAppId(Long appId) { this.appId = appId; }

    public String getLastProductName() { return lastProductName; }
    public void setLastProductName(String lastProductName) { this.lastProductName = lastProductName; }

    public List<ProductTransaction> getTransactions() { return transactions; }
    public void setTransactions(List<ProductTransaction> transactions) { this.transactions = transactions; }

    public void addTransaction(ProductTransaction tx) {
        tx.setProductApplication(this);
        this.transactions.add(tx);
    }
}
