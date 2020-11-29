package com.uta.utacarrental.view.UserCarSummary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Car;
import com.uta.utacarrental.view.homepage.UserHomepage;

import java.util.List;

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
import com.uta.utacarrental.model.Car;
import com.uta.utacarrental.view.view_car_details.ViewCarDetails;

import java.util.List;

public class UserCarListAdapter extends ArrayAdapter {
    private final int ImageId;
    public UserCarListAdapter(Context context, int headImage, List<Car> obj){
        super(context,headImage,obj);
        ImageId = headImage;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Car car = (Car) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(ImageId,parent,false);
        LinearLayout linearLayout = view.findViewById(R.id.user_car_summary_view);
        final TextView carname = view.findViewById(R.id.car_name);
        TextView carnumber = view.findViewById(R.id.car_number);
        TextView capacity = view.findViewById(R.id.car_capacity);
        TextView weekdayRate = view.findViewById(R.id.car_weekdayRate);
        TextView weekendRate = view.findViewById(R.id.car_weekendRate);
        TextView weekRate = view.findViewById(R.id.car_weekRate);
//        TextView GPSrate = view.findViewById(R.id.car_GPSrate);
//        TextView OnStartRate = view.findViewById(R.id.car_OnStartRate);
//        TextView SiriusXMRate = view.findViewById(R.id.car_SiriusXMRate);


        carname.setText("Car Name: "+car.getCarName());
        carnumber.setText("Car Number: "+car.getCarNumber());
        capacity.setText("Car Capacity: "+car.getCapacity());
        weekdayRate.setText("Car WeekdayRate: "+car.getWeekdayRate());
        weekendRate.setText("Car WeekendRate: "+car.getWeekendRate());
        weekRate.setText("Car WeekRate: "+car.getWeekRate());
//        GPSrate.setText("Car GPSrate: "+car.getGPSrate());
//        OnStartRate.setText("Car OnStartRate: "+car.getOnStartRate());
//        SiriusXMRate.setText("Car SiriusXMRate: "+car.getSiriusXMRate());


        return view;
    }

}