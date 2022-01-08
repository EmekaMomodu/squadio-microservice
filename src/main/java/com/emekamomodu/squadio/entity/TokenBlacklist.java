package com.emekamomodu.squadio.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/3/22 10:47 AM
 */
@Entity
@Table(name = "token_blacklist",
        indexes = {@Index(name = "token_blacklist_index_user", columnList = "user_id")})
public class TokenBlacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_blacklist_id")
    private Long tokenBlacklistId;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "create_date", nullable = false)
    private Date createDate = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public TokenBlacklist() {
    }

    public TokenBlacklist(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public Long getTokenBlacklistId() {
        return tokenBlacklistId;
    }

    public void setTokenBlacklistId(Long tokenBlacklistId) {
        this.tokenBlacklistId = tokenBlacklistId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date date) {
        this.createDate = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
