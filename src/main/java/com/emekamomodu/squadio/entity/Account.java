package com.emekamomodu.squadio.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 1:31 PM
 */
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "iban")
    private String iban;

    @Column(name = "balance")
    private String balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountStatement> accountStatements;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Set<AccountStatement> getAccountStatements() {
        return accountStatements;
    }

    public void setAccountStatements(Set<AccountStatement> accountStatements) {
        this.accountStatements = accountStatements;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountNumber='" + accountNumber + '\'' +
                ", iban='" + iban + '\'' +
                ", balance='" + balance + '\'' +
                ", user=" + user +
                ", accountType=" + accountType +
                ", currency=" + currency +
                ", accountStatements=" + accountStatements +
                '}';
    }
}
