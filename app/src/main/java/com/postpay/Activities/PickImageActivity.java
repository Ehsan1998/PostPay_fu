package com.postpay.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.ImageView;

import com.postpay.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class PickImageActivity extends AppCompatActivity {

    Context context;
    CircleImageView profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_image);

        profileImg = (CircleImageView) findViewById(R.id.profileImage2);
        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndroidVersion();
            }
        });
    }
    public void checkAndroidVersion() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 555);
            }catch (Exception e){

            }
        }else
            pickImage();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 555 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            pickImage();
        else
            checkAndroidVersion();
    }

    private void pickImage() {
        CropImage.startPickImageActivity(this);
    }

    public void cropRequest(Uri imageUri){
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .setAspectRatio(1,1)
                .start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(context, data);
            cropRequest(imageUri);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), result.getUri());
                    profileImg.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
