package com.woa.home.fragments.Reminder;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.woa.R;
import com.woa.adapter.RemindersRecyclerAdapter;
import com.woa.dataClasses.Reminder;
import com.woa.databinding.CustomAddReminderDialogeBinding;
import com.woa.receiver.BookCalenderReceiver;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static android.content.Context.ALARM_SERVICE;

public class ReminderFragment extends Fragment implements View.OnClickListener, RemindersRecyclerAdapter.OnItemClickListener {

   com.woa.databinding.FragmentReminderBinding binding;
    private int hours, minutes, years, days, months;
    private DatabaseReference reminderRef;
    Random rand = new Random();
    int reqCode = rand.nextInt(100);

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = com.woa.databinding.FragmentReminderBinding.inflate(inflater, container, false);
        reminderRef = FirebaseDatabase.getInstance().getReference("Reminders").child(FirebaseAuth.getInstance().getUid());

        // reminders
        getReminders();
        binding.addReminderButton.setOnClickListener(this);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });
        return binding.getRoot();
    }

    private void getReminders() {

        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<Reminder> remindersList = new ArrayList<>();

        reminderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.progressBar.setVisibility(View.GONE);

                remindersList.clear();
                if (snapshot.exists()) {
                    binding.noReminderTv.setVisibility(View.GONE);

                    for (DataSnapshot dss : snapshot.getChildren()) {

                        Reminder reminder = dss.getValue(Reminder.class);

                        remindersList.add(reminder);

                    }


                } else binding.noReminderTv.setVisibility(View.VISIBLE);

                initRecyclerView(remindersList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.progressBar.setVisibility(View.GONE);

                Toast.makeText(requireContext(), "" + error.getDetails(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initRecyclerView(ArrayList<Reminder> remindersList) {

        binding.remindersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RemindersRecyclerAdapter adapter = new RemindersRecyclerAdapter(getContext(), remindersList);
        binding.remindersRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {

        if (view == binding.addReminderButton) {
            addBookingReminder();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addBookingReminder() {

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

    @Override
    public void onDeleteClick(Reminder reminder) {

        reminderRef.child(reminder.getAlarmId()).removeValue().addOnSuccessListener(aVoid -> {
            Intent intent = new Intent(requireContext(), BookCalenderReceiver.class);
            intent.putExtra("title", reminder.getTitle());
            intent.putExtra("description", reminder.getDescription());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(requireContext(), reqCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) requireContext().getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
            Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show();


        }).addOnFailureListener(e ->
                Toast.makeText(requireContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show());

    }

    @Override
    public void onEditClick(int position) {

    }
}