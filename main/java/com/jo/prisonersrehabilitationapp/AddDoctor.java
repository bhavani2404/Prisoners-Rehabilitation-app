package com.jo.prisonersrehabilitationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class AddDoctor extends AppCompatActivity {
    EditText e2,e3,e10;
    RadioGroup ra;
    Button bb;
    TextView e11;
    ProgressDialog pDialog;
    RadioButton selected_gender;
    String gender;
    DatePickerDialog datePickerDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        e2 = (EditText) findViewById(R.id.eemail);
        e3 = (EditText) findViewById(R.id.pcontact);

        e10 = (EditText) findViewById(R.id.epassword);
        ra= (RadioGroup)findViewById(R.id.prrg);

        bb = (Button) findViewById(R.id.preg);


        e11 = (TextView) findViewById(R.id.textView2);
        e11.setOnClickListener(v -> {
            Intent in = new Intent(getApplicationContext(), AddLawyer.class);
            startActivity(in);
        });


        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String demail = e2.getText().toString();
                final String dcontact = e3.getText().toString();


                final String dpwd = e10.getText().toString();

                if(demail.equals("")||dcontact.equals("")
                        ||dpwd.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Fill All Fields",Toast.LENGTH_LONG).show();
                }else {


                    int checkedId = ra.getCheckedRadioButtonId();
                    selected_gender = (RadioButton) findViewById(checkedId);
                    if (checkedId == -1) {
                        Toast.makeText(AddDoctor.this, "Select gender please", Toast.LENGTH_SHORT).show();
                    } else {
                        gender = selected_gender.getText().toString();
                        new registeration().execute();
                    }
                }


            }
        });

    }



    public class registeration extends AsyncTask<String, String, String> {


        final String demail = e2.getText().toString();
        final String dcontact = e3.getText().toString();

        final String dpwd = e10.getText().toString();

        //  String  gender = selected_gender.getText().toString();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddDoctor.this);
            pDialog.setMessage("Requesting " + demail + ")...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @SuppressWarnings("deprecation")

        protected String doInBackground(String... args) {

            String txt = "";
            try {
                String ur="http://"+ServerConnect.serverip+"/Android/prisonersrehabilitation/adddoctor.php?username="+ URLEncoder.encode(demail)

                        +"&pcontact="+URLEncoder.encode(dcontact)+

                        "&gender="+URLEncoder.encode(gender)
                        +"&epassword="+URLEncoder.encode(dpwd);
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
            // String ss= file_url.trim();
            // Toast.makeText(getApplicationContext(), ss, Toast.LENGTH_LONG).show();

            if (file_url.trim().equals("You are registered successfully")) {


                Toast.makeText(getApplicationContext(), "Registration Success!", Toast.LENGTH_LONG).show();
                finish();
                Intent in = new Intent(getApplicationContext(), AdminHome.class);
                // in.putExtra("si",serverip);
                startActivity(in);

            }


            else if(file_url.trim().equals("User name allready used type another one")) {
                Toast.makeText(getApplicationContext(), "User name allready used type another one", Toast.LENGTH_LONG).show();


                e10.setText("");

            }
            else
            { Toast.makeText(getApplicationContext(), "Please Check Login...!", Toast.LENGTH_LONG).show();}

            pDialog.dismiss();
        }
    }


}
