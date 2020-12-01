package com.uta.utacarrental.view.view_my_reservations;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uta.utacarrental.R;

public class ViewMyReservationsFragment extends Fragment {

    private ViewMyReservationsViewModel mViewModel;

    public static ViewMyReservationsFragment newInstance() {
        return new ViewMyReservationsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_view_my_reservations, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewMyReservationsViewModel.class);
        // TODO: Use the ViewModel
    }

}