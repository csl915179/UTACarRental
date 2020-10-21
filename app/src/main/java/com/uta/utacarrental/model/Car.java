package com.uta.utacarrental.model;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class Car extends LitePalSupport implements Serializable {
    @Column(unique = true)
    private String carName;
    private int carNumber;
    private int capacity;
    private boolean carStatus;
    private double weekdayRate;
    private double weekendRate;
    private double weekRate;
    private double GPSrate;
    private double OnStartRate;
    private double SiriusXMRate;

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

    public boolean isCarStatus() {
        return carStatus;
    }

    public void setCarStatus(boolean carStatus) {
        this.carStatus = carStatus;
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

    public double getGPSrate() {
        return GPSrate;
    }

    public void setGPSrate(double GPSrate) {
        this.GPSrate = GPSrate;
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




}
