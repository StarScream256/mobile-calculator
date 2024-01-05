package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;

public class RegistrationActivity extends AppCompatActivity {

    private EditText passwordInput;
    private ImageView toggleIcon;
    private boolean passwordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        passwordInput = findViewById(R.id.passwordInput);
        toggleIcon = findViewById(R.id.togglepasswordIconRegistration);
    }

    public void navigateToLogin(View view) {
        Intent login = new Intent(RegistrationActivity.this, MainActivity.class);
        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(login);
    }

    public void togglePasswordRegistration(View view) {
        passwordVisible = !passwordVisible;

        int drawableId = (passwordVisible) ? R.drawable.eye_slash_svgrepo_com : R.drawable.eye_svgrepo_com;
        toggleIcon.setImageResource(drawableId);

        int inputType = (passwordVisible) ?
                            InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                            InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        passwordInput.setInputType(inputType);

        passwordInput.setSelection(passwordInput.length());

    }
}