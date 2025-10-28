package com.example.DroolsEngine.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.Period;

@Entity
public class LoanApplication {

    @Id
    private String appId;

    private String applicantName;
    private LocalDate dateOfBirth;
    private String panNumber;
    private String aadhaarNumber;
    private String email;
    private String phone;
    private String address;
    private String employmentType;

    private double income;
    private double loanAmount;
    private Integer loanTenureMonths;
    private Integer creditScore;
    private int age;
    private String decision;

    //  Transactions relationship
    @OneToMany(mappedBy = "loanApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanTransaction> transactions = new ArrayList<>();

    public LoanApplication() {
        this.decision = "PENDING";
    }

    public LoanApplication(String appId, double income, double loanAmount) {
        this.appId = appId;
        this.income = income;
        this.loanAmount = loanAmount;
        this.decision = "PENDING";
    }

    public LoanApplication(String appId, double income, double loanAmount, String decision) {
        this.appId = appId;
        this.income = income;
        this.loanAmount = loanAmount;
        this.decision = decision;
    }

    //  Helper method for transactions
    public void addTransaction(LoanTransaction tx) {
        tx.setLoanApplication(this);
        this.transactions.add(tx);
    }

    // --- Getters & Setters ---

    public String getAppId() { return appId; }
    public void setAppId(String appId) { this.appId = appId; }

    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPanNumber() { return panNumber; }
    public void setPanNumber(String panNumber) { this.panNumber = panNumber; }

    public String getAadhaarNumber() { return aadhaarNumber; }
    public void setAadhaarNumber(String aadhaarNumber) { this.aadhaarNumber = aadhaarNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmploymentType() { return employmentType; }
    public void setEmploymentType(String employmentType) { this.employmentType = employmentType; }

    public double getIncome() { return income; }
    public void setIncome(double income) { this.income = income; }

    public double getLoanAmount() { return loanAmount; }
    public void setLoanAmount(double loanAmount) { this.loanAmount = loanAmount; }

    public Integer getLoanTenureMonths() { return loanTenureMonths; }
    public void setLoanTenureMonths(Integer loanTenureMonths) { this.loanTenureMonths = loanTenureMonths; }

    public Integer getCreditScore() { return creditScore; }
    public void setCreditScore(Integer creditScore) { this.creditScore = creditScore; }

    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }

    public List<LoanTransaction> getTransactions() { return transactions; }
    public void setTransactions(List<LoanTransaction> transactions) { this.transactions = transactions; }

    // inside LoanApplication class
    public int getAge() {
        if (this.dateOfBirth == null) return 0;
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
