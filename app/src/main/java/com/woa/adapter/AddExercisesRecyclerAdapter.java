package com.woa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.woa.dataClasses.Exercise;
import com.woa.databinding.CustomLayoutAddExercisesListBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AddExercisesRecyclerAdapter extends RecyclerView.Adapter<AddExercisesRecyclerAdapter.ViewHolder> {

    private final Context context;
    ArrayList<Exercise> createdOrderList;

    private AddExercisesRecyclerAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);

        void onEditClick(int position);
    }

    public void setOnItemClickListener(AddExercisesRecyclerAdapter.OnItemClickListener listener) {
        mListener = listener;
    }


    public AddExercisesRecyclerAdapter(Context context, ArrayList<Exercise> createdOrderList) {

        this.context = context;
        this.createdOrderList = createdOrderList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        CustomLayoutAddExercisesListBinding binding = CustomLayoutAddExercisesListBinding
                .inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false);


        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Exercise exercise = createdOrderList.get(position);
        holder.bind(exercise);

    }


    @Override
    public int getItemCount() {
        return createdOrderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CustomLayoutAddExercisesListBinding binding;

        public ViewHolder(CustomLayoutAddExercisesListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.cancelButton.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onDeleteClick(position);

                }
            });
            binding.editButton.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onEditClick(position);

                }
            });
        }

        public void bind(Exercise exercise) {
            if(exercise.getReps().isEmpty())
            {
                binding.reps.setVisibility(View.GONE);
                binding.repsTv.setVisibility(View.GONE);
            }
            binding.repsTv.setText(exercise.getReps());
            binding.exerciseTv.setText(exercise.getWeightKgs());

        }
    }


}
