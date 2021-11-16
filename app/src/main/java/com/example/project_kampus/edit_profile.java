package com.example.project_kampus;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class edit_profile extends AppCompatActivity {
    public EditText nama, ttl, nisn, kelas, institusi, email, nomorhp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String[] data;
        String id = "A";//Id pengenal

        nama = findViewById(R.id.nama);
        ttl = findViewById(R.id.ttlfield);
        nisn = findViewById(R.id.nisnfield);
        kelas = findViewById(R.id.kelasfield);
        institusi = findViewById(R.id.institusifield);
        email = findViewById(R.id.emailfield);
        nomorhp = findViewById(R.id.nohpfield);

        data = getdata(id);

        nama.setText(data[0]);
        ttl.setText(data[1]);
        nisn.setText(data[2]);
        kelas.setText(data[3]);
        institusi.setText(data[4]);
        email.setText(data[5]);
        nomorhp.setText(data[6]);
    }

    private String[] getdata(String ID){
        StringBuilder s= new StringBuilder();
        try {
            String link = "https://aarwebsite.000webhostapp.com/testb.php";
            URL url = new URL(link+"?id="+ ID);
            URLConnection ucon = url.openConnection();
            InputStream in = ucon.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            int datain = isw.read();
            while (datain != -1) {
                char current = (char) datain;
                s.append(current);
                datain = isw.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s.toString().split("`");
    }

    private void senddata(String[] data){
        String s = "";
        try {
            String link = "https://aarwebsite.000webhostapp.com/test.php";
            URL url = new URL(link+"?nama="+data[0]
                    +"&ttl="+ data[1] +"&nisn=" +data[2]+
                    "&kelas="+data[3]+"&institusi="+ data[4]
                    +"&email=" +data[5]+ "&nomorhp="+data[6]);
            URLConnection ucon = url.openConnection();
            InputStream in = ucon.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            int datain = isw.read();
            while (datain != -1) {
                char current = (char) datain;
                s = s + current;
                datain = isw.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(s.equals("Saved Successfully")){
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }
    }

    public void simpan(View v){
        String[] data = new String[7];

        data[0] = String.valueOf(nama.getText());
        data[1] = String.valueOf(ttl.getText());
        data[2] = String.valueOf(nisn.getText());
        data[3] = String.valueOf(kelas.getText());
        data[4] = String.valueOf(institusi.getText());
        data[5] = String.valueOf(email.getText());
        data[6] = String.valueOf(nomorhp.getText());

        senddata(data);
    }

    public void goBack(View v){
        finish();
    }
}
