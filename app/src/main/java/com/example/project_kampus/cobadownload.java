package com.example.project_kampus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

public class cobadownload extends AppCompatActivity {
    DownloadManager dm;
    Toast toast1;
    long dl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy =new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobadownload);
    }
    public void downloadaja(View view){
        dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse("http://markusraja.educationhost.cloud/HKM/hello.mp3");
        DownloadManager.Request r = new DownloadManager.Request(uri);
        r.setDestinationInExternalFilesDir(cobadownload.this, "/", "hello.mp3");
        r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        dl = dm.enqueue(r);
        Toast.makeText(getBaseContext(), "OK", Toast.LENGTH_LONG).show();
    }
}