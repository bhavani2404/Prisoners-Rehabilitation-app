package com.jo.prisonersrehabilitationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdminLogin extends AppCompatActivity {
    Button dl;
    EditText dlt,dlp;
    ProgressDialog pDialog;

    SharedPreferences sp1;
    public static final String SHARED_PREFS1 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        sp1 = getSharedPreferences(SHARED_PREFS1, Context.MODE_PRIVATE);
        dlt = (EditText) findViewById(R.id.entaad1);
        dlp =(EditText) findViewById(R.id.entappp1);
        dl= (Button) findViewById(R.id.ok1);


        dl.setOnClickListener(v -> new dlogin().execute());


    }


    public class dlogin extends AsyncTask<String, String, String> {

        final String duname = dlt.getText().toString();
        final String  dpwd = dlp.getText().toString();


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AdminLogin.this);
            pDialog.setMessage("Requesting " + duname + "...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @SuppressWarnings("deprecation")

        protected String doInBackground(String... args) {

            String txt = "";
            try {
                String ur = "http://"+ServerConnect.serverip +"/Android/prisonersrehabilitation/adminlogin.php?username=" +duname+"&password="+dpwd;
                Log.i("URL", ur);
                URL url = new URL(ur);
                HttpURLConnection uc = (HttpURLConnection) url.openConnection();
                DataInputStream dis = new DataInputStream(uc.getInputStream());
                String t = "";

                while ((t = dis.readLine()) != null) {
                    txt += t;
                }
                Log.i("Read", txt);
                dis.close();

            } catch (Exception e) {
                Log.i("Login Ex", e.getMessage());
            }
            return txt;
        }


        protected void onPostExecute(String file_url) {
            String ss= file_url.trim();
            Toast.makeText(getApplicationContext(), ss, Toast.LENGTH_LONG).show();

            if (file_url.trim().equals("Success")) {

                Toast.makeText(getApplicationContext(), "Login Success!", Toast.LENGTH_LONG).show();
                Intent in = new Intent(AdminLogin.this, AdminHome.class);
                SharedPreferences.Editor editor = sp1.edit();
                editor.putString("EMAIL_KEY1",duname);
                editor.putString("AID",duname);
                editor.commit();
                in.putExtra("dun",duname);
                startActivity(in);

            }


            else if(file_url.trim().equals("failed")) {
                Toast.makeText(getApplicationContext(), "User name and password wrong", Toast.LENGTH_LONG).show();

                dlt.setText("");
                dlp.setText("");

            }
            else
            { Toast.makeText(getApplicationContext(), "Connet error", Toast.LENGTH_LONG).show();}

            pDialog.dismiss();
        }
    }

}
