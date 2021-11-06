package com.example.project_kampus;

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

    }

    private void getProfileDetail(String userId) {

        // url to post our data
        String url = "http://localhost/courseApp/readCourses.php";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(profile.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // on below line passing our response to json object.
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are checking if the response is null or not.
                    if (jsonObject.getString("uid") == null) {
                        // displaying a toast message if we get error
                        Toast.makeText(profile.this, "Please enter valid id.", Toast.LENGTH_SHORT).show();
                    } else {
                        // if we get the data then we are setting it in our text views in below line.
                        txtname.setText(jsonObject.getString("..."));
                        txtemail.setText(jsonObject.getString("..."));
                        txtstatus.setText(jsonObject.getString("..."));
//                        courseCV.setVisibility(View.VISIBLE);
                    }
                    // on below line we are displaying
                    // a success toast message.
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(MainActivity.this, "Fail to get course" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key and value pair to our parameters.
                params.put("id", courseId);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }
}
}
