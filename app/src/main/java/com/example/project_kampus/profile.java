package com.example.project_kampus;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

import  com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class profile extends AppCompatActivity {

    private AppCompatTextView txtname, txtemail, txtstatus;
    private CardView btneditprofile, btnriwayat, btnbahasa, btnbantuan;
    private AppCompatImageView profilepict;
    private GoogleSignInClient mgs;
    private AppCompatButton btn_sign_out;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_profile);;
        StrictMode.ThreadPolicy policy =new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mgs = GoogleSignIn.getClient(this, gso);

        profilepict = findViewById(R.id.profilepict);

        txtname = findViewById(R.id.name);
        txtemail = findViewById(R.id.email);
        txtstatus = findViewById(R.id.status);

        btneditprofile = findViewById(R.id.editprofile);
        btnriwayat = findViewById(R.id.riwayatpengerjaan);
        btnbahasa = findViewById(R.id.pilihbahas);
        btnbantuan = findViewById(R.id.bantuan);
        btn_sign_out = findViewById(R.id.btn_sign_out);

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

//        button signout
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {

            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            txtname.setText(personName);
            txtemail.setText(personEmail);
            txtstatus.setText((personId));

            Glide.with(this).load(String.valueOf(personPhoto)).circleCrop().into(profilepict);
        }

    }

    private void signOut() {
        mgs.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        updateUI(null);
                    }
                });
    }

    private void updateUI(@Nullable GoogleSignInAccount account) {
        if (account == null) {
            Intent i = new Intent(getApplicationContext(), login.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "GAGAL LOGOUT", Toast.LENGTH_LONG).show();
        }
    }


//    buat konek db disini

//        public class getData extends AsyncTask<String , String, String>{
//
//            @Override
//            protected String doInBackground(String... s) {
//                try {
//                    URL url = new URL(JSON_URL);
//                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//
//                    InputStream in = urlConnection.getInputStream();
//                    InputStreamReader isr = new InputStreamReader(in);
//
//
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }



}

