package com.example.project_kampus;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    public TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        score = findViewById(R.id.score);

        if(getIntent().hasExtra("nilai")){
            String nilai = getIntent().getStringExtra("nilai");
            score.setText(nilai);
        }
    }
}