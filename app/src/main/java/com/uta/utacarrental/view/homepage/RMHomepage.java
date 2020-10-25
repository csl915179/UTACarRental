package com.uta.utacarrental.view.homepage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.uta.utacarrental.MainActivity;
import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Car;
import com.uta.utacarrental.model.Reservation;
import com.uta.utacarrental.model.User;
import com.uta.utacarrental.view.common.ChangePasswordScreen;
import com.uta.utacarrental.view.search_for_car.SearchForCar;
import com.uta.utacarrental.view.view_available_car.ViewAvailableCar;
import org.litepal.LitePal;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RMHomepage extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;

    Intent intent;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rm_homepage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view_rm);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_reservation, R.id.nav_profile, R.id.nav_search_car, R.id.nav_view_available_car)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        intent = this.getIntent();
        user = (User) intent.getSerializableExtra("user");

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //抽屉滑动时回调
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //抽屉打开后回调
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                //抽屉关闭后回调
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                //抽屉滑动状态改变时回调
                switch (newState) {
                    case DrawerLayout.STATE_DRAGGING:
                        //拖动状态
                        //没有break,拖动也会执行设置状态的代码
                    case DrawerLayout.STATE_SETTLING:
                        //设置状态
                        System.out.println("设置");
                        ((TextView) findViewById(R.id.username)).setText(user.getUsername());
                        ((TextView) findViewById(R.id.role)).setText(user.getRole());
                        break;
                    case DrawerLayout.STATE_IDLE:
                        //静止状态
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_homepage, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void logOut(MenuItem item){
        //注销时清空保存的登陆信息
        SharedPreferences sharedpreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor session = sharedpreferences.edit();
        session.clear();
        session.commit();

        //更改intent的目的地，销毁activity
        intent.setClass(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void changePassword(MenuItem item){
        //更改intent的目的地和Flags
        //Intent intent = new Intent();
        Bundle bundle=new Bundle();
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);
        intent.setClass(this, ChangePasswordScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void searchForCar(View view){
        String carName = ((EditText)findViewById(R.id.search_for_car_edit)).getText().toString().trim().toLowerCase();
        if (!carName.isEmpty()) {
            Car car = LitePal.where("carName = ?", carName).findFirst(Car.class);
            if (car!=null){
                ArrayList<Car> carList = new ArrayList<Car>();
                carList.add(car);
                Intent intent = new Intent();
                intent.putExtra("car_list",(Serializable)carList);
                intent.setClass(this, SearchForCar.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "Car name does not exist.", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(getApplicationContext(), "Please enter the Car Name", Toast.LENGTH_SHORT).show();
        }
    }
    public void viewAvailableCar(View view){
        String starttime = ((TextView)findViewById(R.id.start_time)).getText().toString();
        String endtime = ((TextView)findViewById(R.id.end_time)).getText().toString();

        if(starttime.equals("Click here to select a start time") || endtime.equals("Click here to select a end time")){
            Toast.makeText(getApplicationContext(), "Please choose the start or end time.", Toast.LENGTH_SHORT).show();
        }else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                Date startDate = format.parse(starttime);
                Date endDate = format.parse(endtime);
                int result = startDate.compareTo(endDate);
                if (result == 1) {
                    Toast.makeText(getApplicationContext(), "End Time earlier than Start Time", Toast.LENGTH_SHORT).show();
                } else {
                    //开始操作查询数据库的各种数据
                    Date currentTime = new Date();
                    List<Reservation> res = LitePal.where("(endtime > ? and starttime < ?) or (starttime < ? and endtime > ?) or (starttime > ? and endtime < ?)", startDate.getTime()+"",startDate.getTime()+"",endDate.getTime()+"",endDate.getTime()+"",startDate.getTime()+"",endDate.getTime()+"").find(Reservation.class);
                    List<Car> carList = LitePal.where("id not in (select car_id from reservation where (endtime > ? and starttime < ?) or (starttime < ? and endtime > ?) or (starttime > ? and endtime < ?))", startDate.getTime()+"",startDate.getTime()+"",endDate.getTime()+"",endDate.getTime()+"",startDate.getTime()+"",endDate.getTime()+"").find(Car.class);

                    Intent intent = new Intent();
                    intent.putExtra("car_list",(Serializable)carList);
                    intent.setClass(this, SearchForCar.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


    }
}
