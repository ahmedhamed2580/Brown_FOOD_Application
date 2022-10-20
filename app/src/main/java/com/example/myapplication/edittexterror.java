package com.example.myapplication;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class edittexterror<T> implements TextWatcher {
    private  T taeget;
    public  edittexterror(T taeget){
        this.taeget=taeget;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
    public abstract void onTextChanged(T target ,Editable s);

}
