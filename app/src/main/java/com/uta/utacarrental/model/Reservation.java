package com.uta.utacarrental.model;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.Date;

public class Reservation extends LitePalSupport implements Serializable {

    @Column(unique = true)
    private int reservationNumber;
    private String firstName;
    private String lastName;
    private Date reservationTime;
    private Date startTime;
    private Date endTime;
    private int riderNumber;
    private double totalCost;
    private boolean gps;
    private boolean sirius;
    private boolean onStart;
    private boolean member;
    private User user;

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getRiderNumber() {
        return riderNumber;
    }

    public void setRiderNumber(int riderNumber) {
        this.riderNumber = riderNumber;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isGps() {
        return gps;
    }

    public void setGps(boolean gps) {
        this.gps = gps;
    }

    public boolean isSirius() {
        return sirius;
    }

    public void setSirius(boolean sirius) {
        this.sirius = sirius;
    }

    public boolean isOnStart() {
        return onStart;
    }

    public void setOnStart(boolean onStart) {
        this.onStart = onStart;
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
