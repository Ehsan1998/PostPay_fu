package com.postpay.Fragmens;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.postpay.Activities.GetPhoneNumberActivity;
import com.postpay.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

import br.com.sapereaude.maskedEditText.MaskedEditText;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    private CircleImageView profileImg;
    private MaskedEditText phoneNumber;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    ImageView logout, editProfile;
    TextView telId , instaId , webAdress;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorAccent));
        getActivity().getWindow().getDecorView().setSystemUiVisibility(getActivity().getWindow().getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        profileImg = (CircleImageView) view.findViewById(R.id.profileImage);
        phoneNumber = (MaskedEditText) view.findViewById(R.id.userPhoneNumber);
        logout = (ImageView) view.findViewById(R.id.message);
        editProfile = (ImageView) view.findViewById(R.id.editProfile);
        telId = (TextView) view.findViewById(R.id.telId);
        instaId = (TextView) view.findViewById(R.id.instaId);
        webAdress = (TextView) view.findViewById(R.id.siteAddress);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , GetPhoneNumberActivity.class);
                startActivity(intent);
            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"شما بر روی ویرایش پروفایل کلیک کرده اید",Toast.LENGTH_SHORT).show();
            }
        });
        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileImageClicked();
            }
        });
        phoneNumber.setText("09388476466");
        phoneNumber.setEnabled(false);
        telId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://t.me/arduino82";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        instaId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.instagram.com/e.k76/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        webAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://ehsankatebi.ir";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });


        return view;
    }

    public void checkAndroidVersion() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
        CropImage.startPickImageActivity(getContext(),this);
    }

    public void cropRequest(Uri imageUri){
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .setAspectRatio(1,1)
                .start(getContext(),this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK){
            Uri imageUri = CropImage.getPickImageResultUri(getContext(),data);
            cropRequest(imageUri);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),result.getUri());
                    profileImg.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void profileImageClicked() {
        checkAndroidVersion();
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(1, 1).start(getContext(),this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (requestCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), resultUri);
                    profileImg.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }*/

}
