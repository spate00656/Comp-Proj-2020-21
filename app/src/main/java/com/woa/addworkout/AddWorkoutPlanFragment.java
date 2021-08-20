package com.woa.addworkout;
import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.woa.databinding.FragmentAddWorkoutPlanBinding;
import com.theartofdev.edmodo.cropper.CropImage;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Objects;
import static android.app.Activity.RESULT_OK;

public class AddWorkoutPlanFragment extends Fragment implements View.OnClickListener {

    private FragmentAddWorkoutPlanBinding binding;
    private Uri resultUriPic;
    private int hours, minutes, years, days, months;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddWorkoutPlanBinding.inflate(inflater, container, false);


        binding.selectPhotoButton.setOnClickListener(view -> checkAndroidVersion());
        binding.addExercisesButton.setOnClickListener(this);
        binding.selectDate.setOnClickListener(v -> {
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

                    }, year, month, day);

            picker.show();

        });
        return binding.getRoot();
    }

    public void checkAndroidVersion() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 555);
            } catch (Exception e) {

                Toast.makeText(requireContext(), "Permissions denied", Toast.LENGTH_SHORT).show();

            }
        } else {
            pickImage();
        }

    }

    public void pickImage() {
        CropImage.startPickImageActivity(requireContext(), this);
    }

    private void croprequest(Uri imageUri) {
        CropImage.activity(imageUri)
                .start(requireContext(), this);
    }

    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        if (requestCode == 555 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        } else {

            checkAndroidVersion();

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //RESULT FROM SELECTED IMAGE
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(requireContext(), data);
            croprequest(imageUri);
        }

        //RESULT FROM CROPING ACTIVITY


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                binding.selectedIv.setVisibility(View.VISIBLE);

                resultUriPic = result.getUri();
                Glide.with(requireContext())
                        .load(resultUriPic)
                        .into(binding.selectedIv);


            }
        }

    }

    @Override
    public void onClick(View view) {

        if (view == binding.addExercisesButton) {


            validationViews();
        }
    }

    private void validationViews() {

        String code = Objects.requireNonNull(binding.codeEt.getText()).toString();
        String name = Objects.requireNonNull(binding.nameEt.getText()).toString();
        String description = Objects.requireNonNull(binding.descriptionEt.getText()).toString();
        String date = binding.selectDateTv.getText().toString();

        if (code.isEmpty()) {
            binding.codeEt.setError("Enter product code");
            binding.codeEt.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            binding.nameEt.setError("Enter name");
            binding.nameEt.requestFocus();
            return;
        }
        if (description.isEmpty()) {
            binding.descriptionEt.setError("Type your description");
            binding.descriptionEt.requestFocus();
            return;
        }

        if (resultUriPic == null) {
            Toast.makeText(requireContext(), "Upload a photo", Toast.LENGTH_SHORT).show();
            return;
        }
        if (date == null) {
            Toast.makeText(requireContext(), "select date", Toast.LENGTH_SHORT).show();
            return;
        }

//        BandProduct bandProduct = new BandProduct(code, name, description, resultUriPic.toString(),date);
//        AddWorkoutPlanFragmentDirections.ActionAddBandProductFragmentToAddExerciseFragment action = AddWorkoutPlanFragmentDirections.actionAddBandProductFragmentToAddExerciseFragment(bandProduct);
//        Navigation.findNavController(getView()).navigate(action);


    }


}