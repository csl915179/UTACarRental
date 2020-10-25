package com.uta.utacarrental.view.reservation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.uta.utacarrental.model.Car;
import com.uta.utacarrental.model.Reservation;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<Reservation>> reservationList;

    public ReservationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is reservation fragment");
        reservationList = new MutableLiveData<>();
//        makeMockReservation();
        List<Reservation> reservations = LitePal.findAll(Reservation.class);
        reservationList.setValue(reservations);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Reservation>> getListData() {
        return reservationList;
    }

    public void makeMockReservation() {
        // reservation list data
//        Car car1 = new Car();
//        car1.setCarName("Car1");
//        car1.setCarNumber(1);
//        car1.setCapacity(5);
//        car1.save();
//        Car car2 = new Car();
//        car2.setCarName("Car2");
//        car2.setCarNumber(2);
//        car2.setCapacity(7);
//        car2.save();

        Reservation reservation1 = new Reservation();
        reservation1.setReservationNumber(1);
        reservation1.setReservationTime(new Date());
        reservation1.setStartTime(new Date());
        reservation1.setEndTime(new Date());
        reservation1.setCar_id(1);
        reservation1.save();
        Reservation reservation2 = new Reservation();
        reservation2.setReservationNumber(2);
        reservation2.setReservationTime(new Date());
        reservation2.setStartTime(new Date());
        reservation2.setEndTime(new Date());
        reservation2.setCar_id(1);
        reservation2.save();
        Reservation reservation3 = new Reservation();
        reservation3.setReservationNumber(3);
        reservation3.setReservationTime(new Date());
        reservation3.setStartTime(new Date());
        reservation3.setEndTime(new Date());
        reservation3.setCar_id(2);
        reservation3.save();
        Reservation reservation4 = new Reservation();
        reservation4.setReservationNumber(4);
        reservation4.setReservationTime(new Date());
        reservation4.setStartTime(new Date());
        reservation4.setEndTime(new Date());
        reservation4.setCar_id(2);
        reservation4.save();
    }
}