package com.uta.utacarrental.view.reservation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Car;
import com.uta.utacarrental.model.Reservation;
import com.uta.utacarrental.model.User;

import org.litepal.LitePal;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class ModifyReservationActivity extends AppCompatActivity {

    boolean gps;
    boolean sirius;
    boolean onStar;
    int riderNumber;
    double totalCost;

    Reservation reservation;
    Car car;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_reservation);
        setTitle("Modify Reservation");
        //Toolbar toolbar = findViewById(R.id.modify_reservation_toolbar);
        //toolbar.setTitle("Modify Reservation");
        //setSupportActionBar(toolbar);
        //ActionBar actionBar = getSupportActionBar();
        //if (actionBar != null) {
        //    actionBar.setDisplayHomeAsUpEnabled(true);
        //}

        Intent intent = getIntent();
        reservation = (Reservation) intent.getSerializableExtra("reservation");
        car = reservation.getCar();
        user=(User) intent.getSerializableExtra("user");


        //TextView reservationNumberTv = findViewById(R.id.reservation_number);
        //reservationNumberTv.setText(String.valueOf(reservation.getReservationNumber()));
        //TextView carNumberTv = findViewById(R.id.reservation_car_number);
        //carNumberTv.setText(String.valueOf(car.getCarNumber()));
        //TextView carNameTv = findViewById(R.id.reservation_car_name);
        //carNameTv.setText(car.getCarName());
        //TextView carCapTv = findViewById(R.id.reservation_car_capacity);
        //carCapTv.setText(String.valueOf(car.getCapacity()));
        //SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //TextView startTimeTv = findViewById(R.id.reservation_start_time);
        //startTimeTv.setText(dateTimeFormat.format(reservation.getStartTime()));
        //TextView endTimeTv = findViewById(R.id.reservation_end_time);
        //endTimeTv.setText(dateTimeFormat.format(reservation.getEndTime()));
        //TextView reservationTimeTv = findViewById(R.id.reservation_time);
        //reservationTimeTv.setText(dateTimeFormat.format(reservation.getReservationTime()));
        //TextView riderNumberTv = findViewById(R.id.reservation_riders_number);
        //riderNumberTv.setText(String.valueOf(reservation.getRiderNumber()));
        //TextView totalCostTv = findViewById(R.id.reservation_total_cost);
        //totalCostTv.setText(String.valueOf(reservation.getTotalCost()));
        //TextView gpsTv = findViewById(R.id.reservation_gps_selected);
        //gpsTv.setText(String.valueOf(reservation.isGps()));
        //TextView siriusTv = findViewById(R.id.reservation_sirius_selected);
        //siriusTv.setText(String.valueOf(reservation.isSirius()));
        //TextView onStarTv = findViewById(R.id.reservation_onstar_selected);
        //onStarTv.setText(String.valueOf(reservation.isOnStart()));
        //TextView clubMemberTv = findViewById(R.id.reservation_club_member);
        //clubMemberTv.setText(String.valueOf(reservation.isMember()));

        ((TextView)findViewById(R.id.modify_reservation_number)).setText(String.valueOf(reservation.getReservationNumber()));
        ((TextView)findViewById(R.id.modify_reservation_car_number)).setText(String.valueOf(car.getCarNumber()));
        ((TextView)findViewById(R.id.modify_reservation_car_name)).setText(car.getCarName());
        ((TextView)findViewById(R.id.modify_reservation_car_capacity)).setText(String.valueOf(car.getCapacity()));
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ((TextView)findViewById(R.id.modify_reservation_start_time)).setText(dateTimeFormat.format(reservation.getStartTime()));
        ((TextView)findViewById(R.id.modify_reservation_end_time)).setText(dateTimeFormat.format(reservation.getEndTime()));
        ((TextView)findViewById(R.id.modify_reservation_time)).setText(dateTimeFormat.format(reservation.getReservationTime()));

        ((EditText)findViewById(R.id.modify_number_of_riders)).setText(String.valueOf(reservation.getRiderNumber()));

        ((TextView)findViewById(R.id.modify_reservation_club_member)).setText(String.valueOf(reservation.isMember()));


        Spinner GPS_selected = (Spinner)findViewById(R.id.modify_GPS_selected);
        Spinner on_star_selected = (Spinner)findViewById(R.id.modify_on_star_selected);
        Spinner sirius_xm_selected = (Spinner)findViewById(R.id.modify_sirius_xm_selected);

        if (reservation.isGps()){
            GPS_selected.setSelection(0);
        }else {
            GPS_selected.setSelection(1);
        }

        if (reservation.isOnStart()){
            on_star_selected.setSelection(0);
        }else {
            on_star_selected.setSelection(1);
        }

        if (reservation.isSirius()){
            sirius_xm_selected.setSelection(0);
        }else {
            sirius_xm_selected.setSelection(1);
        }

        AdapterView.OnItemSelectedListener listener=new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                totalCost=totalCost();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        };

        GPS_selected.setOnItemSelectedListener(listener);
        on_star_selected.setOnItemSelectedListener(listener);
        sirius_xm_selected.setOnItemSelectedListener(listener);



    }

    public Double totalCost(){
        //Date st=new Date();
        //Date et=new Date();
        Calendar scalendar = new GregorianCalendar();
        Calendar ecalendar = new GregorianCalendar();
        double sum=0;
        double GPSRate=0;
        double onStarRate=0;
        double siriusXMRate=0;


        if("yes".equals((String) ((Spinner) findViewById(R.id.modify_GPS_selected)).getSelectedItem())){
            gps=true;
            GPSRate=car.getGPSate();
        }else {
            gps=false;
            GPSRate=0;
        }
        if("yes".equals((String) ((Spinner) findViewById(R.id.modify_on_star_selected)).getSelectedItem())){
            onStar=true;
            onStarRate=car.getOnStartRate();
        }else {
            onStar=false;
            onStarRate=0;
        }
        if("yes".equals((String) ((Spinner) findViewById(R.id.modify_sirius_xm_selected)).getSelectedItem())){
            sirius=true;
            siriusXMRate=car.getSiriusXMRate();
        }else {
            sirius=false;
            siriusXMRate=0;
        }


        scalendar.setTime(reservation.getStartTime());
        ecalendar.setTime(reservation.getEndTime());

        int days = (int) ((reservation.getEndTime().getTime() - reservation.getStartTime().getTime()) / (1000*3600*24));
        int weeks=days/7;
        if(weeks>0){
            scalendar.add(scalendar.DATE,weeks*7);
            sum=sum+weeks*car.getWeekRate();
            sum=sum+weeks*(GPSRate+onStarRate+siriusXMRate)*7;
        }

        Calendar tempcalendar;
        boolean isWeekday=true;
        while (true){
            if(scalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || scalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                isWeekday=false;
            }

            tempcalendar=scalendar;

            tempcalendar.add(scalendar.DATE,1); //把日期往后增加一天,整数往后推,负数往前移动

            if(tempcalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || tempcalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                isWeekday=false;
            }

            //加钱
            if (isWeekday){
                sum=sum+car.getWeekdayRate();
            }else {
                sum=sum+car.getWeekendRate();
            }
            sum=sum+GPSRate+onStarRate+siriusXMRate;

            if(scalendar.after(ecalendar)||scalendar.compareTo(ecalendar)==0){
                break;
            }

            scalendar=tempcalendar;

            isWeekday=true;

        }

        //Calendar calendar = new GregorianCalendar();
        //calendar.setTime(st);
        //calendar.add(calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动
        //st=calendar.getTime(); //这个时间就是日期往后推一天的结果

        if(user.isIsmember()){
            sum=sum*0.9;
        }

        sum=sum*1.0825;

        //保留两位小数，去掉尾数
        BigDecimal b = new BigDecimal(sum);
        sum = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

        ((TextView)findViewById(R.id.modify_reservation_total_cost)).setText(String.valueOf(sum));

        return sum;
    }

    public void save(View view){

        riderNumber=Integer.parseInt(((EditText) findViewById(R.id.modify_number_of_riders)).getText().toString().trim());
        if(riderNumber>car.getCapacity()){
            Toast.makeText(getApplicationContext(), "Invalid Number of Riders", Toast.LENGTH_SHORT).show();
            return;
        }

        reservation.setRiderNumber(riderNumber);
        reservation.setTotalCost(totalCost);
        reservation.setGps(gps);
        reservation.setSirius(sirius);
        reservation.setOnStart(onStar);

        //Reservation r=reservation;
        //
        //r.update(reservation.getId());

        //reservation.update(reservation.getId());
        ContentValues roomValues = new ContentValues();
        roomValues.put("ridernumber",riderNumber);
        roomValues.put("totalcost",totalCost);
        roomValues.put("gps",gps);
        roomValues.put("sirius",sirius);
        roomValues.put("onstart",onStar);

        LitePal.update(Reservation.class,roomValues,reservation.getId());
        Toast.makeText(getApplicationContext(), "Reservation modified successfully", Toast.LENGTH_SHORT).show();



    }

}