package com.example.canteenclientapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.canteenclientapp.R;
import com.example.canteenclientapp.model.User;

public class ProfileActivity extends AppCompatActivity {

    User user;
    TextView t1, t2, t3, t4, t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        user = (User) getIntent().getExtras().getSerializable("user");
        Toolbar tb=findViewById(R.id.profiletool);
        tb.setTitle("User Profile");
        tb.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(tb);

        initViews();
        t1.setText("Name : " + user.getName());
        t2.setText("UserName : " +user.getUsername());
        t3.setText("Pass : " + user.getPass());
        t4.setText("Mobile : " + user.getMobile());
        t5.setText("Address : " + user.getAddress());
    }

    private void initViews() {

        t1 = findViewById(R.id.profilename);
        t2 = findViewById(R.id.profileusername);
        t3 = findViewById(R.id.profilepass);
        t4 = findViewById(R.id.profilemobile);
        t5 = findViewById(R.id.profileaddress);
    }
}
