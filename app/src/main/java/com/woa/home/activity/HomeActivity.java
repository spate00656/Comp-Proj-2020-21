package com.woa.home.activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.fragment.NavHostFragment;

import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.woa.R;
import com.woa.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        setupNavigationView();
    }


    // home NavigationView
    private void setupNavigationView() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.home_dashboard_fragment);
        assert navHostFragment != null;
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navHostFragment.getNavController());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}