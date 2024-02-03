package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usernameInput, passwordInput;
    private ImageView toggleIcon;
    private boolean passwordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        toggleIcon = findViewById(R.id.togglePasswordIconLogin);
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
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.toast_root));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0, 12);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        TextView toastMessage = layout.findViewById(R.id.toast_message);
        layout.setBackgroundResource(R.drawable.style_error_container);

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        if (username.equals("") || password.equals("")) {
            toastMessage.setText("Username dan pasword harus diisi");
            toast.show();
        } else if (username.equals("username") && password.equals("password")) {
            Intent login = new Intent(MainActivity.this, BasicCalculatorActivity.class);
            finish();
            startActivity(login);
        } else {
            toastMessage.setText("Username atau password salah");
            toast.show();
        }
    }

    public void skipLogin(View view) {
        Intent login = new Intent(MainActivity.this, BasicCalculatorActivity.class);
        finish();
        startActivity(login);
    }
}