package com.example.DroolsEngine.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class CreditCardApplication {

    @Id
    private Long appId;  // Removed @GeneratedValue, we will assign manually

    private String applicantName;
    private LocalDate dateOfBirth;
    private String panNumber;
    private String aadhaarNumber;
    private String phone;
    private String email;
    private String address;
    private String employmentType;

    private Double income;
    private Integer cibilScore;

    private String cardType;   // Gold / Platinum / Titanium
    private String decision;   // APPROVED / REJECTED / PENDING

    // -----------------------------
    // Getters & Setters
    // -----------------------------
    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Integer getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(Integer cibilScore) {
        this.cibilScore = cibilScore;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public int getAge() {
        if (this.dateOfBirth == null) return 0;
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
