package com.example.dummy.aman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class MainActivity extends Activity {
    Button login;
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.login);
        etUsername = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPass);
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                login.setClickable(false);
                onLogin();
            }
        });

    }

    public void onLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(username, password);

    }

    public class BackgroundTask extends AsyncTask<String, Void, String> {
        Context context;
        AlertDialog alertDialog;
        BackgroundTask(Context ctx) {
            context = ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String login_url = "http://192.168.43.147/android/Aman_retailers.php";
            //String login_url = "http://31.170.165.106/public_html/Aman_retailers.php";
            try
            {
                String username = params[0];
                String password = params[1];
                //login process
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "Utf-8"));
                String post_data = URLEncoder.encode("userName", "Utf-8") + "=" + URLEncoder.encode(username, "Utf-8") + "&" +
                        URLEncoder.encode("userPass", "Utf-8") + "=" + URLEncoder.encode(password, "Utf-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String line = "";
                String result = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                return result;

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login Status !");
        }
        @Override
        protected void onPostExecute(String result) {
            if (result.equals("Failed to log in!")) {
                alertDialog.setMessage(result);
                alertDialog.show();
            } else
            {
                fetchData feta = new fetchData(MainActivity.this);
                feta.execute(etUsername.getText().toString());
                etUsername.setText("");
                etPassword.setText("");

            }
            login.setClickable(true);
        }
        public class fetchData extends AsyncTask<String, Void, String>
        {

            public fetchData(Context context) {
                Context ctx = context;
            }
            String result2 = "";
            @Override
            protected String doInBackground(String... params) {
                //String fetch_url = "http://31.170.165.106/public_html/Aman_fetch.php";
                String fetch_url = "http://192.168.43.147/android/Aman_fetch.php";
                try
                {
                    String username = params[0];
                    URL url2 = new URL(fetch_url);
                    HttpURLConnection httpURLConnection2 = (HttpURLConnection) url2.openConnection();
                    httpURLConnection2.setRequestMethod("POST");
                    httpURLConnection2.setDoInput(true);
                    httpURLConnection2.setDoInput(true);
                    OutputStream outputStream2 = httpURLConnection2.getOutputStream();
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStream2, "Utf-8"));
                    String post_data2 = URLEncoder.encode("username", "Utf-8") + "=" + URLEncoder.encode(username, "Utf-8");

                    bufferedWriter2.write(post_data2);
                    bufferedWriter2.flush();
                    bufferedWriter2.close();
                    outputStream2.close();

                    InputStream inputStream2 = httpURLConnection2.getInputStream();
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2, "iso-8859-1"));
                    String line2 = "";
                    while ((line2 = bufferedReader2.readLine()) != null) {
                        result2 += line2;
                    }
                    bufferedReader2.close();
                    inputStream2.close();
                    httpURLConnection2.disconnect();
                    return result2;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result2;
            }
            @Override
            protected void onPreExecute() {
            }
            @Override
            protected void onPostExecute(String result2) {
                String a[]=result2.split(";");
                Intent intent=new Intent(MainActivity.this,Panel.class);
                intent.putExtra("Name",a[0]);
                intent.putExtra("Shop",a[1]);
                intent.putExtra("Address",a[2]);
                startActivity(intent);
            }
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }
    }
}


