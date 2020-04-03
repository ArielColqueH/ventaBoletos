package com.example.home.ui.boleto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BoletoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BoletoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is boleto");
    }

    public LiveData<String> getText() {
        return mText;
    }
}