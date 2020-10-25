package com.uta.utacarrental.view.search_for_car;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Car;

import java.util.List;

public class SearchForCar extends AppCompatActivity {
    Intent intent = this.getIntent();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_car_list);
        setTitle("Car summary");
        ListView listView = (ListView) findViewById(R.id.car_listview);
        List<Car> car_list = (List<Car>)getIntent().getSerializableExtra("car_list");
        com.uta.utacarrental.view.search_for_car.CarListAdapter adapter = new com.uta.utacarrental.view.search_for_car.CarListAdapter(SearchForCar.this, R.layout.list_car_item,car_list);
        listView.setAdapter(adapter);

     }
}