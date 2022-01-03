package com.emekamomodu.squadio.model;

/**
 * @author CMOMODU
 * @version 1.0
 * @date 1/2/22 11:16 PM
 */
public class UserModel {

    private Long id;

    private String name;

    public UserModel() {
    }

    public UserModel(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
