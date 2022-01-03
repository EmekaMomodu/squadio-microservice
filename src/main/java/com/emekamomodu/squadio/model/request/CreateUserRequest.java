package com.emekamomodu.squadio.model.request;

import java.util.Set;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/31/21 5:19 PM
 */
public class CreateUserRequest {

    private String username;

    private String password;

    private Set<String> role;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CreateUserRequest(String username, String password, Set<String> role) {
        this.username = username;
        this.password = password;
        this.role = role;
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

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
