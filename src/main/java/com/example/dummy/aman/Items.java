package com.example.dummy.aman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.NumberFormat;

public class Items extends Activity {
Button total,deal;
    EditText item1,item2,item3,item4,item5,item6;
    TextView amt;
    int qt1=0,qt2=0,qt3=0,qt4=0,qt5=0,qt6=0;
    double amount;
    String data="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        amt=(TextView)findViewById(R.id.amount);
        total = (Button)findViewById(R.id.total);
        deal= (Button)findViewById(R.id.deal);
        item1 = (EditText)findViewById(R.id.et1);
        item2 = (EditText)findViewById(R.id.et2);
        item3 = (EditText)findViewById(R.id.et3);
        item4 = (EditText)findViewById(R.id.et4);
        item5 = (EditText)findViewById(R.id.et5);
        item6 = (EditText)findViewById(R.id.et6);

        Intent get=getIntent();
        final String Name = get.getStringExtra("Aname");
        final String Shop = get.getStringExtra("Ashop");
        final String Address = get.getStringExtra("Aaddress");

        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qt1 = Integer.parseInt(item1.getText().toString());
                qt2 = Integer.parseInt(item2.getText().toString());
                qt3 = Integer.parseInt(item3.getText().toString());
                qt4 = Integer.parseInt(item4.getText().toString());
                qt5 = Integer.parseInt(item5.getText().toString());
                qt6 = Integer.parseInt(item6.getText().toString());

                amount =  ((qt1 * 9.70) + (qt2 * 16.15) + (qt3 * 19.40) + (qt4 * 26.60) + (qt5 * 29.10) + (qt6 * 38.80));
                NumberFormat final_amt = NumberFormat.getNumberInstance();
                amt.setText("\u20B9"+final_amt.format(amount));
                if(qt1!=0)
                {
                    data=data+"\n"+" \u20B9 "+" 10 "+" : "+qt1;
                }
                if(qt2!=0)
                {
                    data=data+"\n"+" \u20B9 "+" 17 "+" : "+qt2;
                }
                if(qt3!=0)
                {
                    data=data+"\n"+" \u20B9 "+" 20 "+" : "+qt3;
                }
                if(qt4!=0)
                {
                    data=data+"\n"+" \u20B9 "+" 28 "+" : "+qt4;
                }
                if(qt5!=0)
                {
                    data=data+"\n"+" \u20B9 "+" 30 "+" : "+qt5;
                }
                if(qt6!=0)
                {
                    data=data+"\n"+" \u20B9 "+" 40 "+" : "+qt6;
                }
            }
        });

        deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NumberFormat final_amt = NumberFormat.getNumberInstance();
                Intent intent=new Intent(Items.this,Panel.class);
                intent.putExtra("Name",Name);
                intent.putExtra("Shop",Shop);
                intent.putExtra("Address",Address);
                intent.putExtra("Order",data);
                intent.putExtra("Amount",final_amt.format(amount));
                startActivity(intent);

            }
        });
           }
}


