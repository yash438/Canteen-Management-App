package com.example.canteenclientapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.canteenclientapp.R;
import com.example.canteenclientapp.activity.ProductDetailActivity;
import com.example.canteenclientapp.model.Product;
import com.example.canteenclientapp.model.User;
import com.example.canteenclientapp.util.Utility;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by DEEP on 9/22/2017.
 */

public class Pastry extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    ArrayList<Product> list;
    ListView lv;
    SwipeRefreshLayout sr;
    User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pastry, container, false);
        user = (User) getArguments().getSerializable("user");
        lv = (ListView) v.findViewById(R.id.listdatapastry);
        sr = (SwipeRefreshLayout) v.findViewById(R.id.pastryrefresh);
        lv.setOnItemClickListener(this);
        sr.setOnRefreshListener(this);
        sr.post(new Runnable() {
                    @Override
                    public void run() {
                        sr.setRefreshing(true);
                        setData("pastry");
                    }
                }
        );
        return v;
    }

    @Override
    public void onRefresh() {
        setData("pastry");
    }


    public void setData(final String key) {
        sr.setRefreshing(true);
        StringRequest sr1 = new StringRequest(Request.Method.POST, Utility.PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                if (!s.isEmpty()) {
                    try {
                        Gson g = new Gson();

                        JSONObject jaa = new JSONObject(s);
                        JSONArray ja = jaa.getJSONArray(key);
                        //Toast.makeText((AppCompatActivity) getContext(), ja.toString(), Toast.LENGTH_SHORT).show();
                        list = new ArrayList<Product>();
                        for (int i = 0; i < ja.length(); i++) {
                            //Toast.makeText(HomeActivity.this, ja.getJSONObject(i)+"", Toast.LENGTH_SHORT).show();;
                            Product p = g.fromJson(ja.getJSONObject(i).toString(), Product.class);
                            Log.e("error : " + i, p.getName());
                            list.add(p);
                        }
                        lv.setAdapter(new CustomAdapter((AppCompatActivity) getContext(), list));
                    } catch (Exception e) {
                        Log.e("product loading home", e.toString());
                    }
                    sr.setRefreshing(false);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Utility.showError((AppCompatActivity) getContext(), volleyError.getLocalizedMessage());
            }
        });
        RequestQueue rq = Volley.newRequestQueue(getContext());
        rq.add(sr1);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), user.getName(), Toast.LENGTH_SHORT).show();
        Intent in = new Intent(getContext(), ProductDetailActivity.class);
        in.putExtra("data", list.get(position));
        in.putExtra("user",user);
        startActivity(in);
    }

}
