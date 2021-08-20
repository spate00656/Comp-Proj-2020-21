package com.woa.home.fragments.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.woa.R;
import com.woa.auth.activity.AuthActivity;
import com.woa.dataClasses.Users;
import com.woa.databinding.FragmentProfileBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    FirebaseAuth mAuth;
    DatabaseReference userReference;
    String userId;
    Users users;
    Activity mActivity;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseInitialization();
        getUserProfileData();
        clickListeners();


    }

    private void clickListeners() {
        binding.logout.setOnClickListener(view1 -> {
            mAuth.signOut();
            startActivity(new Intent(requireActivity(), AuthActivity.class));
            requireActivity().finish();
        });


        binding.editProfile.setOnClickListener(view1 -> startActivity(new Intent(requireActivity(), EditProfileActivity.class)));


    }

    private void getUserProfileData() {


        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    users = snapshot.getValue(Users.class);
                    assert users != null;
                    if (users.getImage() != null) {
                        if (mActivity == null) {
                            return;
                        }
                        Glide.with(mActivity).load(users.getImage()).into(binding.profilePhoto);
                    } else {
                        Glide.with(mActivity).load(R.drawable.ic_baseline_person_24).into(binding.profilePhoto);
                    }
                    binding.userName.setText(users.getUserName());
                    binding.userEmail.setText(users.getUserEmail());
                    binding.name.setText(users.getUserName());
                    binding.email.setText(users.getUserEmail());


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void firebaseInitialization() {
        // firebase Reference
        mAuth = FirebaseAuth.getInstance();
        userId = Objects.requireNonNull(Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
        userReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);

        mActivity = getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }


}