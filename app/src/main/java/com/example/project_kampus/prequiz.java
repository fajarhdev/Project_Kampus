package com.example.project_kampus;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class prequiz extends AppCompatActivity {

    public String ns, plink, desc, np, lv;
    public TextView Paket_name, Paket_desc;
    public ImageView Paket_pic;

    private void getExtra(){
        if(     getIntent().hasExtra("ns") &&
                getIntent().hasExtra("plink") &&
                getIntent().hasExtra("desc") &&
                getIntent().hasExtra("np") &&
                getIntent().hasExtra("lv") ){
            ns = getIntent().getStringExtra("ns");
            plink = getIntent().getStringExtra("plink");
            desc = getIntent().getStringExtra("desc");
            np = getIntent().getStringExtra("np");
            lv = getIntent().getStringExtra("lv");
            setExtra();
        }
    }

    private void setExtra(){
        Paket_name = findViewById(R.id.paket_name);
        Paket_pic = findViewById(R.id.paket_pic);
        Paket_desc = findViewById(R.id.paket_desc);

        Paket_name.setText(ns + " ~ " + np + " ~ " + lv);
        Paket_desc.setText(desc);
        if(URLUtil.isValidUrl(plink)){
            Picasso.get().load(plink).into(Paket_pic);
        }
        else{
            Paket_pic.setImageResource(R.drawable.mike);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_prequiz);

        getExtra();
    }

    public void mulai(View v){
        Intent intent = new Intent(this, quiz.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
