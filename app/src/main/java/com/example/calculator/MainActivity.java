package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText usernameInput, passwordInput;
    private ImageView toggleIcon;
    private LinearLayout loginErrorContainer;
    private TextView loginErrorMessage;
    private boolean passwordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        toggleIcon = findViewById(R.id.togglePasswordIconLogin);
        loginErrorContainer = findViewById(R.id.loginErrorContainer);
        loginErrorMessage = findViewById(R.id.loginErrorMessage);
    }

    public void navigateToRegistration(View view) {
        Intent registration = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(registration);
    }

    public void togglePasswordLogin(View view) {
        passwordVisible = !passwordVisible;

        int drawableId = (passwordVisible) ? R.drawable.icon_eye_slash : R.drawable.icon_eye;
        toggleIcon.setImageResource(drawableId);

        int inputType = (passwordVisible) ?
                            InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                            InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        passwordInput.setInputType(inputType);
        passwordInput.setSelection(passwordInput.length());
    }

    public void login(View view) {
        loginErrorContainer.setVisibility(View.INVISIBLE);
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        if (username.equals("") || password.equals("")) {
            String errorMessage = "Username dan password harap diisi";
            loginErrorMessage.setText(errorMessage);
            loginErrorContainer.setVisibility(View.VISIBLE);
        } else if (username.equals("username") && password.equals("password")) {
            Intent login = new Intent(MainActivity.this, BasicCalculatorActivity.class);
            finish();
            startActivity(login);
        } else {
            String errorMessage = "Username atau password salah";
            loginErrorMessage.setText(errorMessage);
            loginErrorContainer.setVisibility(View.VISIBLE);
        }
    }
}