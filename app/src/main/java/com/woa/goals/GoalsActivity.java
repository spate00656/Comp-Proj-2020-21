package com.woa.goals;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.woa.R;
import com.woa.adapter.GoalsRecyclerAdapter;
import com.woa.addworkout.AddWorkoutActivity;
import com.woa.dataClasses.Goals;
import com.woa.databinding.ActivityGoalsBinding;
import com.woa.databinding.ActivityHomeBinding;
import com.woa.databinding.CustomAddGoalsExerciseBinding;
import com.woa.databinding.CustomAddReminderDialogeBinding;
import com.woa.databinding.CustomGaolsDetailsBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GoalsActivity extends AppCompatActivity implements View.OnClickListener, GoalsRecyclerAdapter.OnItemClickListener {

    private ActivityGoalsBinding binding;
    private DatabaseReference goalRef;
    private int hours, minutes, years, days, months;
    private int totalProgress= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGoalsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        goalRef = FirebaseDatabase.getInstance().getReference("Goals").child(FirebaseAuth.getInstance().getUid());

        getGoals();
        binding.addReminderButton.setOnClickListener(this);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void getGoals() {

        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Goals> goalsList = new ArrayList<>();

        goalRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.progressBar.setVisibility(View.GONE);

                goalsList.clear();
                if (snapshot.exists()) {
                    binding.noReminderTv.setVisibility(View.GONE);

                    for (DataSnapshot dss : snapshot.getChildren()) {

                        Goals reminder = dss.getValue(Goals.class);

                        goalsList.add(reminder);

                    }


                } else binding.noReminderTv.setVisibility(View.VISIBLE);

                initRecyclerView(goalsList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.progressBar.setVisibility(View.GONE);

                Toast.makeText(GoalsActivity.this, "" + error.getDetails(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initRecyclerView(ArrayList<Goals> goalsList) {

        binding.remindersRecyclerView.setLayoutManager(new LinearLayoutManager(GoalsActivity.this));
        GoalsRecyclerAdapter adapter = new GoalsRecyclerAdapter(GoalsActivity.this, goalsList);
        binding.remindersRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {

        if (view == binding.addReminderButton) {
            addGoals();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addGoals() {

        Intent intent = new Intent(GoalsActivity.this, AddWorkoutActivity.class);
        intent.putExtra("from", "goals");
        startActivity(intent);
    }


    @Override
    public void onDetailsClick(Goals position) {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(GoalsActivity.this);
        CustomGaolsDetailsBinding reminderDialogBinding = CustomGaolsDetailsBinding.bind(
                LayoutInflater.from(GoalsActivity.this).inflate(
                        R.layout.custom_gaols_details,
                        null
                ));
        builder.setView(reminderDialogBinding.getRoot());
        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();



        // set data  for edit
        reminderDialogBinding.toolbar2.setTitle(position.getGoalsType());
        reminderDialogBinding.exerciseTv.setText(position.getNameExercise());


        int progress = Integer.parseInt(position.getGoalProgress());
        String progressText = "Complete " + progress +" % ";
        reminderDialogBinding.progress.setText(progressText);

        if (!position.getReps().isEmpty()) {
            reminderDialogBinding.target.setText(position.getReps() + "reps");
            reminderDialogBinding.repInputLayout.setVisibility(View.VISIBLE);
            reminderDialogBinding.gaolReps.setText(position.getReps());
            reminderDialogBinding.goalsRecordrepInputLayout.setVisibility(View.VISIBLE);

        }
        if (!position.getWeight().isEmpty()) {
            reminderDialogBinding.target.setText(position.getWeight() + "kgs");
            reminderDialogBinding.weightInputLayout.setVisibility(View.VISIBLE);
            reminderDialogBinding.gaolsWeight.setText(position.getWeight());
            reminderDialogBinding.goalsRecordweightInputLayout.setVisibility(View.VISIBLE);


        }

        reminderDialogBinding.selectStartDateTv.setText(position.getStartDate());
        reminderDialogBinding.selectEndDateTv.setText(position.getEndDate());

        reminderDialogBinding.selectStartDate.setOnClickListener(v -> {
            Calendar calendar;
            calendar = Calendar.getInstance();
            final int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);


            DatePickerDialog picker = new DatePickerDialog(GoalsActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        String dateS = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1;

                        years = year1;
                        months = monthOfYear;
                        days = dayOfMonth;

                        reminderDialogBinding.selectStartDateTv.setText(dateS);

                    }, year, month, day);

            picker.show();

        });
        reminderDialogBinding.selectEndDate.setOnClickListener(v -> {
            Calendar calendar;
            calendar = Calendar.getInstance();
            final int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);


            DatePickerDialog picker = new DatePickerDialog(GoalsActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        String dateS = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1;

                        years = year1;
                        months = monthOfYear;
                        days = dayOfMonth;

                        reminderDialogBinding.selectEndDateTv.setText(dateS);

                    }, year, month, day);

            picker.show();

        });





        reminderDialogBinding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sDate = reminderDialogBinding.selectStartDateTv.getText().toString();
                String eDate = reminderDialogBinding.selectEndDateTv.getText().toString();

                if (sDate.equals("Start Date") || eDate.equals("End Date")) {

                    Toast.makeText(GoalsActivity.this, "Select date", Toast.LENGTH_SHORT).show();
                    return;
                }


                Map<String, Object> map = new HashMap<>();
                map.put("weight", reminderDialogBinding.gaolsWeight.getText().toString());
                map.put("nameExercise", position.getNameExercise());
                map.put("categoryName", position.getCategoryName());
                map.put("reps", reminderDialogBinding.gaolReps.getText().toString());
                map.put("goalsType", position.getGoalsType());
                map.put("startDate", sDate);
                map.put("endDate", eDate);
                map.put("goalId", position.getGoalId());
                map.put("goalProgress", position.getGoalProgress());
                map.put("timestamp", ServerValue.TIMESTAMP);

                goalRef.child(Objects.requireNonNull(position.getGoalId())).updateChildren(map).addOnCompleteListener(task -> {
                    alertDialog.dismiss();
                });

            }
        });

        reminderDialogBinding.goalsRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!position.getReps().isEmpty()) {

                    String getRepProgress = Objects.requireNonNull(reminderDialogBinding.goalsRecordReps.getText()).toString();
                    int progressnumber = Integer.parseInt(getRepProgress);
                    String ExistingProgress = position.getGoalProgress();
                    int ExistingProgressNumber = Integer.parseInt(ExistingProgress);

                    totalProgress = progressnumber+ExistingProgressNumber;
                    int totalReps = Integer.parseInt(position.getReps());

                    if (totalProgress>totalReps)
                    {
                        Toast.makeText(GoalsActivity.this, "Please Enter the Correct Reps", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Map<String, Object> map = new HashMap<>();
                        map.put("goalProgress", String.valueOf(totalProgress));
                        goalRef.child(Objects.requireNonNull(position.getGoalId())).updateChildren(map).addOnCompleteListener(task -> {
                            totalProgress = 0;
                            final MediaPlayer mp = MediaPlayer.create(GoalsActivity.this, R.raw.small_sms_tone);
                            mp.start();
                            alertDialog.dismiss();
                        });
                    }



                }
                if (!position.getWeight().isEmpty()) {

                    String getWeightProgress = Objects.requireNonNull(reminderDialogBinding.goalsRecordWeight.getText()).toString();
                    int progressnumber = Integer.parseInt(getWeightProgress);
                    String ExistingProgress = position.getGoalProgress();
                    int ExistingProgressNumber = Integer.parseInt(ExistingProgress);
                    totalProgress = progressnumber+ExistingProgressNumber;

                    int totalWeight = Integer.parseInt(position.getWeight());

                    if (totalProgress>totalWeight)
                    {
                        Toast.makeText(GoalsActivity.this, "Please Enter the Correct Weight", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Map<String, Object> map = new HashMap<>();
                        map.put("goalProgress", String.valueOf(totalProgress));
                        goalRef.child(Objects.requireNonNull(position.getGoalId())).updateChildren(map).addOnCompleteListener(task -> {
                            totalProgress = 0;
                            final MediaPlayer mp = MediaPlayer.create(GoalsActivity.this, R.raw.small_sms_tone);
                            mp.start();
                            alertDialog.dismiss();
                        });
                    }

                }



            }
        });


        reminderDialogBinding.deleteGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goalRef = FirebaseDatabase.getInstance().getReference("Goals").child(FirebaseAuth.getInstance().getUid());
               goalRef.child(position.getGoalId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void aVoid) {
                       alertDialog.dismiss();
                   }
               });
            }
        });

        reminderDialogBinding.editGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminderDialogBinding.viewGaolLinear.setVisibility(View.GONE);
                reminderDialogBinding.goalsEditLinearView.setVisibility(View.VISIBLE);

            }
        });
        reminderDialogBinding.recordGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminderDialogBinding.viewGaolLinear.setVisibility(View.GONE);
                reminderDialogBinding.goalsRecordLinearView.setVisibility(View.VISIBLE);

            }
        });




    }
}