package com.example.DroolsEngine.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
public class LoanTransaction {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY) //  AUTO_INCREMENT in MySQL
    private Long id;

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

    private String decision;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "app_id")
    private LoanApplication loanApplication;

    public LoanTransaction() {
        this.timestamp = LocalDateTime.now();
    }

    //  Full snapshot constructor from LoanApplication
    public LoanTransaction(LoanApplication loan) {
        this.applicantName = loan.getApplicantName();
        this.dateOfBirth = loan.getDateOfBirth();
        this.panNumber = loan.getPanNumber();
        this.aadhaarNumber = loan.getAadhaarNumber();
        this.email = loan.getEmail();
        this.phone = loan.getPhone();
        this.address = loan.getAddress();
        this.employmentType = loan.getEmploymentType();
        this.income = loan.getIncome();
        this.loanAmount = loan.getLoanAmount();
        this.loanTenureMonths = loan.getLoanTenureMonths();
        this.creditScore = loan.getCreditScore();
        this.decision = loan.getDecision();
        this.timestamp = LocalDateTime.now();
        this.loanApplication = loan;
    }

    // --- getters & setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public LoanApplication getLoanApplication() { return loanApplication; }
    public void setLoanApplication(LoanApplication loanApplication) { this.loanApplication = loanApplication; }
}
