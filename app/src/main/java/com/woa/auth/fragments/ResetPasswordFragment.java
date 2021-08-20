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

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.woa.R;
import com.woa.databinding.FragmentResetPasswordBinding;

import org.jetbrains.annotations.NotNull;

public class ResetPasswordFragment extends Fragment implements View.OnClickListener {

    FragmentResetPasswordBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.sendButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == binding.sendButton) {

            resetPassword();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }


    private void resetPassword() {

        String email = binding.emailEt.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEt.setError("Enter your registered email");
            binding.emailEt.requestFocus();

        } else {
            binding.progressBar.setVisibility(View.VISIBLE);

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {

                    binding.progressBar.setVisibility(View.GONE);
                    binding.sendButton.setVisibility(View.VISIBLE);
//                    Toast.makeText(requireActivity(), "Please check your email to reset Password", Toast.LENGTH_LONG).show();

                    showMessageDialog();
                } else {

                    binding.progressBar.setVisibility(View.GONE);
                    binding.sendButton.setVisibility(View.VISIBLE);
                    Toast.makeText(requireActivity(), "Error in sending email", Toast.LENGTH_SHORT).show();


                }

            }).addOnFailureListener(e -> {
                binding.progressBar.setVisibility(View.GONE);
                binding.sendButton.setVisibility(View.VISIBLE);
                Toast.makeText(requireActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            });

        }
    }

    private void showMessageDialog() {

        new MaterialAlertDialogBuilder(requireActivity())
                .setCancelable(false)
                .setTitle("Check your email to reset password")
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    Navigation.findNavController(binding.sendButton).navigate(R.id.action_resetPasswordFragment_to_loginFragment);
                }).show();
    }

}