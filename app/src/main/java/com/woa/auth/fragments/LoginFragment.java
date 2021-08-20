package com.woa.auth.fragments;

import android.content.Intent;
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
import com.woa.R;
import com.woa.databinding.FragmentLoginBinding;
import com.woa.home.activity.HomeActivity;

import org.jetbrains.annotations.NotNull;

public class LoginFragment extends Fragment implements View.OnClickListener {


    private FragmentLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_login, container, false);
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //fireBase Auth
        mAuth = FirebaseAuth.getInstance();

        binding.signUpTv.setOnClickListener(this);
        binding.forgotPassword.setOnClickListener(this);
        binding.loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == binding.signUpTv) {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
        }
        if (view == binding.forgotPassword) {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_resetPasswordFragment);
        }
        if (view == binding.loginButton) {

            validation();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void validation() {

        String email = binding.loginUsernameEt.getText().toString();
        String password = binding.loginPasswordEt.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.loginUsernameEt.setError("Enter the valid email");
            binding.loginUsernameEt.requestFocus();
            return;
        }

        if (password.isEmpty() || password.length() < 8) {
            binding.loginPasswordEt.setError("Enter the valid password");
            binding.loginPasswordEt.requestFocus();
            return;
        }


        loginUser(email, password);
    }

    private void loginUser(String email, String password) {

        binding.progressBarLayout.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                binding.progressBarLayout.setVisibility(View.GONE);
                binding.loginButton.setVisibility(View.VISIBLE);
                startActivity(new Intent(requireContext(), HomeActivity.class));
                requireActivity().finish();
                Toast.makeText(requireActivity(), "You are successfully Logged in", Toast.LENGTH_SHORT).show();
            }

        }).addOnFailureListener(e -> {
            binding.progressBarLayout.setVisibility(View.GONE);
            binding.loginButton.setVisibility(View.VISIBLE);
            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.println("LoginFragment.loginUser: "+e.getMessage());

        });
    }

}