package com.uta.utacarrental.ui.view_available_car;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewAvailableCarModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ViewAvailableCarModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is view available car fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}