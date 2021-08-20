package com.woa.addworkout;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.woa.R;
import com.woa.adapter.AddExercisesRecyclerAdapter;
import com.woa.dataClasses.Exercise;
import com.woa.dataClasses.ExerciseList;
import com.woa.databinding.CustomAddReminderDialogeBinding;
import com.woa.databinding.CustomDialogEditExerciseBinding;
import com.woa.databinding.FragmentAddExerciseBinding;
import com.woa.goals.GoalsActivity;
import com.woa.home.activity.HomeActivity;
import com.woa.receiver.BookCalenderReceiver;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static android.content.Context.ALARM_SERVICE;


public class AddExerciseFragment extends Fragment implements View.OnClickListener, AddExercisesRecyclerAdapter.OnItemClickListener {

    FragmentAddExerciseBinding binding;
    private final ArrayList<Exercise> exercisesList = new ArrayList<>();
    private AddExercisesRecyclerAdapter adapter;
    private ExerciseList exercise = new ExerciseList();
    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference logBookRef,goalRef;
    private String userId, exerciseDate,checkType,goalType,categoryName;
    private int hours, minutes, years, days, months;



    Random rand = new Random();
    int reqCode = rand.nextInt(100);
    private DatabaseReference reminderRef;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddExerciseBinding.inflate(inflater, container, false);

        setHasOptionsMenu(true);


        firebaseInitialization();
        getDataArguments();
        setDataOnType();
       // setCurrentDate();
        clickListeners();

        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setCurrentDate() {

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("d-M-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        binding.selectDateTv.setText(formattedDate);

    }

    @SuppressLint("SetTextI18n")
    private void setDataOnType() {

        if (checkType.equals("goals"))
        {
            binding.goalsLinearView.setVisibility(View.VISIBLE);
            binding.createNow.setVisibility(View.GONE);
            binding.toolBarText.setText(" Add Goals");
//            Toast.makeText(getContext(), checkType, Toast.LENGTH_SHORT).show();
            setGoalUserData();
        }
        if (checkType.equals("Enter Logs"))
        {
            binding.addWorkoutPlan.setVisibility(View.VISIBLE);
            binding.repsInputLayout.setVisibility(View.VISIBLE);
            binding.toolBarText.setText("Enter Logs");
            logBookRef = FirebaseDatabase.getInstance().getReference("Logbook");
//            Toast.makeText(getContext(), checkType, Toast.LENGTH_SHORT).show();
        }
        if (checkType.equals("TrackWeight"))
        {
            binding.addWorkoutPlan.setVisibility(View.VISIBLE);
            binding.toolBarText.setText("Add TrackWeight");
            logBookRef = FirebaseDatabase.getInstance().getReference("TrackWeight");
//            Toast.makeText(getContext(), checkType, Toast.LENGTH_SHORT).show();
        }

    }

    private void getDataArguments() {

        assert getArguments() != null;
        exercise = AddExerciseFragmentArgs.fromBundle(getArguments()).getExerciseData();
        checkType = AddExerciseFragmentArgs.fromBundle(getArguments()).getCheckType();
        categoryName = AddExerciseFragmentArgs.fromBundle(getArguments()).getCategoryName();
        binding.exerciseName.setText(exercise.getName());



    }

    private void firebaseInitialization() {

        mAuth = FirebaseAuth.getInstance();
        userId = Objects.requireNonNull(Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
        goalRef = FirebaseDatabase.getInstance().getReference("Goals").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()));
        reminderRef = FirebaseDatabase.getInstance().getReference("Reminders").child(FirebaseAuth.getInstance().getUid());

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void clickListeners() {

        binding.addButton.setOnClickListener(this);
        binding.selectDateTv.setOnClickListener(this);


        binding.createNow.setOnClickListener(v -> {

            if (binding.selectDateTv.getText().toString().isEmpty())
            {
                Toast.makeText(requireContext(), "Please Select Date", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if (exercisesList.size() != 0) {

                    showConfirmationDialog();

                } else
                {
                    Toast.makeText(requireContext(), "Add Some Exercises", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.back.setOnClickListener(v -> requireActivity().onBackPressed());

        binding.selectTime.setOnClickListener(v -> {

            setReminder();

        });


    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setReminder() {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        CustomAddReminderDialogeBinding reminderDialogBinding = CustomAddReminderDialogeBinding.bind(
                LayoutInflater.from(requireContext()).inflate(
                        R.layout.custom_add_reminder_dialoge,
                        null
                ));
        builder.setView(reminderDialogBinding.getRoot());
        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();


        reminderDialogBinding.selectDate.setOnClickListener(v -> {
            Calendar calendar;
            calendar = Calendar.getInstance();
            final int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);


            DatePickerDialog picker = new DatePickerDialog(requireContext(),
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        String dateS = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1;

                        years = year1;
                        months = monthOfYear;
                        days = dayOfMonth;

                        reminderDialogBinding.selectDateTv.setText(dateS);

                    }, year, month, day);

            picker.show();

        });

        reminderDialogBinding.selectTime.setOnClickListener(v -> {
            Calendar mcurrentTime = Calendar.getInstance();
            final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            final int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(requireContext(), (timePicker, selectedHour, selectedMinute) -> {

                hours = selectedHour;
                minutes = selectedMinute;

                String timeSet;
                if (selectedHour > 12) {
                    selectedHour -= 12;
                    timeSet = "PM";
                } else if (selectedHour == 0) {
                    selectedHour += 12;
                    timeSet = "AM";
                } else if (selectedHour == 12)
                    timeSet = "PM";
                else
                    timeSet = "AM";
                String minutes;
                if (selectedMinute < 10)
                    minutes = "0" + selectedMinute;
                else
                    minutes = String.valueOf(selectedMinute);

                String aTime = String.valueOf(selectedHour) + ':' + minutes + " " + timeSet;


                reminderDialogBinding.selectTimeTv.setText(aTime);
            }, hour, minute, false);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");

            mTimePicker.show();
        });

        reminderDialogBinding.save.setOnClickListener(view -> {
            final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.small_tone);
            mp.start();

            String reminderTitle = reminderDialogBinding.titleEt.getText().toString();
            String reminderDescription = reminderDialogBinding.descriptionEt.getText().toString();
            String date = reminderDialogBinding.selectDateTv.getText().toString();
            String time = reminderDialogBinding.selectTimeTv.getText().toString();

            if (reminderTitle.isEmpty()) {
                Toast.makeText(requireContext(), "Add Title", Toast.LENGTH_SHORT).show();
                return;
            }
            if (reminderDescription.isEmpty()) {
                Toast.makeText(requireContext(), "Add description", Toast.LENGTH_SHORT).show();
                return;
            }
            if (date.equals("Select Date") || time.equals("Select Time")) {

                Toast.makeText(requireContext(), "Select date and time", Toast.LENGTH_SHORT).show();
                return;
            }


            String key = reminderRef.push().getKey();

            Map<String, Object> map = new HashMap<>();
            map.put("description", reminderDescription);
            map.put("title", reminderTitle);
            map.put("date", date);
            map.put("time", time);
            map.put("selectedHour", hours);
            map.put("selectedMinute", minutes);
            map.put("year", years);
            map.put("day", days);
            map.put("month", months);
            map.put("alarmId", key);

            reminderRef.child(Objects.requireNonNull(key)).setValue(map).addOnCompleteListener(task -> {

                alertDialog.dismiss();
                setReminderAlarm(reminderTitle, reminderDescription);

            });


        });

        reminderDialogBinding.cancel.setOnClickListener(view -> alertDialog.dismiss());


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setReminderAlarm(String reminderTitle, String reminderDescription) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.clear();
        calendar.set(years, months, days, hours, minutes);
        System.out.println("date before aded: " + calendar.getTime());

        calendar.add(Calendar.DATE, 0);
        System.out.println("date after aded: " + calendar.getTime());

        Intent intent = new Intent(requireContext(), BookCalenderReceiver.class);
        intent.putExtra("title", reminderTitle);
        intent.putExtra("description", reminderDescription);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(requireContext(), reqCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) requireContext().getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(requireContext(), "Reminder was added", Toast.LENGTH_SHORT).show();
        System.out.println("setReminderAlarm: " + calendar.getTimeInMillis());
//        startActivity(new Intent(requireContext(), HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }




    private void setGoalUserData() {

        binding.exerciseTv.setText(exercise.getName());
        String[] ITEMS = {"Max Weight", "Max Reps"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.goalSpinner.setAdapter(adapter);
        binding.goalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                goalType = binding.goalSpinner.getSelectedItem().toString();
                if (goalType.equals("Max Weight"))
                {
                    binding.weightInputLayout.setVisibility(View.VISIBLE);
                    binding.repInputLayout.setVisibility(View.GONE);
                }
                if (goalType.equals("Max Reps"))
                {
                    binding.repInputLayout.setVisibility(View.VISIBLE);
                    binding.weightInputLayout.setVisibility(View.GONE);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        binding.selectStartDate.setOnClickListener(v -> {
            Calendar calendar;
            calendar = Calendar.getInstance();
            final int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);


            DatePickerDialog picker = new DatePickerDialog(requireContext(),
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        String dateS = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1;

                        years = year1;
                        months = monthOfYear;
                        days = dayOfMonth;

                        binding.selectStartDateTv.setText(dateS);

                    }, year, month, day);

            picker.show();

        });
        binding.selectEndDate.setOnClickListener(v -> {
            Calendar calendar;
            calendar = Calendar.getInstance();
            final int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);


            DatePickerDialog picker = new DatePickerDialog(requireContext(),
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        String dateS = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1;

                        years = year1;
                        months = monthOfYear;
                        days = dayOfMonth;

                        binding.selectEndDateTv.setText(dateS);

                    }, year, month, day);

            picker.show();

        });





        binding.save.setOnClickListener(v -> {

            final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.small_tone);
            mp.start();

            String sDate = binding.selectStartDateTv.getText().toString();
            String eDate = binding.selectEndDateTv.getText().toString();

            String key = goalRef.push().getKey();

            Map<String, Object> map = new HashMap<>();
            map.put("weight", Objects.requireNonNull(binding.gaolsWeight.getText()).toString());
            map.put("nameExercise", exercise.getName());
            map.put("categoryName", categoryName);
            map.put("reps", Objects.requireNonNull(binding.gaolReps.getText()).toString());
            map.put("goalsType", goalType);
            map.put("goalId", key);
            map.put("goalProgress", "0");
            map.put("timestamp",ServerValue.TIMESTAMP);

            goalRef.child(Objects.requireNonNull(key)).setValue(map).addOnCompleteListener(task -> {
                startActivity(new Intent(getContext(), GoalsActivity.class));
                getActivity().finish();
            });

        });



    }




    @Override
    public void onClick(View view) {

        if (view == binding.addButton) {

            if (Objects.requireNonNull(binding.weightKgsEt.getText()).toString().isEmpty()) {
                Toast toast = Toast.makeText(requireContext(), "Enter weight kgs", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
            } else {
                initRecycler();
            }
        }

        if (view == binding.selectDateTv)
        {
            datePicker();
        }

    }

    private void initRecycler() {

        exercisesList.add(new Exercise(Objects.requireNonNull(binding.weightKgsEt.getText()).toString(), Objects.requireNonNull(binding.repsEt.getText()).toString()));
        adapter = new AddExercisesRecyclerAdapter(requireContext(), exercisesList);
        LinearLayoutManager llm = new LinearLayoutManager(requireContext());
        binding.exercisesRecycler.setLayoutManager(llm);
        llm.setStackFromEnd(true);
        binding.exercisesRecycler.setAdapter(adapter);
//            binding.exercisesRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter.setOnItemClickListener(this);
        adapter.notifyItemInserted(exercisesList.size() - 1);

        binding.weightKgsEt.setText("");

        binding.repsEt.setText("");
    }

    @Override
    public void onDeleteClick(int position) {
        Toast.makeText(requireContext(), "Removed", Toast.LENGTH_SHORT).show();
        exercisesList.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onEditClick(int position) {
        Exercise exercise = exercisesList.get(position);
        showEditProductDialog(exercise, position);
    }

    private void showEditProductDialog(Exercise exercise, int position) {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        @SuppressLint("InflateParams") CustomDialogEditExerciseBinding editDialogBinding = CustomDialogEditExerciseBinding.bind(
                LayoutInflater.from(requireContext()).inflate(
                        R.layout.custom_dialog_edit_exercise,
                        null
                ));


        builder.setView(editDialogBinding.getRoot());
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        editDialogBinding.weightKgsEt.setText(exercise.getWeightKgs());
        editDialogBinding.repsEt.setText(exercise.getReps());

        if (checkType.equals("AddWorkout"))
        {
            editDialogBinding.repEditLinear.setVisibility(View.VISIBLE);
        }






        final int[] count = new int[1];
        editDialogBinding.plusImage.setOnClickListener(view -> {
            if (editDialogBinding.repsEt.getText().toString().isEmpty()) {

                count[0] = 0;
            } else {
                count[0] = Integer.parseInt(editDialogBinding.repsEt.getText().toString());
            }
            count[0]++;
            editDialogBinding.repsEt.setText(String.valueOf(count[0]));
            editDialogBinding.repsEt.setSelection(editDialogBinding.repsEt.getText().length());
        });

        editDialogBinding.minusImage.setOnClickListener(view -> {
            if (editDialogBinding.repsEt.getText().toString().isEmpty()) {
                count[0] = 0;
            } else {

                count[0] = Integer.parseInt(editDialogBinding.repsEt.getText().toString());
            }
            count[0]--;
            if (count[0] < 0) {

                count[0] = 0;

            }

            editDialogBinding.repsEt.setText(String.valueOf(count[0]));
            editDialogBinding.repsEt.setSelection(editDialogBinding.repsEt.getText().length());

        });

        editDialogBinding.confirmButton.setOnClickListener(view -> {


            exercisesList.set(position,
                    new Exercise(
                            editDialogBinding.weightKgsEt.getText().toString(),
                            editDialogBinding.repsEt.getText().toString()));
            adapter.notifyDataSetChanged();

            alertDialog.dismiss();
            Toast.makeText(requireContext(), "Updated", Toast.LENGTH_SHORT).show();
        });

        editDialogBinding.cancelButton.setOnClickListener(view -> alertDialog.dismiss());

    }

    private void createBandProduct() {
        binding.progressBar.setVisibility(View.VISIBLE);
        String date = binding.selectDateTv.getText().toString();
        final String productKey = logBookRef.push().getKey();
        Map<String, Object> orderMap = new HashMap<>();

        orderMap.put("time", ServerValue.TIMESTAMP);
        orderMap.put("name", exercise.getName());
        orderMap.put("categoryName", categoryName);
        orderMap.put("productKey", productKey);
        orderMap.put("date", date);
        orderMap.put("exercisesCount", exercisesList.size());
        orderMap.put("workoutProgress", "0");

        assert productKey != null;
        logBookRef = logBookRef.child(userId).child(productKey);
        logBookRef.setValue(orderMap)
                .addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                    System.out.println("CreateOrderActivity.createOrder: " + e);

                }).addOnCompleteListener(task -> {

            logBookRef = logBookRef.child("exercises");
            for (int i = 0; i < adapter.getItemCount(); i++) {

                final String exerciseKay = logBookRef.child(productKey).push().getKey();
                Exercise exercise;
                exercise = exercisesList.get(i);

                Map<String, Object> exerciseMap = new HashMap<>();
                exerciseMap.put("weightKgs", exercise.getWeightKgs());
                exerciseMap.put("reps", exercise.getReps());
                exerciseMap.put("exerciseKay", exerciseKay);

                assert exerciseKay != null;
                logBookRef.child(exerciseKay)
                        .setValue(exerciseMap).addOnCompleteListener(task1 -> System.out.println("task1 = " + task1)).addOnFailureListener(e -> {
                    binding.progressBar.setVisibility(View.GONE);
                    System.out.println("CreateOrderActivity.createOrder: " + e);
                });

            }

            Toast.makeText(requireContext(), "Successfully Add", Toast.LENGTH_SHORT).show();
            binding.progressBar.setVisibility(View.GONE);
            startActivity(new Intent(requireContext(), HomeActivity.class));
            requireActivity().finish();

        });



    }

//    @Override
//    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.create_band_product_menu, menu);
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        int itemId = item.getItemId();
//
//        if (itemId == R.id.order_now) {
//
//
//
//
//
//            return true;
//        } else return super.onOptionsItemSelected(item);
//    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Confirmation");
//        builder.setMessage("Are you sure you want to delete?\nYou can't undo this action");
        builder.setIcon(R.drawable.ic_baseline_check_circle);
        builder.setPositiveButton("Confirm", (dialog, which) -> {
            final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.small_tone);
            mp.start();

            createBandProduct();
            dialog.dismiss();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }


    private void datePicker() {

        Calendar calendar;
        calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        DatePickerDialog picker = new DatePickerDialog(requireContext(),
                (view, year1, monthOfYear, dayOfMonth) -> {
                    String dateS = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1;
                    years = year1;
                    months = monthOfYear;
                    days = dayOfMonth;
                    binding.selectDateTv.setText(dateS);
                    System.out.println("datesssss"+dateS);


                }, year, month, day);

        picker.show();
    }

}