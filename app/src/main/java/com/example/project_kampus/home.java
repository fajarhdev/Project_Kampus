package com.example.project_kampus;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        BottomNavigationView nav = (BottomNavigationView) findViewById(R.id.bottomnav);

//        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
        NavController navCo = navHostFragment.getNavController();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.homefragment, R.id.scorefragment, R.id.profilefragment).build();
        NavigationUI.setupActionBarWithNavController(this, navCo, appBarConfiguration);
        NavigationUI.setupWithNavController(nav, navCo);


    }
}
