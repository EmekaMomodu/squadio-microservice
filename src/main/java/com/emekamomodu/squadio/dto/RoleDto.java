package com.emekamomodu.squadio.dto;

import com.emekamomodu.squadio.entity.Role;
import com.emekamomodu.squadio.utility.enums.ERole;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 10:35 PM
 */
public class RoleDto {

    private Long id;

    private ERole name;

    public RoleDto() {
    }

    public RoleDto(ERole name) {
        this.name = name;
    }

    public RoleDto(Long id, ERole name) {
        this.id = id;
        this.name = name;
    }

    public RoleDto(Role role) {
        this.id = role.getRoleId();
        this.name = role.getRoleName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
