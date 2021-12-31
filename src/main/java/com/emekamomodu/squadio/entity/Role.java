package com.emekamomodu.squadio.entity;

import com.emekamomodu.squadio.utility.enums.ERole;

import javax.persistence.*;
import java.util.Set;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 12/30/21 1:31 PM
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private ERole roleName;

    @ManyToMany(mappedBy = "userRoles")
    private Set<User> users;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public ERole getRoleName() {
        return roleName;
    }

    public void setRoleName(ERole roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", users=" + users +
                '}';
    }
}
