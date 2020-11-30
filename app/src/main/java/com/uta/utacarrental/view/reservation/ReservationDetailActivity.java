package com.uta.utacarrental.view.reservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Car;
import com.uta.utacarrental.model.Reservation;
import com.uta.utacarrental.model.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.List;

public class ReservationDetailActivity extends AppCompatActivity {

    Reservation reservation;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_detail);

        Toolbar toolbar = findViewById(R.id.reservation_detail_toolbar);
        toolbar.setTitle("Reservation Detail");
        //setTitle("Modify Reservation");

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();

        user = (User) intent.getSerializableExtra("user");

        //角色为user时显示修改订单按钮
        if ("user".equals(user.getRole())) {
            findViewById(R.id.modify_reservation_button).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.modify_reservation_button).setVisibility(View.GONE);
        }

        //角色为user或rental manager时显示删除订单按钮
        if ("user".equals(user.getRole()) || "rental manager".equals(user.getRole())) {
            findViewById(R.id.delete_reservation_button).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.delete_reservation_button).setVisibility(View.GONE);
        }

        final long reservationID = intent.getLongExtra("reservationID",0);
        Reservation reservation = LitePal.find(Reservation.class, reservationID);
        if (reservation != null) {
            Car car = reservation.getCar();
            TextView reservationNumberTv = findViewById(R.id.reservation_number);
            reservationNumberTv.setText(String.valueOf(reservation.getReservationNumber()));
            TextView carNumberTv = findViewById(R.id.reservation_car_number);
            carNumberTv.setText(String.valueOf(car.getCarNumber()));
            TextView carNameTv = findViewById(R.id.reservation_car_name);
            carNameTv.setText(car.getCarName());
            TextView carCapTv = findViewById(R.id.reservation_car_capacity);
            carCapTv.setText(String.valueOf(car.getCapacity()));
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            TextView startTimeTv = findViewById(R.id.reservation_start_time);
            if (reservation.getStartTime() != null) {
                startTimeTv.setText(dateTimeFormat.format(reservation.getStartTime()));
            }
            TextView endTimeTv = findViewById(R.id.reservation_end_time);
            if (reservation.getEndTime() != null) {
                endTimeTv.setText(dateTimeFormat.format(reservation.getEndTime()));
            }
            TextView reservationTimeTv = findViewById(R.id.reservation_time);
            if (reservation.getReservationTime() != null) {
                reservationTimeTv.setText(dateTimeFormat.format(reservation.getReservationTime()));
            }
            TextView riderNumberTv = findViewById(R.id.reservation_riders_number);
            riderNumberTv.setText(String.valueOf(reservation.getRiderNumber()));
            TextView totalCostTv = findViewById(R.id.reservation_total_cost);
            totalCostTv.setText(String.valueOf(reservation.getTotalCost()));
            TextView gpsTv = findViewById(R.id.reservation_gps_selected);
            gpsTv.setText(String.valueOf(reservation.isGps()));
            TextView siriusTv = findViewById(R.id.reservation_sirius_selected);
            siriusTv.setText(String.valueOf(reservation.isSirius()));
            TextView onStarTv = findViewById(R.id.reservation_onstar_selected);
            onStarTv.setText(String.valueOf(reservation.isOnStart()));
            TextView clubMemberTv = findViewById(R.id.reservation_club_member);
            clubMemberTv.setText(String.valueOf(reservation.isMember()));
        }

            Button deleteButton = findViewById(R.id.delete_reservation_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteReservation(reservationID);
                }
            });

        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void modify(View view){
        Intent intent=new Intent();

        Bundle bundle=new Bundle();
        bundle.putSerializable("reservation", reservation);
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);

        intent.setClass(this, ModifyReservationActivity.class);
        startActivity(intent);
    }

    public void deleteReservation(long reservationID) {
        LitePal.delete(Reservation.class, reservationID);
        Intent intent = new Intent();
        intent.setClass(this, ReservationFragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}