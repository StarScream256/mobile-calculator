package com.example.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class BasicCalculatorActivity extends AppCompatActivity {

    private EditText inputExpression;
    private AppCompatButton btnClear, btnDelete, btnOpenBracket, btnCloseBracket, btnDivide, btnTimes, btnMinus, btnPlus, btnComma, btnEqual;
    private AppCompatButton btnNum0, btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9;
    private ArrayList<String> resultSaver = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_calculator);

        inputExpression = findViewById(R.id.inputExpression);

        Intent intent = getIntent();
        if (intent.getSerializableExtra("resultSaver") != null) {
            resultSaver = (ArrayList<String>) intent.getSerializableExtra("resultSaver");
        }

        if (intent.getStringExtra("selectedCalc") != null) {
            String selectedCalc = (String) intent.getStringExtra("selectedCalc");
            inputExpression.setText(selectedCalc);
        }

        btnClear = findViewById(R.id.btnClear);
        btnDelete = findViewById(R.id.btnDelete);
        btnOpenBracket = findViewById(R.id.btnOpenBracket);
        btnCloseBracket = findViewById(R.id.btnCloseBracket);
        btnDivide = findViewById(R.id.btnDivide);
        btnTimes = findViewById(R.id.btnTimes);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnComma = findViewById(R.id.btnComma);
        btnEqual = findViewById(R.id.btnEqual);
        btnNum0 = findViewById(R.id.btnNum0);
        btnNum1 = findViewById(R.id.btnNum1);
        btnNum2 = findViewById(R.id.btnNum2);
        btnNum3 = findViewById(R.id.btnNum3);
        btnNum4 = findViewById(R.id.btnNum4);
        btnNum5 = findViewById(R.id.btnNum5);
        btnNum6 = findViewById(R.id.btnNum6);
        btnNum7 = findViewById(R.id.btnNum7);
        btnNum8 = findViewById(R.id.btnNum8);
        btnNum9 = findViewById(R.id.btnNum9);

        btnClear.setOnClickListener(onClickListener);
        btnOpenBracket.setOnClickListener(onClickListener);
        btnCloseBracket.setOnClickListener(onClickListener);
        btnDivide.setOnClickListener(onClickListener);
        btnTimes.setOnClickListener(onClickListener);
        btnMinus.setOnClickListener(onClickListener);
        btnPlus.setOnClickListener(onClickListener);
        btnDelete.setOnClickListener(onClickListener);
        btnComma.setOnClickListener(onClickListener);
        btnEqual.setOnClickListener(onClickListener);
        btnNum0.setOnClickListener(onClickListener);
        btnNum1.setOnClickListener(onClickListener);
        btnNum2.setOnClickListener(onClickListener);
        btnNum3.setOnClickListener(onClickListener);
        btnNum4.setOnClickListener(onClickListener);
        btnNum5.setOnClickListener(onClickListener);
        btnNum6.setOnClickListener(onClickListener);
        btnNum7.setOnClickListener(onClickListener);
        btnNum8.setOnClickListener(onClickListener);
        btnNum9.setOnClickListener(onClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        resultSaver.clear();
    }

    // Listener ketika setiap button diklik
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AppCompatButton button = (AppCompatButton) v;
            String buttonText = button.getText().toString();
            
            if (inputExpression.getText().toString().equals("Bermasalah")) inputExpression.setText("");

            if (buttonText.equals("=")) {
                evaluateExp(inputExpression.getText().toString());
            } else if (buttonText.equals("C")) {
                inputExpression.setText(null);
            } else if (buttonText.equals("Del")) {
                deleteExpression();
            } else {
                updateExpression(buttonText);
            }
        }
    };

    // Update input ketika ada button diklik
    private void updateExpression(String text) {
        String currentExpression = inputExpression.getText().toString();
        inputExpression.setText(currentExpression + text);
    }

    // hapus 1 karakter dari belakang ketika button "Del" (Delete) diklik
    private void deleteExpression() {
        String exp = inputExpression.getText().toString();
        if (exp.length() >= 1) inputExpression.setText(exp.substring(0, exp.length() - 1));
    }

    private void evaluateExp(String expression) {
        WebView webView = new WebView(BasicCalculatorActivity.this);
        webView.getSettings().setJavaScriptEnabled(true);
        if (expression.startsWith("0")) expression = expression.replaceFirst("0", "");

        if (expression.contains("÷")) expression = expression.replace("÷", "/");
        if (expression.contains("×")) expression = expression.replace("×", "*");
        if (expression.contains(",")) expression = expression.replace(",", ".");

        String script = "" +
                "javascript:(function() {" +
                    "var result = eval("+ expression +");" +
                    "return result;" +
                "})()";

        final String finalExpression = expression;
        webView.evaluateJavascript(script, value -> {
            if (value.equals("null") || value.equals("")) {
                inputExpression.setText("Bermasalah");
                saveResult(finalExpression, "Bermasalah");
            } else {
                if (value.contains(".")) value = value.replace(".", ",");
                if (value.contains("*")) value = value.replace("*", "×");
                if (value.contains("/")) value = value.replace("/", "÷");

                saveResult(finalExpression, value);
                inputExpression.setText(value);
            }
        });
    }

    private void saveResult(String expression, String result) {
        String expRes = expression + "=" + result;
        resultSaver.add(expRes);
    }

    // Tampilkan popup ketika klik save
    public void openSaveDialog(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View saveDialogView = inflater.inflate(R.layout.save_dialog, null);

        TextView currentCalculation = saveDialogView.findViewById(R.id.currentCalculation);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(saveDialogView);

        if (resultSaver.size() < 1) {
            currentCalculation.setText("Tidak ada riwayat hitungan!");
            currentCalculation.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        } else {
            String currentCalc = resultSaver.get(resultSaver.size() - 1);
            if (currentCalc.contains(".")) currentCalc = currentCalc.replace(".", ",");
            if (currentCalc.contains("*")) currentCalc = currentCalc.replace("*", "×");
            if (currentCalc.contains("/")) currentCalc = currentCalc.replace("/", "÷");
            currentCalculation.setText(currentCalc);
            currentCalculation.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        }

        builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.style_popup_background);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void navigateToHistory(View view) {
        Intent intent = new Intent(BasicCalculatorActivity.this, HistoryActivity.class);
        intent.putExtra("resultSaver", resultSaver);
        startActivity(intent);
    }

    public void navigateToProfile(View view) {
        Intent intent = new Intent(BasicCalculatorActivity.this, ProfileActivity.class);
        intent.putExtra("resultSaver", resultSaver);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void navigateToMenu(View view) {
        startActivity(new Intent(BasicCalculatorActivity.this, MenuActivity.class));
    }
}