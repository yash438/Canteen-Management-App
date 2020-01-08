package com.example.canteenclientapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.canteenclientapp.R;
import com.example.canteenclientapp.model.Order;
import com.example.canteenclientapp.model.User;
import com.example.canteenclientapp.util.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DEEP on 08-02-2018.
 */

public class AddToByCart extends AppCompatActivity {

    Order order;
    GlobalVariable global;
    ArrayList<Order> listOrders;
    TextView tv;
    ProgressDialog pb;
    ListView lv;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtobycart);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbycart);
        global = (GlobalVariable) getApplication();
        listOrders = global.getOrderFromByCart();
        tv = findViewById(R.id.total_cart_group_price);
        tb.setTitle("By Cart Items");
        tb.setNavigationIcon(R.drawable.bac);
        user = (User) getIntent().getExtras().getSerializable("user");
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pb = new ProgressDialog(this);
        pb.setCancelable(true);
        pb.setMessage("Waiting..");
        pb.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        lv = (ListView) findViewById(R.id.bycartitems);
        lv.setAdapter(new CustomAdapterForByCartItems(this, listOrders));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order o = listOrders.get(position);
                listOrders.remove(position);
                lv.setAdapter(new CustomAdapterForByCartItems(AddToByCart.this, listOrders));
                setTotalPrice();
            }
        });
        setTotalPrice();


    }

    private void setTotalPrice() {
        int total = 0;
        for (Order o : listOrders) {
            total = total + Integer.parseInt(o.getProductprice()) * Integer.parseInt(o.getProductquantity());
            Log.e("deep", o.toString());
        }

        tv.setText(String.valueOf(total));
    }

    public void clickForOrder(View v) {
        if (listOrders.size() > 0) {
            pb.show();
            sendOrderToServer();
        } else {
            Toast.makeText(AddToByCart.this, "There are no Items !!", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendOrderToServer() {
        StringRequest sr = new StringRequest(Request.Method.POST, Utility.ORDER, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (!s.isEmpty()) {
                    pb.dismiss();
                    Intent in = new Intent(AddToByCart.this, SuccessOrder.class);
                    in.putExtra("total", tv.getText().toString());
                    in.putExtra("name", user.getName());
                    in.putExtra("mobile", user.getMobile());
                    in.putExtra("uname", user.getUsername());
                    in.putExtra("order_id", s.trim());
                    in.putExtra("result", s.trim());
                    startActivity(in);
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                pb.dismiss();
                Utility.showError(AddToByCart.this, volleyError.getLocalizedMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> h = new HashMap();
                Gson g = new Gson();
                h.put("order", "2");
                h.put("group", g.toJson(listOrders));
                return h;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(sr);
    }


}
