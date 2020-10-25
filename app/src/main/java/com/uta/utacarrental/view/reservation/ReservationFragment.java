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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.uta.utacarrental.R;
import com.uta.utacarrental.model.Reservation;

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

        TextView tvFromDate = root.findViewById(R.id.tv_from_date);
        TextView tvFromTime = root.findViewById(R.id.tv_from_time);
        TextView tvToDate = root.findViewById(R.id.tv_to_date);
        TextView tvToTime = root.findViewById(R.id.tv_to_time);

        tvFromDate.setText("2020/10/25");
        tvFromTime.setText("00:00");
        tvToDate.setText("2020/10/25");
        tvToTime.setText("11:59");

        final ListView listView = root.findViewById(R.id.reservation_list);
        reservationViewModel.getListData().observe(getViewLifecycleOwner(), new Observer<List<Reservation>>() {
            @Override
            public void onChanged(final List<Reservation> reservations) {
                listView.setAdapter(new ReservationAdapter(activity, reservations));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(activity, ReservationDetailActivity.class);
                        intent.putExtra("reservationNumber", reservations.get(position).getReservationNumber());
                        startActivity(intent);
                    }
                });
            }
        });
        return root;
    }
}