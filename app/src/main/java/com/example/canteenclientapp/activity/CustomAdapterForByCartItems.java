package com.example.canteenclientapp.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.canteenclientapp.R;
import com.example.canteenclientapp.model.Order;

import java.util.ArrayList;

/**
 * Created by DEEP on 08-02-2018.
 */

public class CustomAdapterForByCartItems extends BaseAdapter {
    ArrayList<Order> listOrders;
    AppCompatActivity cmp;
    int total_price = 0;

    public CustomAdapterForByCartItems(AppCompatActivity cmp, ArrayList<Order> listOrders) {
        this.listOrders = listOrders;
        this.cmp = cmp;
    }

    @Override
    public int getCount() {
        Log.e("error", listOrders + "");
        return listOrders.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

    @Override
    public Object getItem(int position) {
        return listOrders.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = cmp.getLayoutInflater().inflate(R.layout.cart_list_item, null);
        TextView name = v.findViewById(R.id.cart_pname);
        TextView price = v.findViewById(R.id.cart_price);
        TextView quantity = v.findViewById(R.id.cart_quantity);
        TextView total = v.findViewById(R.id.cart_total);


        if (listOrders.size() > 0) {
            Order order = (Order) listOrders.get(position);
            name.setText(order.getProductname());
            price.setText(order.getProductprice());
            quantity.setText(order.getProductquantity());
            int total_amount = Integer.parseInt(order.getProductquantity()) * Integer.parseInt(order.getProductprice());
            Log.e("error_custom",total_amount+"");
            total.setText(total_amount+"");

        }


        return v;
    }
}
