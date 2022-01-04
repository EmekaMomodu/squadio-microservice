package com.emekamomodu.squadio.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/4/22 9:43 AM
 */
public class AccountStatementModel {

    private String accountNumber;

    private String description;

    private BigDecimal amount;

    private Date date;

    public AccountStatementModel() {
    }

    public AccountStatementModel(String accountNumber, String description, BigDecimal amount, Date date) {
        this.accountNumber = accountNumber;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AccountStatementModel{" +
                "accountNumber='" + accountNumber + '\'' +
                ", description='" + description + '\'' +
                ", amount='" + amount + '\'' +
                ", date=" + date +
                '}';
    }
}
