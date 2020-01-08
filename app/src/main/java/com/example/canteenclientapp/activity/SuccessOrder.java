package com.example.canteenclientapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.canteenclientapp.R;
import com.example.canteenclientapp.util.Utility;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DEEP on 08-02-2018.
 */

public class SuccessOrder extends AppCompatActivity {

    ProgressDialog pb;

    String total, username, mobile, name, order_id;

    EditText paytmnumber, paytmprice;

    GlobalVariable global;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);
        Toolbar tb = findViewById(R.id.paymenttool);
        tb.setTitle("Confirmation");
        setSupportActionBar(tb);
        global = (GlobalVariable) getApplication();
        Button b = findViewById(R.id.btn);
        pb = new ProgressDialog(this);
        pb.setCancelable(false);
        pb.setMessage("Waiting..");
        pb.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        total = getIntent().getExtras().getString("total");
        username = getIntent().getExtras().getString("uname");
        mobile = getIntent().getExtras().getString("mobile");
        name = getIntent().getExtras().getString("name");
        order_id = getIntent().getExtras().getString("order_id");
        String result = getIntent().getExtras().getString("result");

        //final code here
        Log.e("error-success", total + "" + username + mobile + name + order_id);

        TextView tv = (TextView) findViewById(R.id.result);

        paytmnumber = findViewById(R.id.paytmnumber);
        paytmnumber.setText(mobile);
        paytmprice = findViewById(R.id.paytmtotalprice);
        paytmprice.setText(total);
        if (result.contains("order Failed.")) {
            tv.setText(result);
            b.setVisibility(View.INVISIBLE);
        }
    }

    private boolean valid() {
        mobile = paytmnumber.getText().toString();
        total = paytmprice.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            paytmnumber.setError("please enter paytm mobile number");
            paytmnumber.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public void submit(View v) {
        if (valid()) {
            pb.show();
            sendOrderToServer();
        }
    }

    private void sendOrderToServer() {
        StringRequest sr = new StringRequest(Request.Method.POST, Utility.PAYMENT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {
                    pb.dismiss();
                    //Toast.makeText(SuccessOrder.this, response, Toast.LENGTH_SHORT).show();
                    if(response!=null){
                        global.clearList();
                        Utility.showMessage(SuccessOrder.this, response);
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pb.dismiss();
                Utility.showError(SuccessOrder.this, error.getLocalizedMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> h = new HashMap<>();
                h.put("status", "pending");
                h.put("total", total);
                h.put("name", name);
                h.put("mobile", mobile);
                h.put("uname", username);
                h.put("order", order_id);

                return h;
            }
        };
        Volley.newRequestQueue(this).add(sr);

    }
}
