package com.emekamomodu.squadio.model;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/5/22 10:59 AM
 */
public class AuthModel {

    private Long userId;

    private String token;


    public AuthModel() {
    }

    public AuthModel(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthModel{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                '}';
    }
}
