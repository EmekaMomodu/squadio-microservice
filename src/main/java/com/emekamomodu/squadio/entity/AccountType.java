package com.emekamomodu.squadio.entity;

import com.emekamomodu.squadio.model.EAccountType;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type_name", unique=true)
    private EAccountType accountTypeName;

    @OneToMany(mappedBy = "accountType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts;

    public AccountType() {
    }

    public AccountType(EAccountType accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public EAccountType getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(EAccountType accountTypeName) {
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
        return "AccountTypeRepository{" +
                "accountTypeId=" + accountTypeId +
                ", accountTypeName='" + accountTypeName + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
