package com.uta.utacarrental.view.reservation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReservationFragment extends Fragment {

    private ReservationViewModel reservationViewModel;
    private TextView tvFromDate;
    private TextView tvToDate;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final Activity activity = getActivity();
        assert activity != null;
        reservationViewModel =
                ViewModelProviders.of(this).get(ReservationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reservation, container, false);

        tvFromDate = root.findViewById(R.id.tv_from_date);
        tvToDate = root.findViewById(R.id.tv_to_date);
        listView = root.findViewById(R.id.reservation_list);

        SimpleDateFormat zeroDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd 00:00");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        tvFromDate.setText(zeroDateTimeFormat.format(new Date()));
        tvToDate.setText(dateTimeFormat.format(new Date()));

        tvFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将timeText传入用于显示所选择的时间
                showDialogPick((TextView) v);
            }
        });

        tvToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将timeText传入用于显示所选择的时间
                showDialogPick((TextView) v);
            }
        });
        updateReservationList();
        return root;
    }

    private void updateReservationList() {
        final Activity activity = getActivity();
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date fromDate = new Date();
        Date toDate = new Date();
        try {
            fromDate = dateTimeFormat.parse((String) tvFromDate.getText());
            toDate = dateTimeFormat.parse((String) tvToDate.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservationViewModel.getListData(fromDate,toDate).observe(getViewLifecycleOwner(), new Observer<List<Reservation>>() {
            @Override
            public void onChanged(final List<Reservation> reservations) {
                listView.setAdapter(new ReservationAdapter(activity, reservations));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(activity, ReservationDetailActivity.class);
                        intent.putExtra("reservationID", reservations.get(position).getId());
                        startActivity(intent);
                    }
                });
            }
        });
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

                System.out.println("After set to date");
                // update the reservation list
                updateReservationList();

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
}