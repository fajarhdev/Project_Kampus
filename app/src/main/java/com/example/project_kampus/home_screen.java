package com.example.project_kampus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class home_screen extends AppCompatActivity{
    private RecyclerView recyclerView;
    private subjectAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Subject> list;
    private Subject[] subjects;

    private void getlist(){
        String s = "";
        try{
            URL url =new URL("https://projectquiz001.000webhostapp.com/paket.php?getsubjects=1");
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
        subjects = gson.fromJson(s,Subject[].class);
    }

    private void inputlist(){
        list = new ArrayList<>();
        int i = 0;
        while(i< subjects.length){
            list.add(subjects[i]);
            i++;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        StrictMode.ThreadPolicy policy =new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getlist();
        inputlist();
        buildRecyclerMethod();

    }

    public void insertItem(int position){
        list.add(position, new Subject(Integer.toString(R.drawable.mike),"Suboo","lalala","Boi"));
        adapter.notifyItemInserted(position);
    }

    public void removeItem(int position){
        list.remove(position);
        adapter.notifyItemRemoved(position);
    }

    public void changeItem(int position, String text){
        list.get(position).changeText(text);
        adapter.notifyItemChanged(position);
    }

    public void gotopaket(int position){
        //Toast.makeText(this, list.get(position).getSubject(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, paket.class);
        intent.putExtra("ns", list.get(position).getSubject());
        if(list.get(position).getSubject_pic().getClass().equals(String.class)){
            intent.putExtra("plink", list.get(position).getSubject_pic());
        }
        else{
            intent.putExtra("plink", "Something");
        }
        intent.putExtra("desc", list.get(position).getSubject_Desc());
        intent.putExtra("desc2", list.get(position).getSubject_Desc2());
        startActivity(intent);
    }

    public void buildRecyclerMethod(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new subjectAdapter(list);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new subjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position,"HOHOHOOO");
                gotopaket(position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}