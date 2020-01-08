package com.example.canteenclientapp.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.canteenclientapp.R;
import com.example.canteenclientapp.util.Utility;

/**
 * Created by DEEP on 9/21/2017.
 */

public class SplashActivity extends AppCompatActivity {

    private ProgressBar pb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        pb = (ProgressBar) findViewById(R.id.progress);

        new MyServerTask().execute();

    }

    private class MyServerTask extends AsyncTask<Void, Void, Void> {

        boolean status = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            status = isNetworkAvailable();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pb.setVisibility(View.INVISIBLE);
            if (status) {
                Intent in = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(in);
                finish();
            } else {
                Utility.showError(SplashActivity.this, "No Internet is available");
            }
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
