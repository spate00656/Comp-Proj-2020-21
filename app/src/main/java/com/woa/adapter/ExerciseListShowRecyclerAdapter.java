package com.woa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.woa.dataClasses.ExerciseList;
import com.woa.databinding.CustomExerciseViewListBinding;

import java.util.ArrayList;


public class ExerciseListShowRecyclerAdapter extends RecyclerView.Adapter<ExerciseListShowRecyclerAdapter.BandsViewHolder> {

    private Context context;
   ExerciseListShowRecyclerAdapter.OnItemClickListener mListener;
    private final ArrayList<ExerciseList> exerciseList;
    private ArrayList<Integer> noOfItemsList;


    public ExerciseListShowRecyclerAdapter(Context context, ArrayList<ExerciseList> exerciseList) {
        this.context = context;
        this.exerciseList = exerciseList;
    }

    public interface OnItemClickListener {

        void onListItemClick(ExerciseList bandProduct);

    }

    public void setOnItemClickListener(ExerciseListShowRecyclerAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public BandsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomExerciseViewListBinding binding = CustomExerciseViewListBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );

        return new BandsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BandsViewHolder holder, int position) {
        ExerciseList order = exerciseList.get(position);
//        int itemCount = noOfItemsList.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public class BandsViewHolder extends RecyclerView.ViewHolder {
        private final CustomExerciseViewListBinding binding;

        public BandsViewHolder(@NonNull CustomExerciseViewListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.mainView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    ExerciseList bandProduct = exerciseList.get(position);
                    mListener.onListItemClick(bandProduct);

                }
            });

        }

        public void bind(ExerciseList exerciseList) {
            binding.name.setText(exerciseList.getName());
        }

    }


}
