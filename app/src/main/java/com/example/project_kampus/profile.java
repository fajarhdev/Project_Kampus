package com.example.project_kampus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

import  com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class profile extends AppCompatActivity {

    private AppCompatTextView txtname, txtemail, txtstatus;
    private CardView btneditprofile, btnriwayat, btnbahasa, btnbantuan;
    private AppCompatImageView profilepict;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_profile);;

        profilepict = findViewById(R.id.profilepict);

        txtname = findViewById(R.id.name);
        txtemail = findViewById(R.id.email);
        txtstatus = findViewById(R.id.status);

        btneditprofile = findViewById(R.id.editprofile);
        btnriwayat = findViewById(R.id.riwayatpengerjaan);
        btnbahasa = findViewById(R.id.pilihbahas);
        btnbantuan = findViewById(R.id.bantuan);

//      button edit profile
        btneditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), edit_profile.class);
                startActivity(i);
            }
        });

//      button riwayat
        btnriwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), history.class);
                startActivity(i);
            }
        });

//      button bahasa
        btnbahasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), bahasa.class);
                startActivity(i);
            }
        });

//      button bantuan
        btnbantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), pusat_bantuan.class);
                startActivity(i);
            }
        });
    }

//    buat konek db disini



}

