package com.example.canteenclientapp.util;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.canteenclientapp.R;




public class Utility {

    public static final String USER_REGISTER = "http://192.168.43.11:8080/CanteenApp/user_reg.jsp";
    public static final String USER_LOGIN = "http://192.168.43.11:8080/CanteenApp/user_log.jsp";
    public static final String PRODUCTS = "http://192.168.43.11:8080/CanteenApp/myJson.jsp";
    public static final String ORDER = "http://192.168.43.11:8080/CanteenApp/order.jsp";
    public static final String PAYMENT_URL = "http://192.168.43.11:8080/CanteenApp/payment.jsp";
    public static final String ORDER_HISTORY = "http://192.168.43.11:8080/CanteenApp/order_history.jsp";

    public static void showError(final AppCompatActivity cmp, String message) {
        AlertDialog.Builder a = new AlertDialog.Builder(cmp);
        a.setTitle("Error-Message");
        a.setIcon(R.drawable.error);
        a.setMessage(message);
        a.setCancelable(false);
        a.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                System.exit(0);
            }
        });
        a.show();
    }

    public static void showMessage(final AppCompatActivity cmp, String message) {
        AlertDialog.Builder a = new AlertDialog.Builder(cmp);
        a.setTitle("Message");
        a.setMessage(message);
        a.setCancelable(false);
        a.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                cmp.finish();
            }
        });
        a.show();
    }
}
