package com.woa.home.fragments.exerciseAssistance;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.woa.RepMaxCalculator.RepMaxCalculatorActivity;
import com.woa.databinding.FragmentExerciseAssistanceBinding;
import com.woa.tutorial.TutorialWorkoutActivity;

public class ExerciseAssistanceFragment extends Fragment {

    private FragmentExerciseAssistanceBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExerciseAssistanceBinding.inflate(inflater, container, false);


        // click listener TutorialWorkout
        binding.cardVideoTutorials.setOnClickListener(v -> startActivity(new Intent(getContext(), TutorialWorkoutActivity.class)));

        // click listener RepMaxCalculator
        binding.cardCalculator.setOnClickListener(v -> startActivity(new Intent(getContext(), RepMaxCalculatorActivity.class)));


        return binding.getRoot();
    }
}