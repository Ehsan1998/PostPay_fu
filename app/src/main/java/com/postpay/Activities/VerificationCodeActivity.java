package com.postpay.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.postpay.R;

public class VerificationCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);

        TextView textView = findViewById(R.id.txt);
        Bundle bundle = getIntent().getExtras();
        textView.setText(bundle.getString("phoneNumber"));
    }
}
