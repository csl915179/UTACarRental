package com.uta.utacarrental.database;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.Date;

public class Reservation extends LitePalSupport implements Serializable {

    @Column(unique = true)
    private int reservationNumber;
    private String username;
    private int carNumber;
    private String carName;
    private int capacity;
    private String lastName;
    private Date startTime;
    private Date endTime;
    private String numberRiders;
    private double totalCost;
    private boolean GPS;
    private boolean Sirius;
    private boolean OnStart;
    private boolean ismember;

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getNumberRiders() {
        return numberRiders;
    }

    public void setNumberRiders(String numberRiders) {
        this.numberRiders = numberRiders;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isGPS() {
        return GPS;
    }

    public void setGPS(boolean GPS) {
        this.GPS = GPS;
    }

    public boolean isSirius() {
        return Sirius;
    }

    public void setSirius(boolean sirius) {
        Sirius = sirius;
    }

    public boolean isOnStart() {
        return OnStart;
    }

    public void setOnStart(boolean onStart) {
        OnStart = onStart;
    }

    public boolean isIsmember() {
        return ismember;
    }

    public void setIsmember(boolean ismember) {
        this.ismember = ismember;
    }
}
