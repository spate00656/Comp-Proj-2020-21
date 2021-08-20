package com.woa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.woa.dataClasses.Exercise;
import com.woa.databinding.CustomLayoutExerciseListBinding;

import java.util.ArrayList;

public class ExercisesRecyclerAdapter extends RecyclerView.Adapter<ExercisesRecyclerAdapter.ViewHolder> {

    private Context context;
    ArrayList<Exercise> exercisesList;

    private ExercisesRecyclerAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);

        void onEditClick(int position);
    }

    public void setOnItemClickListener(ExercisesRecyclerAdapter.OnItemClickListener listener) {
        mListener = listener;
    }


    public ExercisesRecyclerAdapter(Context context, ArrayList<Exercise> exercisesList) {

        this.context = context;
        this.exercisesList = exercisesList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CustomLayoutExerciseListBinding binding = CustomLayoutExerciseListBinding
                .inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false);


        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Exercise exercise = exercisesList.get(position);
        holder.bind(exercise);

    }


    @Override
    public int getItemCount() {
        return exercisesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CustomLayoutExerciseListBinding binding;

        public ViewHolder(CustomLayoutExerciseListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Exercise exercise) {
            String reps = "X "+exercise.getReps();
            binding.repsTv.setText(reps);
            binding.exerciseTv.setText(exercise.getWeightKgs());

        }
    }


}
