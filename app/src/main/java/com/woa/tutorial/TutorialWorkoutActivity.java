package com.woa.tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.woa.R;
import com.woa.adapter.RecyclerViewAdapter;
import com.woa.databinding.ActivityTutorialWorkoutBinding;

import java.util.ArrayList;
import java.util.Objects;

public class TutorialWorkoutActivity extends AppCompatActivity {


    private ActivityTutorialWorkoutBinding binding;
    //vars
    private final ArrayList<String> mNames = new ArrayList<>();
    private final ArrayList<String> mVideoUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTutorialWorkoutBinding.inflate(getLayoutInflater());
        setTitle("Workout Tutorial");
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_outline_arrow_back);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getImages();
    }

    private void getImages() {

        mVideoUrls.add("https://firebasestorage.googleapis.com/v0/b/woa-fitness-86f15.appspot.com/o/How%20To_%20Barbell%20Bench%20Press.mp4%20-%20Google%20Drive.mp4?alt=media&token=2d8cb60f-4c23-4003-bb09-6381d9047c52");
        mNames.add("Side planks");

        mVideoUrls.add("https://firebasestorage.googleapis.com/v0/b/woa-fitness-86f15.appspot.com/o/new1.mp4?alt=media&token=95b78ae4-1872-49c4-a664-6ec0e3b32c69");
        mNames.add("Burpees");

        mVideoUrls.add("https://firebasestorage.googleapis.com/v0/b/woa-fitness-86f15.appspot.com/o/new2.mp4?alt=media&token=aa422623-5a86-4bb4-92ec-c9a12aa5dbbd");
        mNames.add("Single-leg deadlifts");

        mVideoUrls.add("https://firebasestorage.googleapis.com/v0/b/woa-fitness-86f15.appspot.com/o/How%20To_%20Barbell%20Bench%20Press.mp4%20-%20Google%20Drive.mp4?alt=media&token=2d8cb60f-4c23-4003-bb09-6381d9047c52");
        mNames.add("Dumbbell rows");

        mVideoUrls.add("https://firebasestorage.googleapis.com/v0/b/woa-fitness-86f15.appspot.com/o/new1.mp4?alt=media&token=95b78ae4-1872-49c4-a664-6ec0e3b32c69");
        mNames.add("Dumbbell rows");


        initRecyclerView();

    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(TutorialWorkoutActivity.this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_tp);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(TutorialWorkoutActivity.this, mNames, mVideoUrls);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        // Handle item selection
        if (itemId == android.R.id.home) {
            this.finish();
            return true;
        } else return super.onOptionsItemSelected(item);
    }


    }
