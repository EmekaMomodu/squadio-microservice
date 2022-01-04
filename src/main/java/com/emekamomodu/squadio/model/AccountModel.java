package com.emekamomodu.squadio.model;

import java.math.BigDecimal;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/4/22 8:37 AM
 */
public class AccountModel {

    private Long Id;

    private String accountType;

    private String accountNumber;

    private String IBAN;

    private BigDecimal balance;

    private String currency;

    public AccountModel() {
    }

    public AccountModel(Long id, String accountType, String accountNumber, String IBAN, BigDecimal balance, String currency) {
        Id = id;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.IBAN = IBAN;
        this.balance = balance;
        this.currency = currency;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "Id=" + Id +
                ", accountType='" + accountType + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", IBAN='" + IBAN + '\'' +
                ", balance='" + balance + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
