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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DoctorConsult extends AppCompatActivity {
    Button dl;
    static EditText dlt;
    ProgressDialog pDialog;


    static ArrayList<String> FID;
    public static final String SHARED_PREFS1 = "";

    SharedPreferences sharedpreferences1;


    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_consult);
        TextView textView=findViewById(R.id.doc);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ViewSolutionDoctor.class);
                startActivity(intent);
            }
        });
        dlt = (EditText) findViewById(R.id.entusr);
        dl= (Button) findViewById(R.id.ulog);
        sharedpreferences1 = getSharedPreferences(SHARED_PREFS1, Context.MODE_PRIVATE);

        dl.setOnClickListener(v -> {
            if(dlt.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Fill All Fields", Toast.LENGTH_SHORT).show();

            }else {
                new dlogin().execute();
            }
        });

    }



    public class dlogin extends AsyncTask<String, String, String> {


        final String eusername = dlt.getText().toString();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(DoctorConsult.this);
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
                String ur = "http://"+ServerConnect.serverip +"/Android/prisonersrehabilitation/doctorpostquery.php?eusername="+eusername+
                        "&pid="+sharedpreferences1.getString("UID", null);

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

                Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                Intent in = new Intent(DoctorConsult.this, PrisonerHome.class);
                startActivity(in);

            }


            else if(file_url.trim().equals("failed")) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                dlt.setText("");


            }
            else
            { Toast.makeText(getApplicationContext(), "Connection Error!", Toast.LENGTH_LONG).show();}

            pDialog.dismiss();
        }
    }

}