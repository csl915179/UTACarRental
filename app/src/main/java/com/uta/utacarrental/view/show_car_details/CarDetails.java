package com.uta.utacarrental.view.show_car_details;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Car;

public class CarDetails extends AppCompatActivity {
    Intent intent = this.getIntent();
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_car_details);
        Car car = (Car)getIntent().getSerializableExtra("car");
        setTitle(car.getCarName());
        TextView car_name = findViewById(R.id.details_car_name);
        TextView car_number = findViewById(R.id.details_car_number);
        TextView car_capacity = findViewById(R.id.details_car_capacity);
        TextView weekday_rate = findViewById(R.id.details_weekday_rate);
        TextView week_rate = findViewById(R.id.details_week_rate);
        TextView weekend_rate = findViewById(R.id.details_weekend_rate);
        TextView GPS_rate = findViewById(R.id.details_GPS_rate);
        TextView On_Star_rate = findViewById(R.id.details_on_star_rate);
        TextView Sirius_rate = findViewById(R.id.details_sirius_XM_rate);

        car_name.setText(car.getCarName());
        car_number.setText(car.getCarNumber()+"");
        car_capacity.setText(car.getCapacity()+"");
        weekday_rate.setText(car.getWeekdayRate()+"");
        week_rate.setText(car.getWeekRate()+"");
        weekend_rate.setText(car.getWeekendRate()+"");
        GPS_rate.setText(car.getGPSate()+"");
        On_Star_rate.setText(car.getOnStartRate()+"");
        Sirius_rate.setText(car.getSiriusXMRate()+"");




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
}
