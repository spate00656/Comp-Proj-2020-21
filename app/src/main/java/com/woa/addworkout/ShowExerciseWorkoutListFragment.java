package com.woa.addworkout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.woa.R;
import com.woa.adapter.ExerciseListShowRecyclerAdapter;
import com.woa.dataClasses.ExerciseList;
import com.woa.databinding.CustomAddExtraExerciseBinding;
import com.woa.databinding.CustomAddReminderDialogeBinding;
import com.woa.databinding.FragmentShowExerciseListBinding;
import com.woa.databinding.FragmentShowExerciseWorkoutListBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.woa.addworkout.ShowExerciseWorkoutListFragmentDirections.actionShowExerciseWorkoutListFragmentToAddExerciseFragment4;

public class ShowExerciseWorkoutListFragment extends Fragment implements  ExerciseListShowRecyclerAdapter.OnItemClickListener  {


    private FragmentShowExerciseWorkoutListBinding binding;
    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference exercisesRef;
    private String exerciseName,userId;
    ArrayList<ExerciseList> ExerciseList = new ArrayList<>();
    private String selectDateTv ,checkType;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentShowExerciseWorkoutListBinding.inflate(inflater, container, false);
        exerciseName = ShowExerciseWorkoutListFragmentArgs.fromBundle(getArguments()).getExerciseName();
        checkType = ShowExerciseWorkoutListFragmentArgs.fromBundle(getArguments()).getCheckType();

        binding.exerciseNames.setText(exerciseName);

        firebaseInitialization();
        clickListerners();
        getExercise();

    return binding.getRoot();
    }

    private void clickListerners() {
        binding.addButton.setOnClickListener(v -> {

            addFunction();
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

    }

    private void addFunction() {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        CustomAddExtraExerciseBinding reminderDialogBinding = CustomAddExtraExerciseBinding.bind(
                LayoutInflater.from(requireContext()).inflate(
                        R.layout.custom_add_extra_exercise,
                        null
                ));
        builder.setView(reminderDialogBinding.getRoot());
        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();


        reminderDialogBinding.save.setOnClickListener(view -> {
            String reminderTitle = reminderDialogBinding.titleEt.getText().toString();

            if (reminderTitle.isEmpty()) {
                Toast.makeText(requireContext(), "Add Exercise Name", Toast.LENGTH_SHORT).show();
                return;
            }

            final String pushKey = exercisesRef.push().getKey();
            Map<String, Object> map = new HashMap<>();
            map.put("name",reminderTitle);
            map.put("exerciseId",pushKey);
            exercisesRef.child(exerciseName).child(pushKey).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    alertDialog.dismiss();
                }
            });


        });

        reminderDialogBinding.cancel.setOnClickListener(view -> alertDialog.dismiss());



    }

    private void firebaseInitialization() {
        mAuth = FirebaseAuth.getInstance();
        userId = Objects.requireNonNull(mAuth.getCurrentUser().getUid());
        exercisesRef = FirebaseDatabase.getInstance().getReference("Exercises");
    }

    private void getExercise() {
        binding.progressBar.setVisibility(View.VISIBLE);
        exercisesRef.child(exerciseName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ExerciseList.clear();
                binding.progressBar.setVisibility(View.GONE);

                if (snapshot.exists()) {

                    for (DataSnapshot dss : snapshot.getChildren()) {


                        ExerciseList product = dss.getValue(ExerciseList.class);

                        System.out.println("surname="+dss);

                        ExerciseList.add(product);


                    }


                } else {
                    binding.noDataMessage.setVisibility(View.VISIBLE);

                }

                initRecycler(ExerciseList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initRecycler(ArrayList<com.woa.dataClasses.ExerciseList> exerciseList) {

        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ExerciseListShowRecyclerAdapter adapter = new ExerciseListShowRecyclerAdapter(getContext(), exerciseList);
        binding.recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }


    @Override
    public void onListItemClick(com.woa.dataClasses.ExerciseList bandProduct) {
        ShowExerciseWorkoutListFragmentDirections.ActionShowExerciseWorkoutListFragmentToAddExerciseFragment4 action = actionShowExerciseWorkoutListFragmentToAddExerciseFragment4(bandProduct,checkType,exerciseName);
        Navigation.findNavController(getView()).navigate(action);
    }

}