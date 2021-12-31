package com.emekamomodu.squadio.payload.request;

import javax.validation.constraints.NotBlank;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/31/21 12:29 PM
 */
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

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

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                '}';
    }
}
