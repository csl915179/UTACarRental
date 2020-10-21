package com.uta.utacarrental.ui.search_for_user;

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

public class SearchForUserFragment extends Fragment {

    private SearchForUserViewModel searchForUserViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchForUserViewModel =
                ViewModelProviders.of(this).get(SearchForUserViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search_user, container, false);
        final TextView textView = root.findViewById(R.id.text_search_user);
        searchForUserViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}