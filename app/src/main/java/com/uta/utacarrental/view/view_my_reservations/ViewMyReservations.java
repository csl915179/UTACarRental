package com.uta.utacarrental.view.view_my_reservations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Car;
import com.uta.utacarrental.model.Reservation;
import com.uta.utacarrental.model.User;
import com.uta.utacarrental.view.reservation.ReservationDetailActivity;
import com.uta.utacarrental.view.view_car_details.ViewCarDetails;

import java.util.List;

public class ViewMyReservations extends AppCompatActivity {

    Intent intent = this.getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view_my_reservations);
        setTitle("Reservation Summary");
        ListView listView = (ListView) findViewById(R.id.reservation_listview);
        List<Reservation> reservation_list = (List<Reservation>)getIntent().getSerializableExtra("reservation_list");
        UserReservationAdapter adapter = new UserReservationAdapter(ViewMyReservations.this, R.layout.fragment_user_reservation_summary,reservation_list);
        listView.setAdapter(adapter);

        //设置点击项目动作
        final Context ThisContext = this;
        final Intent ThisIntent = this.getIntent();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
                //arg0表示点击发生的所在的AdapterView，arg1是在AdapterView中被点击的view，arg2表示adapter中view的位置（position），arg3表示被点击的item的行id
                Adapter adapter=arg0.getAdapter();
                Reservation reservation = (Reservation) adapter.getItem(position);
                User user = (User)ThisIntent.getSerializableExtra("user");
                Bundle bundle=new Bundle();
                bundle.putSerializable("user", user);
                bundle.putSerializable("reservation", reservation);
                Intent detailIntent = new Intent();
                detailIntent.putExtras(bundle);
                detailIntent.putExtra("user",user.getRole());
                detailIntent.putExtra("reservationID",reservation.getId());
                detailIntent.setClass(ThisContext, ReservationDetailActivity.class);
                detailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(detailIntent);
                finish();
            }
        });
    }
}