package com.uta.utacarrental.view.reservation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Reservation;

import org.litepal.LitePal;

import java.util.Date;
import java.util.List;

public class ReservationFragment extends Fragment {

    private ReservationViewModel reservationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final Activity activity = getActivity();
        assert activity != null;
        reservationViewModel =
                ViewModelProviders.of(this).get(ReservationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reservation, container, false);
        final TextView fromDate = root.findViewById(R.id.tv_from_date);

        final ListView listView = root.findViewById(R.id.reservation_list);
//        listView.setAdapter(new ReservationAdapter(activity, reservationList));
//        reservationViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText("From");
//            }
//        });

        reservationViewModel.getListData().observe(getViewLifecycleOwner(), new Observer<List<Reservation>>() {
            @Override
            public void onChanged(final List<Reservation> reservations) {
                listView.setAdapter(new ReservationAdapter(activity, reservations));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(activity, reservations.get(position).getCarName(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(activity, ReservationDetailActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
        return root;
    }
}