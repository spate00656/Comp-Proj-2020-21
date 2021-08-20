package com.woa.addworkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.woa.R;
import com.woa.databinding.FragmentShowExerciseListBinding;

import org.jetbrains.annotations.NotNull;

import static com.woa.addworkout.ShowExerciseListFragmentDirections.actionShowExerciseListFragmentToShowExerciseWorkoutListFragment;

public class ShowExerciseListFragment extends Fragment {

    private FragmentShowExerciseListBinding binding;
    private String type;


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShowExerciseListBinding.inflate(inflater, container, false);

        Bundle b = getActivity().getIntent().getExtras();
        if (b != null){
             type = b.getString("from");
        }

        clickListeners();


        return binding.getRoot();

    }

    private void clickListeners() {
        binding.shoulders.setOnClickListener(v -> {
            ShowExerciseListFragmentDirections.ActionShowExerciseListFragmentToShowExerciseWorkoutListFragment action = actionShowExerciseListFragmentToShowExerciseWorkoutListFragment("Shoulders",type);
            Navigation.findNavController(getView()).navigate(action);
        });

        binding.triceps.setOnClickListener(v -> {
            ShowExerciseListFragmentDirections.ActionShowExerciseListFragmentToShowExerciseWorkoutListFragment action = actionShowExerciseListFragmentToShowExerciseWorkoutListFragment("Triceps",type);
            Navigation.findNavController(getView()).navigate(action);
        });

        binding.biceps.setOnClickListener(v -> {
            ShowExerciseListFragmentDirections.ActionShowExerciseListFragmentToShowExerciseWorkoutListFragment action = actionShowExerciseListFragmentToShowExerciseWorkoutListFragment("Biceps",type);
            Navigation.findNavController(getView()).navigate(action);
        });
        binding.chest.setOnClickListener(v -> {
            ShowExerciseListFragmentDirections.ActionShowExerciseListFragmentToShowExerciseWorkoutListFragment action = actionShowExerciseListFragmentToShowExerciseWorkoutListFragment("Chest",type);
            Navigation.findNavController(getView()).navigate(action);
        });
        binding.back.setOnClickListener(v -> {
            ShowExerciseListFragmentDirections.ActionShowExerciseListFragmentToShowExerciseWorkoutListFragment action = actionShowExerciseListFragmentToShowExerciseWorkoutListFragment("Back",type);
            Navigation.findNavController(getView()).navigate(action);
        });
        binding.legs.setOnClickListener(v -> {
            ShowExerciseListFragmentDirections.ActionShowExerciseListFragmentToShowExerciseWorkoutListFragment action = actionShowExerciseListFragmentToShowExerciseWorkoutListFragment("Legs",type);
            Navigation.findNavController(getView()).navigate(action);
        });
        binding.abs.setOnClickListener(v -> {
            ShowExerciseListFragmentDirections.ActionShowExerciseListFragmentToShowExerciseWorkoutListFragment action = actionShowExerciseListFragmentToShowExerciseWorkoutListFragment("Abs",type);
            Navigation.findNavController(getView()).navigate(action);
        });

        binding.cardio.setOnClickListener(v -> {
            ShowExerciseListFragmentDirections.ActionShowExerciseListFragmentToShowExerciseWorkoutListFragment action = actionShowExerciseListFragmentToShowExerciseWorkoutListFragment("Cardio",type);
            Navigation.findNavController(getView()).navigate(action);

        });
        binding.addProductButton.setOnClickListener(v -> {
            Navigation.findNavController(binding.addProductButton).navigate(R.id.action_showExerciseListFragment_to_addWorkoutPlanFragment2);
        });

        binding.backe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });


    }



}