package com.example.canteenclientapp.activity;

import android.app.Application;

import com.example.canteenclientapp.model.Order;

import java.util.ArrayList;

/**
 * Created by DEEP on 08-02-2018.
 */

public class GlobalVariable extends Application{

ArrayList<Order> list=new ArrayList<>();;

    public ArrayList<Order> getOrderFromByCart(){
        return list;
    }

    public void clearList(){
        list.clear();
    }

}
