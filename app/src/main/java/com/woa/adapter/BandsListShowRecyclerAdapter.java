package com.woa.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.woa.dataClasses.BandProductData;
import com.woa.dataClasses.Exercise;
import com.woa.databinding.CustomLayoutBandsProductsListShowBinding;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class BandsListShowRecyclerAdapter extends RecyclerView.Adapter<BandsListShowRecyclerAdapter.BandsViewHolder> {

    private Context context;
   BandsListShowRecyclerAdapter.OnItemClickListener mListener;
    private final ArrayList<BandProductData> bandProductsArrayList;
    private ArrayList<Integer> noOfItemsList;

    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference workOutPlanRef;

    public BandsListShowRecyclerAdapter(Context context, ArrayList<BandProductData> bandProductsArrayList) {
        this.context = context;
        this.bandProductsArrayList = bandProductsArrayList;
    }

    public interface OnItemClickListener {

        void onListItemClick(BandProductData bandProduct);

    }

    public void setOnItemClickListener(BandsListShowRecyclerAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public BandsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomLayoutBandsProductsListShowBinding binding = CustomLayoutBandsProductsListShowBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );

        return new BandsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BandsViewHolder holder, int position) {
        BandProductData order = bandProductsArrayList.get(position);
//        int itemCount = noOfItemsList.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return bandProductsArrayList.size();
    }

    public class BandsViewHolder extends RecyclerView.ViewHolder {
        private final CustomLayoutBandsProductsListShowBinding binding;

        public BandsViewHolder(@NonNull CustomLayoutBandsProductsListShowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.mainView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    BandProductData bandProduct = bandProductsArrayList.get(position);
                    mListener.onListItemClick(bandProduct);

                }
            });


        }

        public void bind(BandProductData bandProduct) {

            binding.exerciseName.setText(bandProduct.getName());
            binding.dateTv.setText(bandProduct.getDate());
            String total = String.valueOf(bandProduct.getExercisesCount());
            binding.exerciseTotal.setText(total);
            binding.timeTv.setText(getTime(bandProduct.getTime()));

            mAuth = FirebaseAuth.getInstance();
           String userId = Objects.requireNonNull(mAuth.getCurrentUser().getUid());
            workOutPlanRef = FirebaseDatabase.getInstance().getReference("Logbook");
            ArrayList<Exercise> exercisesList = new ArrayList<>();
            workOutPlanRef.child(userId).child(bandProduct.getProductKey())
                    .child("exercises").addListenerForSingleValueEvent(new ValueEventListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        for (DataSnapshot ds : snapshot.getChildren()) {

                            Exercise productInfo = ds.getValue(Exercise.class);
                            exercisesList.add(productInfo);

                        }



                        binding.exercisesListRecycler.setLayoutManager(new LinearLayoutManager(context));
                        ExercisesRecyclerAdapter adapter = new ExercisesRecyclerAdapter(context, exercisesList);
                        binding.exercisesListRecycler.setAdapter(adapter);
                    }else {


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });



        }

    }


    public static String getDate(long timestamp) {
        try {
            Date netDate = (new Date(timestamp));
            SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            return sfd.format(netDate);
        } catch (Exception e) {
            return "date";
        }
    }

    public static String getTime(long timestamp) {
        try {
            Date netDate = (new Date(timestamp));
            SimpleDateFormat sfd = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            return sfd.format(netDate);
        } catch (Exception e) {
            return "date";
        }
    }


}
