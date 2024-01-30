package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class BinaryCalculatorActivity extends AppCompatActivity {

    private EditText inputExpression;
    private AppCompatButton btnClear, btnDelete, btnOpenBracket, btnCloseBracket, btnDivide, btnTimes, btnMinus, btnPlus, btnEqual;
    private AppCompatButton btnNum0, btnNum1;
    private ArrayList<String> resultSaver = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binary_calculator);

        inputExpression = findViewById(R.id.inputExpression);

        btnClear = findViewById(R.id.btnClear);
        btnDelete = findViewById(R.id.btnDelete);
        btnOpenBracket = findViewById(R.id.btnOpenBracket);
        btnCloseBracket = findViewById(R.id.btnCloseBracket);
        btnDivide = findViewById(R.id.btnDivide);
        btnTimes = findViewById(R.id.btnTimes);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnEqual = findViewById(R.id.btnEqual);
        btnNum0 = findViewById(R.id.btnNum0);
        btnNum1 = findViewById(R.id.btnNum1);

        btnClear.setOnClickListener(onClickListener);
        btnOpenBracket.setOnClickListener(onClickListener);
        btnCloseBracket.setOnClickListener(onClickListener);
        btnDivide.setOnClickListener(onClickListener);
        btnTimes.setOnClickListener(onClickListener);
        btnMinus.setOnClickListener(onClickListener);
        btnPlus.setOnClickListener(onClickListener);
        btnDelete.setOnClickListener(onClickListener);
        btnEqual.setOnClickListener(onClickListener);
        btnNum0.setOnClickListener(onClickListener);
        btnNum1.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AppCompatButton button = (AppCompatButton) v;
            String buttonText = button.getText().toString();

            if (inputExpression.getText().toString().equals("Bermasalah")) inputExpression.setText("");

            if (buttonText.equals("=")) {

            } else if (buttonText.equals("C")) {
                inputExpression.setText(null);
            } else if (buttonText.equals("Del")) {
                deleteExpression();
            } else {
                updateExpression(buttonText);
            }
        }
    };

    private void updateExpression(String text) {
        String currentExpression = inputExpression.getText().toString();
        inputExpression.setText(currentExpression + text);
    }

    private void deleteExpression() {
        String exp = inputExpression.getText().toString();
        if (exp.length() >= 1) inputExpression.setText(exp.substring(0, exp.length() - 1));
    }


    public void navigateToBasicCalculator(View view) {
        startActivity(new Intent(BinaryCalculatorActivity.this, BasicCalculatorActivity.class));
    }

    private void convertBinToDecimal(String binaryString) {
        String[] splitted = binaryString.split("[+]");
    }
}