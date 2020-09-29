package com.uta.utacarrental;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class User extends LitePalSupport implements Serializable {
    @Column(unique = true)
    private String name;
    private String password;
    private String UTAID;
    private String role;


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUTAID() {
        return UTAID;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUTAID(String UTAID) {
        this.UTAID = UTAID;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
