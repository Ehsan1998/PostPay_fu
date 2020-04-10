package com.postpay.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.postpay.R;

import br.com.sapereaude.maskedEditText.MaskedEditText;
import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;

public class VerificationCodeActivity extends AppCompatActivity {

    private OtpTextView otpTextView;
    MaskedEditText phoneViewer;
    ImageView editPhone;
    Button sendAgain;
    private int count = 60;
    private boolean run = true;
    private Thread thread;
    Window window;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);

        Bundle bundle = getIntent().getExtras();

        otpTextView = (OtpTextView) findViewById(R.id.otp_view);
        phoneViewer = (MaskedEditText) findViewById(R.id.phoneViewer);
        editPhone = (ImageView) findViewById(R.id.phoneEdit);
        sendAgain = (Button) findViewById(R.id.sendCodeAgain);

        window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.colorAccent));
        window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility()| View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        runThread();

        sendAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VerificationCodeActivity.this,"کد فعالسازی مجدداَ ارسال شد",Toast.LENGTH_SHORT).show();
                btnClicked();
            }
        });
        phoneViewer.setText(bundle.getString("phoneNumber"));
        phoneViewer.setEnabled(false);
        otpTextView.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {

            }

            @Override
            public void onOTPComplete(String otp) {
                otpTextView.showSuccess();
                Intent intent = new Intent(VerificationCodeActivity.this,HomePageActivity.class);
                startActivity(intent);
            }
        });
        editPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerificationCodeActivity.this , GetPhoneNumberActivity.class);
                startActivity(intent);
            }
        });
    }

    private void runThread() {
        thread = new Thread(){
            @Override
            public void run(){
                while (run){
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("sendagaintext",String.valueOf(sendAgain.getText()));
                                if (count == 0){
                                    sendAgain.setBackgroundResource(R.drawable.enable_button);
                                    sendAgain.setEnabled(true);
                                    sendAgain.setTextColor(getResources().getColor(R.color.colorAccent));
                                    sendAgain.setText("ارسال مجدد");
                                    run = false;
                                }
                                else {
                                    sendAgain.setText(String.valueOf(count));
                                    count--;
                                }


                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    private void btnClicked() {
        sendAgain.setBackgroundResource(R.drawable.disable_button);
        sendAgain.setEnabled(false);
        sendAgain.setTextColor(getResources().getColor(R.color.gray500));
        count = 60;
        run = true;
        runThread();
    }
}

