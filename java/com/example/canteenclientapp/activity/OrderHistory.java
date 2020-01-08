package com.example.canteenclientapp.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.canteenclientapp.R;
import com.example.canteenclientapp.model.Payment;
import com.example.canteenclientapp.model.User;
import com.example.canteenclientapp.util.Utility;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderHistory extends AppCompatActivity {

    User user;
    ProgressDialog pb;
    ListView lv;
    List<Payment> listPayment;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);
        user = (User) getIntent().getExtras().getSerializable("user");
        lv = findViewById(R.id.list_order_history);
        listPayment = new ArrayList();
        Toolbar tb=findViewById(R.id.toollist);
        tb.setTitle("Order History");
        tb.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(tb);
        tb.setNavigationIcon(R.drawable.order);

        pb = new ProgressDialog(this);
        pb.setCancelable(false);
        pb.setMessage("Waiting..");
        pb.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        getDataFromServer();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listPayment);
        lv.setAdapter(adapter);
    }

    private void getDataFromServer() {
        StringRequest sr = new StringRequest(Request.Method.POST, Utility.ORDER_HISTORY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {

                    pb.dismiss();
                    try {
                        JSONArray ja = new JSONArray(response);
                        for (int i = 0; i < ja.length(); i++) {
                            Gson g = new Gson();
                            Payment p = g.fromJson(ja.getJSONObject(i).toString(), Payment.class);
                            listPayment.add(p);
                            //Toast.makeText(OrderHistory.this, listPayment+"", Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        Log.e("error", e.toString());
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pb.dismiss();
                Utility.showError(OrderHistory.this, error.getLocalizedMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> h = new HashMap<>();
                h.put("uname", user.getUsername());
                h.put("mobile", user.getMobile());
                return h;
            }
        };
        Volley.newRequestQueue(this).add(sr);

    }



}
