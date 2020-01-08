package com.example.canteenclientapp.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
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
import com.example.canteenclientapp.model.Product;
import com.example.canteenclientapp.model.User;
import com.example.canteenclientapp.util.Utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ProductDetailActivity extends AppCompatActivity {

    Product product;
    TextView t1, t2, dtotal, deliver_time;
    Spinner t3;
    RadioGroup rg;
    String name, price, quantity, delivery, total, mode;
    ProgressDialog pb;
    User user;
    ArrayList<Order> addtobycart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.setTitleTextColor(getResources().getColor(R.color.white));
        tb.setTitle("Product Detail");
        GlobalVariable g = (GlobalVariable) getApplication();
        addtobycart = g.getOrderFromByCart();
        tb.setNavigationIcon(R.drawable.bac);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        user = (User) getIntent().getExtras().getSerializable("user");
        product = (Product) getIntent().getExtras().getSerializable("data");
        deliver_time = (TextView) findViewById(R.id.ddltime);
        t1 = (TextView) findViewById(R.id.dname);
        t2 = (TextView) findViewById(R.id.dprice);
        t3 = (Spinner) findViewById(R.id.dquantity);
        dtotal = (TextView) findViewById(R.id.dtotal);
        rg = (RadioGroup) findViewById(R.id.rg);
        pb = new ProgressDialog(this);
        pb.setCancelable(false);
        pb.setMessage("Waiting..");
        pb.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton r = findViewById(group.getCheckedRadioButtonId());
                //Toast.makeText(ProductDetailActivity.this, "Hello " + r.getText(), Toast.LENGTH_SHORT).show();
                if (r.getText().toString().equalsIgnoreCase("paytm")) {
                    AlertDialog.Builder a = new AlertDialog.Builder(ProductDetailActivity.this);
                    a.setTitle("Confirmation");
                    a.setMessage("Do you want to open Paytm ? ");
                    a.setPositiveButton("Now", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("net.one97.paytm");
                            startActivity(LaunchIntent);
                        }
                    });
                    a.setNegativeButton("Later", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    a.show();
                }


            }
        });
        t3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!t3.getSelectedItem().toString().equalsIgnoreCase("select quantity")) {
                    //Toast.makeText(ProductDetailActivity.this, t2.getText() + "\n" + t3.getSelectedItem(), Toast.LENGTH_SHORT).show();
                    int i = Integer.parseInt(t3.getSelectedItem().toString());
                    int j = Integer.parseInt(t2.getText().toString());
                    int k = i * j;
                    dtotal.setText(String.valueOf(k));
                } else {
                    dtotal.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        setData();

    }

    public void timePic(final View v) {
        // TimePicker
        Calendar c = Calendar.getInstance();
        int mhour = c.get(Calendar.HOUR);
        int mminute = c.get(Calendar.MINUTE);

        TimePickerDialog td = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                TextView t = (TextView) v;
                t.setText(hourOfDay + " : " + minute);
            }
        }, mhour, mminute, false);

        td.show();

    }

    private void setData() {

        t1.setText(product.getName());
        t2.setText(product.getPrice());
        int quant = Integer.parseInt(product.getQuantity());
        quant++;
        String qu[] = new String[quant];
        qu[0] = "Select Quantity";
        for (int i = 1; i < quant; i++) {
            qu[i] = (i) + "";
        }
        t3.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, qu));
    }

    private boolean valid() {
        name = t1.getText().toString();
        price = t2.getText().toString();
        quantity = t3.getSelectedItem().toString();
        Log.e("error time", delivery + "\n" + deliver_time);
        delivery = String.valueOf(deliver_time.getText());
        total = String.valueOf(dtotal.getText());
        if (TextUtils.isEmpty(total)) {
            return false;
        } else if (quantity.equalsIgnoreCase("select quantity")) {
            Log.e("error", quantity);
            Toast.makeText(this, "please select quantity", Toast.LENGTH_SHORT).show();
            t3.requestFocus();
            return false;
        } else if (delivery.equals("")) {
            Log.e("error", delivery);
            Toast.makeText(this, "please select time", Toast.LENGTH_SHORT).show();
            deliver_time.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public void sendOrder(View v) {
        if (valid()) {
            pb.show();
            RadioButton r = findViewById(rg.getCheckedRadioButtonId());
            mode = r.getText().toString();
            sendOrderToServer();
        }


    }

    private void sendOrderToServer() {
        StringRequest sr = new StringRequest(Request.Method.POST, Utility.ORDER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()) {
                    pb.dismiss();
                    Intent in = new Intent(ProductDetailActivity.this, SuccessOrder.class);
                    in.putExtra("total", total);
                    in.putExtra("name", user.getName());
                    in.putExtra("mobile", user.getMobile());
                    in.putExtra("uname", user.getUsername());
                    in.putExtra("order_id", response.trim());
                    in.putExtra("result", response.trim());
                    startActivity(in);
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pb.dismiss();
                Utility.showError(ProductDetailActivity.this, error.getLocalizedMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> h = new HashMap<>();
                h.put("order","1");
                h.put("pname", name);
                h.put("price", price);
                h.put("quantity", quantity);
                h.put("total", total);
                h.put("delivery", delivery);
                h.put("name", user.getName());
                h.put("mobile", user.getMobile());
                h.put("uname", user.getUsername());
                h.put("mode", mode);
                return h;
            }
        };
        Volley.newRequestQueue(this).add(sr);

    }

    public void myCart(View v) {
        if (valid()) {
            RadioButton r = findViewById(rg.getCheckedRadioButtonId());
            mode = r.getText().toString();
            Order order = new Order();
            order.setProductname(name);
            order.setProductprice(price);
            order.setProductquantity(quantity);
            order.setTotal(total);
            order.setDelivery(delivery);
            order.setName(user.getName());
            order.setMobile(user.getMobile());
            order.setUsername(user.getUsername());
            order.setMode(mode);

            addtobycart.add(order);
            Intent in = new Intent(this, AddToByCart.class);
            in.putExtra("user", user);
            startActivity(in);
        }
    }
}
