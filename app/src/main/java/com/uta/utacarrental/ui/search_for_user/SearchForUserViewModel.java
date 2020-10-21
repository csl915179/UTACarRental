package com.uta.utacarrental.ui.search_for_user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchForUserViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SearchForUserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is search for user fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}