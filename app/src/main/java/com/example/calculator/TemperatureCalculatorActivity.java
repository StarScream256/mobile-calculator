package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TemperatureCalculatorActivity extends AppCompatActivity {

    private String fromState, toState;
    private AppCompatButton fromCelsius, fromKelvin, fromReamur, fromFahrenheit, toCelsius, toKelvin, toReamur, toFahrenheit;
    private EditText inputTemperature, resultTemperature;

    private final int activeButton = R.drawable.style_active_convertion_button;
    private final int normalButton = R.drawable.style_normal_convertion_button;
    private final String Fahrenheit = "Fahrenheit";
    private final String Celsius = "Celsius";
    private final String Reamur = "Reamur";
    private final String Kelvin = "Kelvin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature_calculator);

        fromCelsius = findViewById(R.id.fromCelsius);
        fromKelvin = findViewById(R.id.fromKelvin);
        fromReamur = findViewById(R.id.fromReamur);
        fromFahrenheit = findViewById(R.id.fromFahrenheit);
        toCelsius = findViewById(R.id.toCelcius);
        toKelvin = findViewById(R.id.toKelvin);
        toReamur = findViewById(R.id.toReamur);
        toFahrenheit = findViewById(R.id.toFahrenheit);

        inputTemperature = findViewById(R.id.inputTemperature);
        resultTemperature = findViewById(R.id.resultTemperature);

        fromCelsius.setOnClickListener(fromClickListener);
        fromKelvin.setOnClickListener(fromClickListener);
        fromReamur.setOnClickListener(fromClickListener);
        fromFahrenheit.setOnClickListener(fromClickListener);
        toCelsius.setOnClickListener(toClickListener);
        toKelvin.setOnClickListener(toClickListener);
        toReamur.setOnClickListener(toClickListener);
        toFahrenheit.setOnClickListener(toClickListener);

        fromState = Celsius;
        toState = Kelvin;
        fromCelsius.setBackgroundResource(activeButton);
        toKelvin.setBackgroundResource(activeButton);

        inputTemperature.addTextChangedListener(inputTemperatureWatcher);

        inputTemperature.setSelection(inputTemperature.getText().length());
        double initialInput = Double.parseDouble(inputTemperature.getText().toString());
        double initialResult = calculateTemperature(fromState, toState, initialInput);
        resultTemperature.setText(String.valueOf(initialResult));
    }

    private final View.OnClickListener fromClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AppCompatButton button = (AppCompatButton) v;
            String buttonText = button.getText().toString();
            String currentFromState = fromState;
            if (!buttonText.equals(fromState)) {
                if (buttonText.equals("K")) {
                    fromState = Kelvin;
                    fromKelvin.setBackgroundResource(activeButton);
                    fromKelvin.setText(fromState);
                } else if (buttonText.equals("R")) {
                    fromState = Reamur;
                    fromReamur.setBackgroundResource(activeButton);
                    fromReamur.setText(fromState);
                } else if (buttonText.equals("C")) {
                    fromState = Celsius;
                    fromCelsius.setBackgroundResource(activeButton);
                    fromCelsius.setText(fromState);
                } else if (buttonText.equals("F")) {
                    fromState = Fahrenheit;
                    fromFahrenheit.setBackgroundResource(activeButton);
                    fromFahrenheit.setText(fromState);
                }
                removeFromState(currentFromState, buttonText);
            }
            String reqInput = inputTemperature.getText().toString();
            double input = 0;
            if (!reqInput.equals("0")) {
                input = Double.parseDouble(reqInput);
            }
            double result = calculateTemperature(fromState, toState, input);
            resultTemperature.setText(String.valueOf(result));
        }
    };

    private void removeFromState(String currentState, String reqState) {
        if (currentState.equals(Celsius) && !reqState.equals("C")) {
            fromCelsius.setBackgroundResource(normalButton);
            fromCelsius.setText("C");
        } else if (currentState.equals(Kelvin) && !reqState.equals("K")) {
            fromKelvin.setBackgroundResource(normalButton);
            fromKelvin.setText("K");
        } else if (currentState.equals(Reamur) && !reqState.equals("R")) {
            fromReamur.setBackgroundResource(normalButton);
            fromReamur.setText("R");
        } else if (currentState.equals(Fahrenheit) && !reqState.equals("F")) {
            fromFahrenheit.setBackgroundResource(normalButton);
            fromFahrenheit.setText("F");
        }
    }

    private final View.OnClickListener toClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AppCompatButton button = (AppCompatButton) v;
            String buttonText = button.getText().toString();
            String currentFromState = toState;
            if (!buttonText.equals(toState)) {
                if (buttonText.equals("K")) {
                    toState = Kelvin;
                    toKelvin.setBackgroundResource(activeButton);
                    toKelvin.setText(toState);
                } else if (buttonText.equals("R")) {
                    toState = Reamur;
                    toReamur.setBackgroundResource(activeButton);
                    toReamur.setText(toState);
                } else if (buttonText.equals("C")) {
                    toState = Celsius;
                    toCelsius.setBackgroundResource(activeButton);
                    toCelsius.setText(toState);
                }  else if (buttonText.equals("F")) {
                    toState = Fahrenheit;
                    toFahrenheit.setBackgroundResource(activeButton);
                    toFahrenheit.setText(toState);
                }
                removeToState(currentFromState, buttonText);
            }
            String reqInput = inputTemperature.getText().toString();
            double input = 0;
            if (!reqInput.equals("0")) {
                input = Double.parseDouble(reqInput);
            }
            double result = calculateTemperature(fromState, toState, input);
            resultTemperature.setText(String.valueOf(result));
        }
    };

    private void removeToState(String currentState, String reqState) {
        if (currentState.equals(Celsius) && !reqState.equals("C")) {
            toCelsius.setBackgroundResource(normalButton);
            toCelsius.setText("C");
        } else if (currentState.equals(Kelvin) && !reqState.equals("K")) {
            toKelvin.setBackgroundResource(normalButton);
            toKelvin.setText("K");
        } else if (currentState.equals(Reamur) && !reqState.equals("R")) {
            toReamur.setBackgroundResource(normalButton);
            toReamur.setText("R");
        } else if (currentState.equals(Fahrenheit) && !reqState.equals("F")) {
            toFahrenheit.setBackgroundResource(normalButton);
            toFahrenheit.setText("F");
        }
    }

    private double calculateTemperature(String fromState, String toState, double inputTemperature) {
        double result = 0;
        double scale = 0;
        if (fromState.equals(Celsius)) {
            if (toState.equals(Reamur)) {
                scale = (double) inputTemperature * 4/5;
            } else if (toState.equals(Kelvin)) {
                scale = inputTemperature + 273.15;
            } else if (toState.equals(Fahrenheit)) {
                scale = (double) (inputTemperature * 9/5) + 32;
            } else if (toState.equals(Celsius)) {
                scale = inputTemperature;
            }
            result = scale;
        } else if (fromState.equals(Kelvin)) {
            if (toState.equals(Celsius)) {
                scale = inputTemperature - 273.15;
            } else if (toState.equals(Reamur)) {
                scale = (double) 4/5 * inputTemperature;
            } else if (toState.equals(Fahrenheit)) {
                scale = (double) 9/5 * (inputTemperature - 273.15) + 32;
            } else if (toState.equals(Kelvin)) {
                scale = inputTemperature;
            }
            result = scale;
        } else if (fromState.equals(Reamur)) {
            if (toState.equals(Celsius)) {
                scale = (double) 5/4;
            } else if (toState.equals(Kelvin)) {
                scale = (double) 5/4 * inputTemperature + 273;
            } else if (toState.equals(Fahrenheit)) {
                scale = (double) 9/4 * inputTemperature + 32;
            } else if (toState.equals(Reamur)) {
                scale = inputTemperature;
            }
            result = scale;
        } else if (fromState.equals(Fahrenheit)) {
            if (toState.equals(Celsius)) {
                scale = (double) 5/9 * (inputTemperature - 32);
            } else if (toState.equals(Kelvin)) {
                scale = (double) 5/9 * (inputTemperature - 32) + 273.15;
            } else if (toState.equals(Reamur)) {
                scale = (double) 4/9 * (inputTemperature - 32);
            } else if (toState.equals(Fahrenheit)) {
                scale = inputTemperature;
            }
            result = scale;
        }
        return result;
    }

    private final TextWatcher inputTemperatureWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String reqInput = inputTemperature.getText().toString();
            double input = 0;
            if (reqInput.length() < 1) {
                inputTemperature.setText("0");
                inputTemperature.setSelection(inputTemperature.getText().length());
            } else if (reqInput.length() > 1 && reqInput.charAt(0) == '0') {
                inputTemperature.setText(reqInput.substring(1));
                inputTemperature.setSelection(inputTemperature.getText().length());
                input = Double.parseDouble(reqInput);
            } else {
                input = Double.parseDouble(reqInput);
            }
            double result = calculateTemperature(fromState, toState, input);
            resultTemperature.setText(String.valueOf(result));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void navigateBack(View view) {
        finish();
    }
}