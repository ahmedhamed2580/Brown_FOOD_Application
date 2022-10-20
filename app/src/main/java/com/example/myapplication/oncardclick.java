package com.example.myapplication;

import java.util.ArrayList;

public interface oncardclick {
    void oncard_click(ArrayList<fooddb> food, int position);
    void oncardclickminus(ArrayList<fooddb>food,int position);
    void onexitclick(ArrayList<fooddb>food,int position);
}
