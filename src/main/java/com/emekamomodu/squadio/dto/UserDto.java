package com.emekamomodu.squadio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 9:38 PM
 */
public class UserDto {

    private Long id;

    private String name;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Set<RoleDto> userRoles;

    public UserDto() {
    }

    public UserDto(String name, String password, Set<RoleDto> userRoles) {
        this.name = name;
        this.password = password;
        this.userRoles = userRoles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDto> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<RoleDto> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userRoles=" + userRoles +
                '}';
    }
}
