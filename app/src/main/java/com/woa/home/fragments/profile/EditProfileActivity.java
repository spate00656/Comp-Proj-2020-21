package com.woa.home.fragments.profile;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.woa.R;
import com.woa.dataClasses.Users;
import com.woa.databinding.ActivityEditProfileBinding;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EditProfileActivity extends AppCompatActivity {

    ActivityEditProfileBinding binding;
    FirebaseAuth mAuth;
    DatabaseReference userReference;
    String userId;
    Users users;
    Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseInitialization();
        getUserProfileData();
        clickListeners();
    }


    private void clickListeners() {
        binding.updateBtn.setOnClickListener(view1 -> updateUser());
        binding.back.setOnClickListener(view1 -> onBackPressed());
        binding.profilePhoto.setOnClickListener(view1 -> checkAndroidVersion());
    }

    private void updateUser() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.updateBtn.setVisibility(View.GONE);
        if (resultUri != null) {

            final StorageReference filePath = FirebaseStorage.getInstance().getReference().child("Users").child(resultUri.getLastPathSegment());
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            assert bitmap != null;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = filePath.putBytes(data);

            uploadTask.addOnFailureListener(e -> {

            });
            uploadTask.addOnSuccessListener(taskSnapshot -> filePath.getDownloadUrl().addOnSuccessListener(uri -> {
                Map<String, Object> newImage = new HashMap<>();
                newImage.put("image", uri.toString());
                userReference.updateChildren(newImage).addOnCompleteListener(task -> {
                    binding.progressBar.setVisibility(View.GONE);
                    binding.updateBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(EditProfileActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditProfileActivity.this, EditProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                });

            }).addOnFailureListener(exception -> {
                binding.progressBar.setVisibility(View.GONE);
                binding.updateBtn.setVisibility(View.VISIBLE);
                finish();


            }));
        } else {

            Map<String, Object> map = new HashMap<>();
            map.put("userId", userId);
            map.put("userName", binding.userName.getText().toString());
            map.put("userEmail", binding.email.getText().toString());
            userReference.updateChildren(map).addOnCompleteListener(task1 -> {
                binding.progressBar.setVisibility(View.GONE);
                binding.updateBtn.setVisibility(View.VISIBLE);
                Toast.makeText(EditProfileActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditProfileActivity.this, EditProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }).addOnFailureListener(e -> {
                binding.progressBar.setVisibility(View.GONE);
                binding.updateBtn.setVisibility(View.VISIBLE);
                Toast.makeText(EditProfileActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

            });


        }


    }

    private void getUserProfileData() {

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    users = snapshot.getValue(Users.class);
                    assert users != null;
                    if (users.getImage() != null) {
                        Glide.with(EditProfileActivity.this).load(users.getImage()).into(binding.profilePhoto);
                    } else {
                        Glide.with(EditProfileActivity.this).load(R.drawable.ic_baseline_person_24).into(binding.profilePhoto);
                    }
                    binding.userName.setText(users.getUserName());
                    binding.name.setText(users.getUserName());
                    binding.email.setText(users.getUserEmail());
                    binding.mobile.setText(users.getUserPhoneNo());


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void firebaseInitialization() {
        // firebase Reference
        mAuth = FirebaseAuth.getInstance();
        userId = Objects.requireNonNull(Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
        userReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

    }


    public void checkAndroidVersion() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 555);
            } catch (Exception ignored) {


            }
        } else {
            pickImage();
        }


    }

    public void pickImage() {
        CropImage.startPickImageActivity(this);
    }

    private void croprequest(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //RESULT FROM SELECTED IMAGE
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(EditProfileActivity.this, data);
            croprequest(imageUri);
        }

        //RESULT FROM CROPING ACTIVITY
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(EditProfileActivity.this.getContentResolver(), result.getUri());

                    resultUri = getImageUri(EditProfileActivity.this, bitmap);
                    Glide.with(this).load(bitmap).into(binding.profilePhoto);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 555 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        } else {

            checkAndroidVersion();

        }
    }



}