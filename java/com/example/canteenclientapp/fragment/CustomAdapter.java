package com.example.canteenclientapp.fragment;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.canteenclientapp.R;
import com.example.canteenclientapp.model.Product;

import java.util.ArrayList;

/**
 * Created by DEEP on 11/7/2017.
 */

public class CustomAdapter extends BaseAdapter {

    ArrayList<Product> list;
    AppCompatActivity cmp;

    public CustomAdapter(AppCompatActivity cmp, ArrayList<Product> list) {
        this.cmp = cmp;
        this.list = list;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = cmp.getLayoutInflater().inflate(R.layout.grid_item, null);
        TextView tv = (TextView) v.findViewById(R.id.name);
        TextView tv1 = (TextView) v.findViewById(R.id.quantity);
        TextView tv2 = (TextView) v.findViewById(R.id.price);
        tv.setText(list.get(position).getName());
        tv1.setText(list.get(position).getQuantity());
        tv2.setText(list.get(position).getPrice());
        return v;
    }

    @Override
    public int getCount() {
        return list.size();
    }


}
