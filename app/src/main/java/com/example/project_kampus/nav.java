package com.example.project_kampus;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class nav extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav);

        BottomNavigationView bottomNav = findViewById(R.id.bottombar);
        //bottomNav.setOnNavigationItemSelectedListener(navListener);
    }
/*
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selected = null;

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selected = new HomeFragment();
                            break;
                        case R.id.nav_score:
                            selected = new ScoreFragment();
                            break;
                        case R.id.nav_setting:
                            selected = new SettingsFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selected).commit();
                    return true;
                }
            };

 */
}

// TODO: 16/11/2021 create and continue the navigation creation in the bottom nav bar
