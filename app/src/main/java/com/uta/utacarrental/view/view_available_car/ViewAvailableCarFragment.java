package com.uta.utacarrental.view.view_available_car;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Car;
import com.uta.utacarrental.model.Reservation;
import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewAvailableCarFragment extends Fragment {

    private ViewAvailableCarModel viewAvailableCarModel;

    private Context context;
    private CalendarView calendarView;
    private TextView starttime;
    private TextView endtime;
    private TextView endtext;
    private TextView starttext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewAvailableCarModel =
                ViewModelProviders.of(this).get(ViewAvailableCarModel.class);
        View root = inflater.inflate(R.layout.fragment_view_available_car, container, false);

        //generateReservations();

//        calendarView = (CalendarView)(root.findViewById(R.id.calendar));
//        calendarView.setVisibility(View.INVISIBLE);
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            public void onSelectedDayChange(CalendarView view, int year, int month,
//                                            int dayOfMonth) {
//                String content = year+"-"+(month+1)+"-"+dayOfMonth;
//                Toast.makeText(getContext(), "你选择了:\n"+content, Toast.LENGTH_SHORT).show();
//                System.out.println("你选择了:\n"+content);
//            }
//        });
        starttime = (TextView) root.findViewById(R.id.start_time);
        endtime = (TextView) root.findViewById(R.id.end_time);

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