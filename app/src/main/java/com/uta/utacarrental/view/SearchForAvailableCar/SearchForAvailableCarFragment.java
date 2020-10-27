package com.uta.utacarrental.view.SearchForAvailableCar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;


import com.uta.utacarrental.model.Car;
import com.uta.utacarrental.model.Reservation;

import com.uta.utacarrental.R;

import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchForAvailableCarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchForAvailableCarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView starttime;
    private TextView endtime;
    private TextView capacity;
    private TextView endtext;
    private TextView starttext;
    private TextView capacitytext;

    public SearchForAvailableCarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchForAvailableCar.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchForAvailableCarFragment newInstance(String param1, String param2) {
        SearchForAvailableCarFragment fragment = new SearchForAvailableCarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_search_for_available_car, container, false);

        starttime = (TextView) root.findViewById(R.id.start_time);
        endtime = (TextView) root.findViewById(R.id.end_time);
        capacity = (TextView) root.findViewById(R.id.capacity);

        String strMsg = "<font color=\"#00ff00\">Click here to select a start time</font>";
        starttime.setText(Html.fromHtml(strMsg));
        strMsg = "<font color=\"#00ff00\">Click here to select a end time</font>";
        endtime.setText(Html.fromHtml(strMsg));

        starttime = (TextView) root.findViewById(R.id.start_time);
        //为TextView设置点击事件
        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将timeText传入用于显示所选择的时间
                showDialogPick((TextView) v);
            }
        });

        endtime = (TextView) root.findViewById(R.id.end_time);
        //为TextView设置点击事件
        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将timeText传入用于显示所选择的时间
                showDialogPick((TextView) v);
            }
        });

        return root;
    }
    private void showDialogPick(final TextView timeText) {
        final StringBuffer time = new StringBuffer();
        //获取Calendar对象，用于获取当前时间
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //实例化TimePickerDialog对象
        final TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT,new TimePickerDialog.OnTimeSetListener() {
            //选择完时间后会调用该回调函数
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //time.append(" "  + hourOfDay + ":" + minute);

                time.append(" ");
                if (hourOfDay<=9){
                    time.append("0"+hourOfDay+":");
                }else{
                    time.append(hourOfDay+":");
                }
                if (minute<=9){
                    time.append("0"+minute);
                }else{
                    time.append(minute);
                }

                //设置TextView显示最终选择的时间
                timeText.setText(time);
            }
        }, hour, minute, false);
        timePickerDialog.setTitle("pick");
        //实例化DatePickerDialog对象
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT,new DatePickerDialog.OnDateSetListener() {
            //选择完日期后会调用该回调函数
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //因为monthOfYear会比实际月份少一月所以这边要加1
                //time.append(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);

                time.append(year + "-");
                if (monthOfYear+1<10){
                    time.append("0"+(monthOfYear+1)+"-");
                }else{
                    time.append((monthOfYear+1)+"-");
                }
                if (dayOfMonth<=9){
                    time.append("0"+dayOfMonth);
                }else{
                    time.append(dayOfMonth);
                }

                //选择完日期后弹出选择时间对话框
                timePickerDialog.show();
            }
        }, year, month, day);
        //弹出选择日期对话框
        datePickerDialog.show();
    }
    void generateReservations(){

        Reservation reservation = new Reservation();
        reservation.setReservationNumber(1111);
        Car car = LitePal.where("carName = ?","smart").findFirst(Car.class);
        List<Reservation> res_list =  car.getReservationList();
        res_list.add(reservation);
        car.setReservationList(res_list);
        car.save();
        String st = "2020-10-25 10:00";
        String et = "2020-10-26 10:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date sd = format.parse(st);
            Date ed = format.parse(et);
            reservation.setStartTime(sd);
            reservation.setEndTime(ed);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        reservation.save();

        Reservation reservation1 = new Reservation();
        reservation1.setReservationNumber(2222);
        Car car1 = LitePal.where("carName = ?","economy").findFirst(Car.class);
        res_list =  car1.getReservationList();
        res_list.add(reservation1);
        car1.setReservationList(res_list);
        car1.save();
        st = "2020-10-23 10:00";
        et = "2020-10-24 10:00";
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date sd = format.parse(st);
            Date ed = format.parse(et);
            reservation1.setStartTime(sd);
            reservation1.setEndTime(ed);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        reservation1.save();

        Reservation reservation2 = new Reservation();
        reservation2.setReservationNumber(3333);
        Car car2 = LitePal.where("carName = ?","compact").findFirst(Car.class);
        res_list =  car2.getReservationList();
        res_list.add(reservation2);
        car2.setReservationList(res_list);
        car2.save();
        st = "2020-10-27 10:00";
        et = "2020-10-28 10:00";
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date sd = format.parse(st);
            Date ed = format.parse(et);
            reservation2.setStartTime(sd);
            reservation2.setEndTime(ed);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        reservation2.save();
    }
}