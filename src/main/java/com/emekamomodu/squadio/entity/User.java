package com.emekamomodu.squadio.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Set;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 1:31 PM
 */
@Entity
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "login_flag")
    @ColumnDefault("'N'")
    private String loginFlag = "N";

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> userRoles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TokenBlacklist> tokenBlacklists;

    public User() {
    }

    public User(Long userId) {
        this.userId = userId;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Set<Role> userRoles) {
        this.username = username;
        this.password = password;
        this.userRoles = userRoles;
    }

    public User(Long userId, String username, String password, String loginFlag, Set<Role> userRoles, Set<Account> accounts, Set<TokenBlacklist> tokenBlacklists) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.loginFlag = loginFlag;
        this.userRoles = userRoles;
        this.accounts = accounts;
        this.tokenBlacklists = tokenBlacklists;
    }

    public User(User user) {
        this.userId = user.userId;
        this.username = user.username;
        this.password = user.password;
        this.loginFlag = user.loginFlag;
        this.userRoles = user.userRoles;
        this.accounts = user.accounts;
        this.tokenBlacklists = user.tokenBlacklists;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
