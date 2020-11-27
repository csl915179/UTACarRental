package com.uta.utacarrental.view.view_car_details;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Car;
import com.uta.utacarrental.model.Reservation;
import com.uta.utacarrental.model.User;
import com.uta.utacarrental.view.reservation.ReservationDetailActivity;

import org.litepal.LitePal;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;


public class ViewCarDetails extends AppCompatActivity {

    Car car;
    User user;
    Date st;
    Date et;
    double totalCost;
    boolean gps;
    boolean sirius;
    boolean onStar;
    int riderNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_car_details);
        setTitle("Car Detail");
        //Toolbar toolbar = findViewById(R.id.details_toolbar);
        //toolbar.setTitle("Car Details");
        //
        //setSupportActionBar(toolbar);
        //ActionBar actionBar = getSupportActionBar();
        //if (actionBar != null) {
        //    actionBar.setDisplayHomeAsUpEnabled(true);
        //}

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        st = (Date) intent.getSerializableExtra("st");
        //System.out.println(st);
        et = (Date) intent.getSerializableExtra("et");

        //System.out.println(et);
        String carName = intent.getStringExtra("carName");
        List<Car> carList = LitePal.where("carName = ?", String.valueOf(carName)).find(Car.class);

        if (!carList.isEmpty()) {
            car = carList.get(0);

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

            ((TextView)findViewById(R.id.details_car_name)).setText(String.valueOf(car.getCarName()));
            ((TextView)findViewById(R.id.details_car_number)).setText(String.valueOf(car.getCarNumber()));
            ((TextView)findViewById(R.id.details_car_capacity)).setText(String.valueOf(car.getCapacity()));
            ((TextView)findViewById(R.id.details_weekday_rate)).setText(String.valueOf(car.getWeekdayRate()));
            ((TextView)findViewById(R.id.details_weekend_rate)).setText(String.valueOf(car.getWeekendRate()));
            ((TextView)findViewById(R.id.details_week_rate)).setText(String.valueOf(car.getWeekRate()));
            ((TextView)findViewById(R.id.details_GPS_rate)).setText(String.valueOf(car.getGPSate()));
            ((TextView)findViewById(R.id.details_on_star_rate)).setText(String.valueOf(car.getOnStartRate()));
            ((TextView)findViewById(R.id.details_sirius_XM_rate)).setText(String.valueOf(car.getSiriusXMRate()));


            Spinner GPS_selected = (Spinner)findViewById(R.id.GPS_selected);

            GPS_selected.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    totalCost=totalCost();
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });

            Spinner on_star_selected = (Spinner)findViewById(R.id.on_star_selected);

            on_star_selected.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    totalCost=totalCost();
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });

            Spinner sirius_xm_selected = (Spinner)findViewById(R.id.sirius_xm_selected);

            sirius_xm_selected.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    totalCost=totalCost();
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
        }

        Button reserveButton= (Button)findViewById(R.id.details_reserve);
        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserve();
            }
        });



    }

    //public Double totalCost(){
    //    Date st=new Date();
    //    Date et=new Date();
    //    double sum=0;
    //
    //
    //    int days = (int) ((st.getTime() - et.getTime()) / (1000*3600*24));
    //    int weeks=days%7;
    //    Calendar calendar = new GregorianCalendar();
    //    if(weeks>0){
    //        calendar.setTime(st);
    //        calendar.add(calendar.DATE,weeks*7);
    //        sum=sum+weeks*car.getWeekRate();
    //    }
    //
    //    Date temp;
    //    boolean isWeekday=true;
    //    while (true){
    //
    //        calendar.setTime(st);
    //
    //        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
    //            isWeekday=false;
    //        }
    //
    //        calendar.add(calendar.DATE,1); //把日期往后增加一天,整数往后推,负数往前移动
    //
    //        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
    //            isWeekday=false;
    //        }
    //
    //        temp=calendar.getTime(); //这个时间就是日期往后推一天的结果
    //
    //        //加钱
    //        if (isWeekday){
    //            sum=sum+car.getWeekdayRate();
    //        }else {
    //            sum=sum+car.getWeekendRate();
    //        }
    //
    //        if(st.after(et)||st.compareTo(et)==0){
    //            break;
    //        }
    //
    //        st=temp;
    //        isWeekday=true;
    //
    //    }
    //
    //    //Calendar calendar = new GregorianCalendar();
    //    //calendar.setTime(st);
    //    //calendar.add(calendar.DATE,1); //把日期往后增加一天,整数  往后推,负数往前移动
    //    //st=calendar.getTime(); //这个时间就是日期往后推一天的结果
    //
    //
    //    return sum;
    //}

    public Double totalCost(){
        //Date st=new Date();
        //Date et=new Date();
        Calendar scalendar = new GregorianCalendar();
        Calendar ecalendar = new GregorianCalendar();
        double sum=0;
        double GPSRate=0;
        double onStarRate=0;
        double siriusXMRate=0;


        if("yes".equals((String) ((Spinner) findViewById(R.id.GPS_selected)).getSelectedItem())){
            gps=true;
            GPSRate=car.getGPSate();
        }else {
            gps=false;
            GPSRate=0;
        }
        if("yes".equals((String) ((Spinner) findViewById(R.id.on_star_selected)).getSelectedItem())){
            onStar=true;
            onStarRate=car.getOnStartRate();
        }else {
            onStar=false;
            onStarRate=0;
        }
        if("yes".equals((String) ((Spinner) findViewById(R.id.sirius_xm_selected)).getSelectedItem())){
            sirius=true;
            siriusXMRate=car.getSiriusXMRate();
        }else {
            sirius=false;
            siriusXMRate=0;
        }


        scalendar.setTime(st);
        ecalendar.setTime(et);

        int days = (int) ((et.getTime() - st.getTime()) / (1000*3600*24));
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

        ((TextView)findViewById(R.id.details_total_cost)).setText(String.valueOf(sum));

        return sum;
    }



    public void reserve(){

        riderNumber=Integer.parseInt(((EditText) findViewById(R.id.details_number_of_riders)).getText().toString().trim());
        if(riderNumber>car.getCapacity()){
            Toast.makeText(getApplicationContext(), "Invalid Number of Riders", Toast.LENGTH_SHORT).show();
            return;
        }
        Reservation reservation=new Reservation();
        Random random=new Random();
        reservation.setReservationNumber(random.nextInt(50000));
        reservation.setFirstName(user.getFirstname());
        reservation.setLastName(user.getLastname());
        reservation.setReservationTime(new Date());
        reservation.setStartTime(st);
        reservation.setEndTime(et);
        reservation.setRiderNumber(riderNumber);
        reservation.setTotalCost(totalCost);
        reservation.setGps(gps);
        reservation.setSirius(sirius);
        reservation.setOnStart(onStar);
        reservation.setMember(user.isIsmember());
        reservation.setCar_id(car.getCarNumber());//没有Long类型的car_id
        //reservation.setUser_id();  没有user_id
        reservation.save();

        Intent intent=new Intent();


        Bundle bundle=new Bundle();
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);

        intent.putExtra("reservationNumber",reservation.getReservationNumber());
        intent.setClass(this, ReservationDetailActivity.class);
        startActivity(intent);

    }

}