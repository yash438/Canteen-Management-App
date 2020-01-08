package com.example.canteenclientapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.canteenclientapp.model.User;
import com.example.canteenclientapp.util.Utility;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DEEP on 9/21/2017.
 */

public class LoginActivity extends AppCompatActivity {

    EditText t1, t2;
    TextView tv;
    String uname, pass;
    ProgressDialog pb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        pb = new ProgressDialog(this);
        pb.setCancelable(true);
        pb.setMessage("Waiting..");
        pb.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        initViews();
    }

    private void initViews() {
        t1 = (EditText) findViewById(R.id.luname);
        t2 = (EditText) findViewById(R.id.lpass);
        tv = (TextView) findViewById(R.id.lsignup);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(in);
                finish();
            }
        });
    }

    private boolean valid() {
        uname = t1.getText().toString();
        pass = t2.getText().toString();

        if (uname.equals("")) {
            t1.setError("Please enter username");
            t1.requestFocus();
            return false;
        } else if (pass.equals("")) {
            t2.setError("Please enter password");
            t2.requestFocus();
            return false;
        } else {
            return true;
        }
    }


    public void login(View v) {
        if (v.getId() == R.id.lsubmit) {
            if (valid()) {
                pb.show();
                loginUser();
            }
        } else {
            clear();
        }
    }

    private void clear() {
        t1.setText("");
        t2.setText("");
    }

    private void loginUser() {
        StringRequest sr = new StringRequest(Request.Method.POST, Utility.USER_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (!s.isEmpty()&&s!=null) {
                    pb.dismiss();
                    Gson g = new Gson();
                    User u = g.fromJson(s, User.class);
                    if (u!=null) {
                        Intent in = new Intent(LoginActivity.this, HomeActivity.class);
                        in.putExtra("user", u);
                        startActivity(in);
                        finish();

                    }else{
                        Toast.makeText(LoginActivity.this, "Login Failed !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                pb.dismiss();
                Utility.showError(LoginActivity.this, volleyError.getLocalizedMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> h = new HashMap();
                h.put("uname", uname);
                h.put("pass", pass);
                return h;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(sr);
    }
}
