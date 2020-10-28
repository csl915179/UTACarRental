package com.uta.utacarrental.view.UserCarSummary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.uta.utacarrental.R;
import com.uta.utacarrental.view.search_for_car.SearchForCar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.uta.utacarrental.model.Car;
import com.uta.utacarrental.model.User;
import com.uta.utacarrental.view.view_car_details.ViewCarDetails;

public class UserCarSummary extends AppCompatActivity {
    Intent intent = this.getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_car_list);
        setTitle("Car summary");
        ListView listView = (ListView) findViewById(R.id.car_listview);
        List<Car> car_list = (List<Car>)getIntent().getSerializableExtra("car_list");
        com.uta.utacarrental.view.UserCarSummary.UserCarListAdapter adapter = new com.uta.utacarrental.view.UserCarSummary.UserCarListAdapter(UserCarSummary.this, R.layout.fragment_user_car_summary,car_list);
        listView.setAdapter(adapter);
        //设置点击项目动作
        final Context ThisContext = this;
        final Intent ThisIntent = this.getIntent();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position,long arg3){
                //arg0表示点击发生的所在的AdapterView，arg1是在AdapterView中被点击的view，arg2表示adapter中view的位置（position），arg3表示被点击的item的行id
                Adapter adapter=arg0.getAdapter();
                Car car = (Car) adapter.getItem(position);
                User user = (User)ThisIntent.getSerializableExtra("user");
                Bundle bundle=new Bundle();
                bundle.putSerializable("user", user);
                bundle.putSerializable("st",ThisIntent.getSerializableExtra("startDate"));
                bundle.putSerializable("st",ThisIntent.getSerializableExtra("endDate"));
                Intent detailIntent = new Intent();
                detailIntent.putExtras(bundle);
                detailIntent.putExtra("carName",car.getCarName());
                detailIntent.setClass(ThisContext, ViewCarDetails.class);
                detailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(detailIntent);
            }
        });

    }
}