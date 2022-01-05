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
        indexes = {@Index(name = "token_blacklist_index_user",  columnList="user_id")})
public class TokenBlacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_blacklist_id")
    private Long tokenBlacklistId;

    @Column(name = "token")
    private String token;

    @Column(name = "date")
    private Date date = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id")
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
