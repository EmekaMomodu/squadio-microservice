package com.emekamomodu.squadio.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 1:33 PM
 */
@Entity
@Table(name = "account_statement")
public class AccountStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_statement_id")
    private Long accountStatementId;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private String amount;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Long getAccountStatementId() {
        return accountStatementId;
    }

    public void setAccountStatementId(Long accountStatementId) {
        this.accountStatementId = accountStatementId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "AccountStatement{" +
                "accountStatementId=" + accountStatementId +
                ", description='" + description + '\'' +
                ", amount='" + amount + '\'' +
                ", date=" + date +
                ", account=" + account +
                '}';
    }
}
