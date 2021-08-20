package com.woa.addworkout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.Openable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.woa.R;
import com.woa.databinding.ActivityAddWorkoutBinding;

public class AddWorkoutActivity extends AppCompatActivity {

    private ActivityAddWorkoutBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddWorkoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_bands_container);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

    }

    //to navigate to back screens, starts here
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (Openable) null);
    }


}