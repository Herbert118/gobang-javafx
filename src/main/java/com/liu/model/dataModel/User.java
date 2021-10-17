package com.liu.model.dataModel;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 7495446426670336572L;
    private String name;
    private String id;
    private String password;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User(String name, String id, String password, String email,String authority) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.authority = authority;
        this.email = email;
    }

    private String authority;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return !getId().equals(user.getId()) && getEmail().equals(user.getEmail());
    }


    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public User(String name, String id, String password, String email) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.authority = "user";
    }
}
