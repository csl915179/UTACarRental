package com.uta.utacarrental.model;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Car extends LitePalSupport implements Serializable {
    @Column(unique = true)
    private String carName;
    private int carNumber;
    private int capacity;
    private double weekdayRate;
    private double weekendRate;
    private double weekRate;
    private double GPSate;
    private double OnStartRate;
    private double SiriusXMRate;
    private List<Reservation> reservationList = new ArrayList<Reservation>();

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getWeekdayRate() {
        return weekdayRate;
    }

    public void setWeekdayRate(double weekdayRate) {
        this.weekdayRate = weekdayRate;
    }

    public double getWeekendRate() {
        return weekendRate;
    }

    public void setWeekendRate(double weekendRate) {
        this.weekendRate = weekendRate;
    }

    public double getWeekRate() { return weekRate; }

    public void setWeekRate(double weekRate) { this.weekRate = weekRate; }

    public double getGPSrate() {
        return GPSrate;
    }

    public double getGPSate() {
        return GPSate;
    }

    public void setGPSate(double GPSate) {
        this.GPSate = GPSate;
    }

    public double getOnStartRate() {
        return OnStartRate;
    }

    public void setOnStartRate(double onStartRate) {
        OnStartRate = onStartRate;
    }

    public double getSiriusXMRate() {
        return SiriusXMRate;
    }

    public void setSiriusXMRate(double siriusXMRate) {
        SiriusXMRate = siriusXMRate;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
