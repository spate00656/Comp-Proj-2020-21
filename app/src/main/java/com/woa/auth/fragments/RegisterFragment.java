package com.woa.auth.fragments;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.woa.R;
import com.woa.databinding.FragmentRegisterBinding;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class RegisterFragment extends Fragment implements View.OnClickListener {

    private FragmentRegisterBinding binding;
    private DatabaseReference usersRef;
    private FirebaseAuth mAuth;
    private String userId;
//    private AwesomeValidation mAwesomeValidation;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // firebase Reference
        usersRef = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();


        /*mAwesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        // mAwesomeValidation.setTextInputLayoutErrorTextAppearance(R.style.TextInputLayoutErrorStyle); // optional, default color is holo_red_light if not set
        // by default, it automatically sets focus to the first failed input field after validation is triggered
        // you can disable this behavior by
        // AwesomeValidation.disableAutoFocusOnFirstFailure();
        addValidations();*/

        binding.signUpButton.setOnClickListener(this);
        binding.back.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
        });

        binding.signInTv.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
        });


    }

    @Override
    public void onClick(View view) {

        if (view == binding.signUpButton) {
//            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment);
            addValidations();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void addValidations() {

        //adding validation to edit texts
       /* mAwesomeValidation.addValidation(requireActivity(), R.id.user_name_et, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.name_error);
        mAwesomeValidation.addValidation(requireActivity(), R.id.email_et, RegexTemplate.NOT_EMPTY, R.string.address_error);
        mAwesomeValidation.addValidation(requireActivity(), R.id.email_et, Patterns.EMAIL_ADDRESS, R.string.email_error);
        mAwesomeValidation.addValidation(requireActivity(), R.id.phone_no_et, "^[2-9]{2}[0-9]{8}$", R.string.phone_no);
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        mAwesomeValidation.addValidation(requireActivity(), R.id.password_et, regexPassword, R.string.password_error);
        mAwesomeValidation.addValidation(requireActivity(), R.id.confirm_password_et, R.id.password_et, R.string.password_match_error);
        mAwesomeValidation.addValidation(requireActivity(), R.id.address_et, RegexTemplate.NOT_EMPTY, R.string.address_error);*/


        String userName = binding.userNameEt.getText().toString();
        String userEmail = binding.emailEt.getText().toString();
        String userPassword = binding.passwordEt.getText().toString();
        String userConfPswrd = binding.confirmPasswordEt.getText().toString();


        if (userName.isEmpty()) {
            binding.userNameEt.setError(getResources().getString(R.string.name_error));
            binding.userNameEt.requestFocus();
            return;
        }


        if (userEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            binding.emailEt.setError(getResources().getString(R.string.email_error));
            binding.emailEt.requestFocus();
            return;
        }



        if (userPassword.length() < 8) {
            binding.passwordEt.setError(getResources().getString(R.string.password_error));
            binding.passwordEt.requestFocus();
            return;
        }

        if (userConfPswrd.isEmpty()) {
            binding.confirmPasswordEt.setError(getResources().getString(R.string.password_match_error));
            binding.confirmPasswordEt.requestFocus();
            return;
        }

        registerUser(userName, userEmail, userPassword);
    }

    private void registerUser(String userName, String userEmail, String userPassword) {

        binding.progressBar.setVisibility(View.VISIBLE);
        binding.linear.setVisibility(View.GONE);
        mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(task -> {

                    System.out.println("RegisterFragment.onComplete");
                    FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                    if (mFirebaseUser != null) {
                        userId = mFirebaseUser.getUid(); //Do what you need to do with the id
                    }

                    if (task.isSuccessful()) {
                        Map<String, Object> map = new HashMap<>();

                        map.put("userId", userId);
                        map.put("userName", userName);
                        map.put("userEmail", userEmail);
                        usersRef.child(userId).setValue(map).addOnCompleteListener(task1 -> {

                            mAuth.signOut();
                            binding.progressBar.setVisibility(View.GONE);
                            binding.linear.setVisibility(View.VISIBLE);
                            Toast.makeText(requireContext(), "Successfully Sign Up", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(binding.linear).navigate(R.id.action_registerFragment_to_loginFragment);

                        }).addOnFailureListener(e -> {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.linear.setVisibility(View.VISIBLE);
                            Toast.makeText(requireContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        });
                    }

                }).addOnFailureListener(e -> {

                    binding.progressBar.setVisibility(View.GONE);
                    binding.linear.setVisibility(View.VISIBLE);
                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }


}