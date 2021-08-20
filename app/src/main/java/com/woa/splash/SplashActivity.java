package com.woa.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.woa.R;
import com.woa.auth.activity.AuthActivity;
import com.woa.home.activity.HomeActivity;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        new Handler(Looper.getMainLooper())
                .postDelayed(() -> {

                            if (mAuth.getCurrentUser() != null) {
                                startActivity(new Intent(SplashActivity.this, HomeActivity.class));

                            } else {
                                startActivity(new Intent(SplashActivity.this, AuthActivity.class));
                            }
                            finish();
                        },
                        2000);
    }
}