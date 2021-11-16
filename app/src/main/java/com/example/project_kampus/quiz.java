package com.example.project_kampus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class quiz extends AppCompatActivity {
    public TextView soal, a, b, c, scoreboard;
    public RadioGroup jawaban;
    public RadioButton pilihan;
    ArrayList<Integer> urutan = new ArrayList<>();
    public TextView timer;
    public int x = 0;
    public int score = 0;
    public boolean sequence;
    public int tmr;
    public Gson gson;
    public String json;
    @SerializedName("transkrip")
    private List<transkrip> transkripList;
    private hasil hasil;
    public Context context;
    public content data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        gson = new Gson();
        transkripList = new ArrayList<>();
        context = getApplicationContext();
        getJSON();
        setContentView(R.layout.activity_quiz);
        soal = findViewById(R.id.soal);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        scoreboard = findViewById(R.id.scoreboard);
        timer = findViewById(R.id.timer);
        call();
    }

    public void getUrutanSoal() {
        for (int i = 0; i < data.getContent().size(); i++) {
            urutan.add(i);
        }
        int a = data.getContent().size();
        int b, c;
        while (a > 0) {
            c = (int) Math.floor(Math.random() * a--);
            b = urutan.get(a);
            urutan.set(a, urutan.get(c));
            urutan.set(c, b);
        }
    }

    private void getJSON() {
        String s = "";
        try {
            String link = "https://projectquiz001.000webhostapp.com/soal.php?getall=1&kt=dsa";
            URL url = new URL(link);
            URLConnection ucon = url.openConnection();
            InputStream in = ucon.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                s = s + current;
                data = isw.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        data = gson.fromJson(s, content.class);
    }

    public void call() {
        getUrutanSoal();
        Toast.makeText(context, "Ready", Toast.LENGTH_SHORT).show();
        panggilSoal();
        scoreboard.setText(Integer.toString(score));
    }

    public void panggilSoal() {
        if (x < data.getContent().size()) {
            soal.setText("" + data.getContent().get(urutan.get(x)).getMisi());
            String[] jawabn = {data.getContent().get(urutan.get(x)).getMpil().getMa(),
                    data.getContent().get(urutan.get(x)).getMpil().getMb(),
                    data.getContent().get(urutan.get(x)).getMpil().getMc()};
            int[] jwbn = new int[]{0, 1, 2};
            int t1 = 3;
            int t2, t3;
            while (t1 > 0) {
                t3 = (int) Math.floor(Math.random() * t1--);
                t2 = jwbn[t1];
                jwbn[t1] = jwbn[t3];
                jwbn[t3] = t2;
            }
            a.setText(jawabn[jwbn[0]]);
            b.setText(jawabn[jwbn[1]]);
            c.setText(jawabn[jwbn[2]]);
            sequence = true;
            calltimer();
        }
    }

    public void submit(View v) {
        sequence = false;
    }

    private boolean issequence() {
        return sequence;
    }

    private void calltimer() {
        Handler handler = new Handler();
        Runnable runnable;
        runnable = new Runnable() {
            @Override
            public void run() {
                tmr = Integer.parseInt(data.getContent().get(urutan.get(x)).getMwk());
                while (tmr > 0 && issequence()) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            timer.setText("" + tmr);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                        tmr--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        check(tmr);
                    }
                });
            }
        };
        new Thread(runnable).start();
    }

    public void result() {
        hasil = new hasil(1, transkripList);
        json = gson.toJson(hasil);
        String s = "";
        try {
            String link = "https://projectquiz001.000webhostapp.com/paket.php?transaction=";
            URL url = new URL(link + json);
            URLConnection ucon = url.openConnection();
            InputStream in = ucon.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                s = s + current;
                data = isw.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (s.equals("Berhasil tersimpan")) {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, ScoreActivity.class);
            intent.putExtra("nilai", Integer.toString(score));
            startActivity(intent);
        } else {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    }

    public void check(int time) {
        if (x < data.getContent().size()) {
            jawaban = findViewById(R.id.jawaban);
            int ans = jawaban.getCheckedRadioButtonId();
            if (ans != -1 && x > -1) {
                pilihan = findViewById(ans);
                int answer = -1;
                String[] jawabn = {
                        data.getContent().get(urutan.get(x)).getMpil().getMa(),
                        data.getContent().get(urutan.get(x)).getMpil().getMb(),
                        data.getContent().get(urutan.get(x)).getMpil().getMc()};
                for (int i = 0; i < 3; i++) {
                    if (pilihan.getText().toString().equals(jawabn[i])) {
                        answer = i;
                        break;
                    }
                }
                if (time == 0) {
                    Toast.makeText(context, "TIMEOUT", Toast.LENGTH_SHORT).show();
                    transkripList.add(new transkrip(Integer.parseInt(data.getContent().get(urutan.get(x)).getMsid()), answer, "process", Integer.parseInt(data.getContent().get(urutan.get(x)).getMwk()) - time));
                } else if (answer == Integer.parseInt(data.getContent().get(urutan.get(x)).getMjb())) {
                    Toast.makeText(context, "BENAR", Toast.LENGTH_SHORT).show();
                    transkripList.add(new transkrip(Integer.parseInt(data.getContent().get(urutan.get(x)).getMsid()), answer, "resolved", Integer.parseInt(data.getContent().get(urutan.get(x)).getMwk()) - time));
                    score += Integer.parseInt(data.getContent().get(urutan.get(x)).getMss());
                } else {
                    Toast.makeText(context, "SALAH", Toast.LENGTH_SHORT).show();
                    transkripList.add(new transkrip(Integer.parseInt(data.getContent().get(urutan.get(x)).getMsid()), answer, "0", Integer.parseInt(data.getContent().get(urutan.get(x)).getMwk()) - time));
                }
            }
            scoreboard.setText(Integer.toString(score));
            x++;
            panggilSoal();
            if (x == data.getContent().size()) {
                result();
            }
        }
    }
}