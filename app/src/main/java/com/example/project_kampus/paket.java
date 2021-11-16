package com.example.project_kampus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class paket extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PaketAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<paket_Item> list;
    private paket_Item[] pakets;

    public String ns;
    public String plink;
    public String desc;
    public String desc2;

    public TextView Subject_name, Subject_desc, Subject_desc2;
    public ImageView Subject_pic;

    public void gotoprequiz(int position){
        //Toast.makeText(this, list.get(position).getSubject(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, prequiz.class);
        intent.putExtra("ns", list.get(position).getMns());
        if(list.get(position).getMpic().getClass().equals(String.class)){
            intent.putExtra("plink", list.get(position).getMpic());
        }
        else{
            intent.putExtra("plink", "Something");
        }
        intent.putExtra("desc", list.get(position).getMdesc());
        intent.putExtra("np", list.get(position).getMname());
        intent.putExtra("lv", list.get(position).getMlv());
        startActivity(intent);
    }

    private void getExtra(){
        if(     getIntent().hasExtra("ns") &&
                getIntent().hasExtra("plink") &&
                getIntent().hasExtra("desc") &&
                getIntent().hasExtra("desc2") ){
            ns = getIntent().getStringExtra("ns");
            plink = getIntent().getStringExtra("plink");
            desc = getIntent().getStringExtra("desc");
            desc2 = getIntent().getStringExtra("desc2");
            setExtra();
        }
    }

    private void setExtra(){
        Subject_name = findViewById(R.id.subject_name);
        Subject_pic = findViewById(R.id.subject_pic);
        Subject_desc = findViewById(R.id.subject_desc);
        Subject_desc2 = findViewById(R.id.subject_desc2);

        Subject_name.setText(ns);
        Subject_desc.setText(desc);
        Subject_desc2.setText(desc2);
        if(URLUtil.isValidUrl(plink)){
            Picasso.get().load(plink).into(Subject_pic);
        }
        else{
            Subject_pic.setImageResource(R.drawable.mike);
        }
    }

    private void getlist(){
        String s = "";
        try{
            URL url =new URL("https://projectquiz001.000webhostapp.com/paket.php?getlevels=1&subject=dsa");
            URLConnection ucon = url.openConnection();
            InputStream is = ucon.getInputStream();
            InputStreamReader isr =new InputStreamReader(is);
            int data = isr.read();
            while(data != -1){
                char current = (char)data;
                s = s + current;
                data =isr.read();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        Gson gson = new Gson();
        pakets = gson.fromJson(s,paket_Item[].class);
    }

    private void inputlist(){
        list = new ArrayList<>();
        int i = 0;
        while(i<pakets.length){
            list.add(pakets[i]);
            i++;
        }
    }

    public void buildRecyclerMethod(){
        recyclerView = findViewById(R.id.reViewPaket);
        recyclerView.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);
        adapter = new PaketAdapter(list);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new PaketAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                gotoprequiz(position);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getExtra();
        getlist();
        inputlist();
        buildRecyclerMethod();
    }
}