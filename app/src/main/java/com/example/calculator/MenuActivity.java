package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void navigateBackToCalculator(View view) {
        finish();
    }

    public void navigateToTemperature(View view) {
        startActivity(new Intent(MenuActivity.this, TemperatureCalculatorActivity.class));
    }

    public void navigateToDistance(View view) {
        startActivity(new Intent(MenuActivity.this, DistanceCalculatorActivity.class));
    }
}