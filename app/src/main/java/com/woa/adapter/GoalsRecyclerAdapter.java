package com.woa.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.woa.dataClasses.Goals;
import com.woa.databinding.CustomGoalDesingBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GoalsRecyclerAdapter extends RecyclerView.Adapter<GoalsRecyclerAdapter.ViewHolder> {

    private final Context context;
    ArrayList<Goals> remindersList;
    float defaultValue ;
    String progressText;

    private GoalsRecyclerAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onDetailsClick(Goals position);

    }

    public void setOnItemClickListener(GoalsRecyclerAdapter.OnItemClickListener listener) {
        mListener = listener;
    }


    public GoalsRecyclerAdapter(Context context, ArrayList<Goals> remindersList) {

        this.context = context;
        this.remindersList = remindersList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        CustomGoalDesingBinding binding = CustomGoalDesingBinding
                .inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false);


        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Goals reminder = remindersList.get(position);
        holder.bind(reminder);

    }


    @Override
    public int getItemCount() {
        return remindersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CustomGoalDesingBinding binding;

        public ViewHolder(CustomGoalDesingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.details.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Goals reminder = remindersList.get(position);
                    mListener.onDetailsClick(reminder);

                }
            });
        }

        @SuppressLint("SetTextI18n")
        public void bind(Goals reminder) {
            binding.exerciseName.setText(reminder.getNameExercise());
            binding.goalsType.setText(reminder.getGoalsType());
            binding.starteDate.setText(reminder.getStartDate());
            binding.endDate.setText(reminder.getEndDate());

            if (!reminder.getWeight().isEmpty()) {
                binding.linearWeightRep.setVisibility(View.GONE);
                binding.linearWeight.setVisibility(View.VISIBLE);
                binding.linearRep.setVisibility(View.GONE);
                binding.weightTv.setText(reminder.getWeight());
                defaultValue = Integer.parseInt(reminder.getWeight());


            } else if (!reminder.getReps().isEmpty()) {
                binding.linearWeightRep.setVisibility(View.GONE);
                binding.linearWeight.setVisibility(View.GONE);
                binding.linearRep.setVisibility(View.VISIBLE);
                binding.repsTv.setText(reminder.getReps());
                defaultValue = Integer.parseInt(reminder.getReps());
            }


            float progress = Integer.parseInt(reminder.getGoalProgress());


            // percentage
           float percentage = (float)((progress / defaultValue) * 100);


            System.out.println("progress"+progress);
            System.out.println("defaultValue"+defaultValue);
            System.out.println("finalValue"+percentage);
            int lastValue = (int) percentage;
            System.out.println("lastValue"+percentage);


            binding.progressBar.setProgress(lastValue);
            String value = String.valueOf(lastValue);
             progressText = "Complete " + value +" % ";
            binding.progressBarText.setText(progressText);



        }
    }


}
