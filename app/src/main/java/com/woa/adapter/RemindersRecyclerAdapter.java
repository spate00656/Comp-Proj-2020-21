package com.woa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.woa.dataClasses.Reminder;
import com.woa.databinding.CustomLayoutRemindersListBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RemindersRecyclerAdapter extends RecyclerView.Adapter<RemindersRecyclerAdapter.ViewHolder> {

    private final Context context;
    ArrayList<Reminder> remindersList;

    private RemindersRecyclerAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onDeleteClick(Reminder position);

        void onEditClick(int position);
    }

    public void setOnItemClickListener(RemindersRecyclerAdapter.OnItemClickListener listener) {
        mListener = listener;
    }


    public RemindersRecyclerAdapter(Context context, ArrayList<Reminder> remindersList) {

        this.context = context;
        this.remindersList = remindersList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        CustomLayoutRemindersListBinding binding = CustomLayoutRemindersListBinding
                .inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false);


        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Reminder reminder = remindersList.get(position);
        holder.bind(reminder);

    }


    @Override
    public int getItemCount() {
        return remindersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CustomLayoutRemindersListBinding binding;

        public ViewHolder(CustomLayoutRemindersListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.deleteReminder.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Reminder reminder  =  remindersList.get(position);
                    mListener.onDeleteClick(reminder);

                }
            });
        }

        public void bind(Reminder reminder) {
            binding.titleTv.setText(reminder.getTitle());
            binding.descriptionTv.setText(reminder.getDescription());
            binding.dateTv.setText(reminder.getDate());
            binding.timeTv.setText(reminder.getTime());
        }
    }


}
