package com.woa.home.fragments.stayontrack;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.woa.R;
import com.woa.databinding.FragmentStayOnTrackBinding;
import com.woa.goals.GoalsActivity;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class StayOnTrackFragment extends Fragment {

    //Binding
    FragmentStayOnTrackBinding binding;
    FirebaseAuth mAuth;
    DatabaseReference userReference;
    String userId;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentStayOnTrackBinding.inflate(inflater, container, false);

        firebaseInitialization(); // firebaseInitialization
        clickListeners(); // listeners
        return binding.getRoot();
    }

    private void clickListeners() {

        binding.cardGoals.setOnClickListener(v -> startActivity(new Intent(getContext(), GoalsActivity.class)));
        binding.cardNotification.setOnClickListener(v -> Navigation.findNavController(binding.cardNotification).navigate(R.id.action_stayOnTrackFragment_to_reminderFragment));


    }



    private void firebaseInitialization() {
        // firebase Reference
        mAuth = FirebaseAuth.getInstance();
        userId = Objects.requireNonNull(Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
        userReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

    }




}