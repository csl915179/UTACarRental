package com.uta.utacarrental.view.view_available_car;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Car;
import com.uta.utacarrental.view.search_for_car.CarListAdapter;
import com.uta.utacarrental.view.search_for_car.SearchForCar;
import com.uta.utacarrental.view.show_car_details.CarDetails;

import java.io.Serializable;
import java.util.List;

public class ViewAvailableCar extends AppCompatActivity {
    Intent intent = this.getIntent();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_car_list);
        setTitle("Car summary");
        ListView listView = (ListView) findViewById(R.id.car_listview);
        final List<Car> car_list = (List<Car>)getIntent().getSerializableExtra("car_list");
        final CarListAdapter adapter = new CarListAdapter(ViewAvailableCar.this, R.layout.list_car_item,car_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car car = car_list.get(position);
                Toast.makeText(ViewAvailableCar.this,car.getCarName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("car",(Serializable)car);
                intent.setClass(ViewAvailableCar.this, CarDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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
}