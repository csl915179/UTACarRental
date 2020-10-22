package com.uta.utacarrental.view.reservation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

        // reservation list data
//        Reservation reservation1 = new Reservation();
//        reservation1.setReservationNumber(1);
//        reservation1.setReservationTime(new Date());
//        reservation1.save();
//        Reservation reservation2 = new Reservation();
//        reservation2.setReservationNumber(2);
//        reservation2.setReservationTime(new Date());
//        reservation2.save();
//        Reservation reservation3 = new Reservation();
//        reservation3.setReservationNumber(3);
//        reservation3.setReservationTime(new Date());
//        reservation3.save();
//        Reservation reservation4 = new Reservation();
//        reservation4.setReservationNumber(4);
//        reservation4.setReservationTime(new Date());
//        reservation4.save();
        List<Reservation> reservations = LitePal.findAll(Reservation.class);
        reservationList.setValue(reservations);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Reservation>> getListData() {
        return reservationList;
    }
}