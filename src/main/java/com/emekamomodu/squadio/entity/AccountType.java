package com.emekamomodu.squadio.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 1:32 PM
 */
@Entity
@Table(name = "account_type")
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_type_id")
    private Long accountTypeId;

    @Column(name = "account_type_name")
    private String accountTypeName;

    @OneToMany(mappedBy = "accountType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts;

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "accountTypeId=" + accountTypeId +
                ", accountTypeName='" + accountTypeName + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
