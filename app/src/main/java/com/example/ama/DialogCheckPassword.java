package com.example.ama;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Objects;

public class DialogCheckPassword extends Activity {

//    private static DialogCheckPassword _sharedIntanceForDCP;
//    OnItemClickListener _acceptButtonClick;
//    OnItemClickListener _cancelButtonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_check_password);

//        _sharedIntanceForDCP = DialogCheckPassword.this;

        Intent intentFromService = getIntent();
        String appPackageName = intentFromService.getStringExtra("appPackageName");
        String appPassword = intentFromService.getStringExtra("appPassword");

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button acceptButton = findViewById(R.id.button_acceptCheckPassword);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            private int checkCount = 1;

            @Override
            public void onClick(View v) {
                Log.d("ap", appPassword);
//                _acceptButtonClick.onButtonClick(1);
                TextView inputPassword = findViewById(R.id.editText_checkPassword);

                if (appPassword.equals(inputPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하였습니다.", Toast.LENGTH_SHORT).show();

                    finish();
                    Intent intentToApp = getApplicationContext().getPackageManager().getLaunchIntentForPackage(appPackageName);
                    intentToApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intentToApp);
                }
                else {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.(" + checkCount + "/3)", Toast.LENGTH_SHORT).show();

                    checkCount++;
                    if (checkCount > 3) {
                        finish();
                        checkCount = 1;
                    }
                }

                inputPassword.setText("");

            }
        });

        Button cancelButton = findViewById(R.id.button_cancelCheckPassword);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                _cancelButtonClick.onButtonClick();
                finish();
            }
        });
    }

//    public void acceptButtonClickListener(OnItemClickListener listener) { _acceptButtonClick = listener; };
//    public void cancelButtonClickListener(OnItemClickListener listener) { _cancelButtonClick = listener; };
//    public static DialogCheckPassword getSharedInstance() {
//        return _sharedIntanceForDCP;
//    }
//    public interface OnCheckButtonClickListener {
//        public void onButtonClick();
//    }
}
