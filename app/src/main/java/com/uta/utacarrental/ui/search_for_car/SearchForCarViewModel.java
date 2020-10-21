package com.uta.utacarrental.ui.search_for_car;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchForCarViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SearchForCarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is search for car fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}