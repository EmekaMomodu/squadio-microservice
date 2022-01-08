package com.emekamomodu.squadio.entity;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @Column(name = "amount", scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public AccountStatement() {
    }

    public AccountStatement(String description, BigDecimal amount, Date createDate, Account account) {
        this.description = description;
        this.amount = amount;
        this.createDate = createDate;
        this.account = account;
    }

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date date) {
        this.createDate = date;
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
                ", date=" + createDate +
                ", account=" + account +
                '}';
    }
}
