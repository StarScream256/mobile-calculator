package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;

public class TemperatureCalculatorActivity extends AppCompatActivity {

    private String fromState, toState;
    private AppCompatButton fromCelcius, fromKelvin, fromReamur, toCelcius, toKelvin, toReamur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature_calculator);

        fromCelcius = findViewById(R.id.fromCelcius);
        fromKelvin = findViewById(R.id.fromKelvin);
        fromReamur = findViewById(R.id.fromReamur);
        toCelcius = findViewById(R.id.toCelcius);
        toKelvin = findViewById(R.id.toKelvin);
        toReamur = findViewById(R.id.toReamur);

        fromCelcius.setOnClickListener(fromClickListener);
        fromKelvin.setOnClickListener(fromClickListener);
        fromReamur.setOnClickListener(fromClickListener);
    }

    private final View.OnClickListener fromClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AppCompatButton button = (AppCompatButton) v;
            String buttonText = button.getText().toString();
        }
    };
}