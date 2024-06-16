package com.jo.prisonersrehabilitationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PrisonerLogin extends AppCompatActivity {
    Button dl;
    static EditText dlt,dlp;
    ProgressDialog pDialog;


    static ArrayList<String> FID;

    SharedPreferences sp1;
    public static final String SHARED_PREFS1 = "";
    TextView e11;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prisoner_login);
        e11 = (TextView) findViewById(R.id.textView2);
        e11.setOnClickListener(v -> {
            Intent in = new Intent(getApplicationContext(), DoctorLogin.class);
            startActivity(in);
        });

        sp1 = getSharedPreferences(SHARED_PREFS1, Context.MODE_PRIVATE);
        dlt = (EditText) findViewById(R.id.entusr);
        dlp =(EditText) findViewById(R.id.entpass);
        dl= (Button) findViewById(R.id.ulog);

        dl.setOnClickListener(v -> {
            if(dlt.getText().toString().equals("")||dlp.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Fill All Fields", Toast.LENGTH_SHORT).show();

            }else {
                new dlogin().execute();
            }
        });

    }



    public class dlogin extends AsyncTask<String, String, String> {


        final String eusername = dlt.getText().toString();
        final String  epassword = dlp.getText().toString();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(PrisonerLogin.this);
            pDialog.setMessage("Requesting");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @SuppressWarnings("deprecation")

        protected String doInBackground(String... args) {
            //Log.i("Read",eusername.get(RecyclerViewItemPosition));

            String txt = "";
            try {
                String ur = "http://"+ServerConnect.serverip +"/Android/prisonersrehabilitation/prisonerlogin.php?eusername="+eusername
                        +"&epassword="+epassword;
                Log.i("URL", ur);

                URL url = new URL(ur);
                HttpURLConnection uc = (HttpURLConnection) url.openConnection();
                DataInputStream dis = new DataInputStream(uc.getInputStream());
                String t = "";
                while ((t = dis.readLine()) != null) {
                    txt += t;
                }
                Log.i("Read", txt);
                // dis.close();
            } catch (Exception e) {
                Log.i("Login Ex", e.getMessage());
            }
            return txt;
        }


        protected void onPostExecute(String file_url) {

            if (file_url.trim().equals("Success")) {

                Toast.makeText(getApplicationContext(), "Login Success!", Toast.LENGTH_LONG).show();
                Intent in = new Intent(getApplicationContext(), PrisonerHome.class);
                SharedPreferences.Editor editor = sp1.edit();
                //UserHome2.putExtra("name",eusername);
                editor.putString("UID",eusername);
                editor.commit();
                startActivity(in);

            }


            else if(file_url.trim().equals("failed")) {
                Toast.makeText(getApplicationContext(), "User name OR password wrong", Toast.LENGTH_LONG).show();
                dlt.setText("");
                dlp.setText("");

            }
            else
            { Toast.makeText(getApplicationContext(), "Connection Error!", Toast.LENGTH_LONG).show();}

            pDialog.dismiss();
        }
    }

}