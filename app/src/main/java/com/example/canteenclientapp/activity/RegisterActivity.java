package com.example.canteenclientapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
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
import com.example.canteenclientapp.util.Utility;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    TextView tv;
    EditText t1, t2, t3, t4, t5;
    ProgressDialog pb;
    String uname, name, pass, mobile, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        pb = new ProgressDialog(this);
        pb.setCancelable(true);
        pb.setMessage("Waiting..");
        pb.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        initViews();
    }

    private void initViews() {
        t1 = (EditText) findViewById(R.id.runame);
        t2 = (EditText) findViewById(R.id.rpass);
        t3 = (EditText) findViewById(R.id.rname);
        t4 = (EditText) findViewById(R.id.rmobile);
        t5 = (EditText) findViewById(R.id.raddress);
        tv = (TextView) findViewById(R.id.rsignin);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(in);
                finish();
            }
        });
    }

    private boolean valid() {
        uname = t1.getText().toString();
        pass = t2.getText().toString();
        name = t3.getText().toString();
        mobile = t4.getText().toString();
        address = t5.getText().toString();
        if (uname.equals("")) {
            t1.setError("Please enter username");
            t1.requestFocus();
            return false;
        } else if (pass.equals("")) {
            t2.setError("Please enter password");
            t2.requestFocus();
            return false;
        } else if (name.equals("")) {
            t3.setError("Please enter name");
            t3.requestFocus();
            return false;
        } else if (mobile.equals("")) {
            t4.setError("Please enter mobile");
            t4.requestFocus();
            return false;
        } else if (address.equals("")) {
            t5.setError("Please enter address");
            t5.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public void submit(View v) {
        if (v.getId() == R.id.rsubmit) {
            if (valid()) {
                pb.show();
                registerServer();
            } else {
                clear();
            }
        }
    }

    private void clear() {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
    }

    private void registerServer() {
        StringRequest sr = new StringRequest(Request.Method.POST, Utility.USER_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (!s.isEmpty()) {
                    pb.dismiss();
                    Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(in);
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                pb.dismiss();
                Utility.showError(RegisterActivity.this, volleyError.getLocalizedMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> h = new HashMap();
                h.put("uname", uname);
                h.put("pass", pass);
                h.put("name", name);
                h.put("mobile", mobile);
                h.put("address", address);
                return h;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(sr);
    }
}
