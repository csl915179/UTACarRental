package com.uta.utacarrental.ui.search_for_car;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.uta.utacarrental.R;

public class SearchForCarFragment extends Fragment {

    private SearchForCarViewModel searchForCarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchForCarViewModel =
                ViewModelProviders.of(this).get(SearchForCarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search_car, container, false);
        final TextView textView = root.findViewById(R.id.text_search_car);
        searchForCarViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}