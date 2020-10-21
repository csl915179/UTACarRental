package com.uta.utacarrental.ui.view_available_car;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.uta.utacarrental.R;

public class ViewAvailableCarFragment extends Fragment {

    private ViewAvailableCarModel viewAvailableCarModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewAvailableCarModel =
                ViewModelProviders.of(this).get(ViewAvailableCarModel.class);
        View root = inflater.inflate(R.layout.fragment_view_available_car, container, false);
        final TextView textView = root.findViewById(R.id.text_view_car);
        viewAvailableCarModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}