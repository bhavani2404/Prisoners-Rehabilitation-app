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
import java.util.ArrayList;

public class QuerySolution extends AppCompatActivity {
    Button dl;
    static EditText dlt;
    ProgressDialog pDialog;


    static ArrayList<String> FID;

    SharedPreferences sp1;
    public static final String SHARED_PREFS1 = "";
    String uid,pcategory,ptax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_solution);
        sp1 = getSharedPreferences(SHARED_PREFS1, Context.MODE_PRIVATE);
        dlt = (EditText) findViewById(R.id.entusr);
        dl= (Button) findViewById(R.id.ulog);
        Intent is = getIntent();

        String pname = is.getStringExtra("name");
        pcategory = is.getStringExtra("place");
        ptax = is.getStringExtra("time");
        String pprice = is.getStringExtra("date");
        uid = is.getStringExtra("dept");
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
            pDialog = new ProgressDialog(QuerySolution.this);
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
                String ur = "http://"+ServerConnect.serverip +"/Android/prisonersrehabilitation/postsolution.php?eusername="+eusername+"&id="+ptax
                        ;
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
                Intent in = new Intent(QuerySolution.this, HomePage.class);
                SharedPreferences.Editor editor = sp1.edit();
                //UserHome2.putExtra("name",eusername);
                editor.putString("UID",eusername);
                editor.commit();
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