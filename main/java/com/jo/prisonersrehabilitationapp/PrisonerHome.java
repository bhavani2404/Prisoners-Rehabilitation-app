package com.jo.prisonersrehabilitationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class PrisonerHome extends AppCompatActivity {
    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prisoner_home);
        linearLayout1=findViewById(R.id.custom);
        linearLayout2=findViewById(R.id.farmer);
        linearLayout3=findViewById(R.id.admin);
        linearLayout4=findViewById(R.id.admin1);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PrisonerHome.this,DoctorConsult.class);
                startActivity(intent);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(PrisonerHome.this,LawyerConsult.class);
                startActivity(intent);
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PrisonerHome.this,ViewMaterial.class);
                startActivity(intent);
            }
        });
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PrisonerHome.this,AdminLogin.class);
                startActivity(intent);
            }
        });
    }
}