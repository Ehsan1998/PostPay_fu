package com.postpay.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.postpay.R;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public class GetPhoneNumberActivity extends AppCompatActivity {

    //MaskedEditText maskedEditText;
    br.com.sapereaude.maskedEditText.MaskedEditText maskedEditText;
    Button button;
    private String phoneNumber;
    private String maskedPhoneNumber;
    private String code = "09";
    Window window;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_phone_number);

        maskedEditText = (MaskedEditText) findViewById(R.id.phoneInput2);
        button = (Button) findViewById(R.id.phoneButton);
        button.setEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClicked();
            }
        });

        window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.colorAccent));
        window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility()| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        maskedEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phoneNumber = code + maskedEditText.getRawText();
                if (phoneNumber.length() == 11){
                    button.setBackgroundResource(R.drawable.enable_button);
                    button.setEnabled(true);
                    button.setTextColor(getResources().getColor(R.color.colorAccent));
                }
                else{
                    button.setBackgroundResource(R.drawable.disable_button);
                    button.setEnabled(false);
                    button.setTextColor(getResources().getColor(R.color.gray500));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void btnClicked() {
        Intent intent = new Intent(this , VerificationCodeActivity.class);
        intent.putExtra("phoneNumber",phoneNumber);
        startActivity(intent);
    }
}
