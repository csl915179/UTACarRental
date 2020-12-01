package com.uta.utacarrental.model;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User extends LitePalSupport implements Serializable {

    private long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String UTAID;
    private String role;
    private String phoneoremail;
    private String lastname;
    private String firstname;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private boolean privilege;
    private boolean ismember;

    public long getId() {
        return id;
    }

    private List<Reservation> commentList = new ArrayList<>();

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

    public String getUTAID() {
        return UTAID;
    }

    public void setUTAID(String UTAID) {
        this.UTAID = UTAID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneoremail() {
        return phoneoremail;
    }

    public void setPhoneoremail(String phoneoremail) {
        this.phoneoremail = phoneoremail;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isPrivilege() {
        return privilege;
    }

    public void setPrivilege(boolean privilege) {
        this.privilege = privilege;
    }

    public boolean isIsmember() {
        return ismember;
    }

    public void setIsmember(boolean ismember) {
        this.ismember = ismember;
    }

    public List<Reservation> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Reservation> commentList) {
        this.commentList = commentList;
    }
}
