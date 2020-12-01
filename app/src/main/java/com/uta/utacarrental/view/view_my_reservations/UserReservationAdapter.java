package com.uta.utacarrental.view.view_my_reservations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Reservation;

import java.util.List;

public class UserReservationAdapter extends ArrayAdapter {
    private final int ImageId;
    public UserReservationAdapter(Context context, int headImage, List<Reservation> obj){
        super(context,headImage,obj);
        ImageId = headImage;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Reservation reservation = (Reservation) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(ImageId,parent,false);
        LinearLayout linearLayout = view.findViewById(R.id.user_reservation_summary_view);
        final TextView carnnumber = view.findViewById(R.id.reservation_car_number);
        TextView carnname = view.findViewById(R.id.reservation_car_name);
        TextView carcapacity = view.findViewById(R.id.reservation_car_capacity);
        TextView starttime = view.findViewById(R.id.reservation_start_time);
        TextView endtime = view.findViewById(R.id.reservation_end_time);

        carnnumber.setText(Integer.toString(reservation.getCar().getCarNumber()));
        carnname.setText(reservation.getCar().getCarName());
        carcapacity.setText(Integer.toString(reservation.getCar().getCapacity()));
        starttime.setText(reservation.getStartTime().toString());
        endtime.setText(reservation.getEndTime().toString());


        return view;
    }
}
