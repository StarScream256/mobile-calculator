package com.example.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.EditText;

import org.xml.sax.Parser;

public class BasicCalculatorActivity extends AppCompatActivity {

    private EditText inputExpression;
    private AppCompatButton btnClear, btnDelete, btnOpenBracket, btnCloseBracket, btnDivide, btnTimes, btnMinus, btnPlus, btnComma, btnEqual;
    private AppCompatButton btnNum0, btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_calculator);

        inputExpression = findViewById(R.id.inputExpression);

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

    // Listener ketika setiap button diklik
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AppCompatButton button = (AppCompatButton) v;
            String buttonText = button.getText().toString();

            if (buttonText.equals("=")) {
            } else if (buttonText.equals("C")) {
                inputExpression.setText(null);
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

    public void evaluateExp(String expression) {
        WebView webView = new WebView(BasicCalculatorActivity.this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.evaluateJavascript("", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {

            }
        });
    }

    // Tampilkan popup ketika klik save
    public void openSaveDialog(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View saveDialogView = inflater.inflate(R.layout.save_dialog, null);

        final EditText descriptionText = saveDialogView.findViewById(R.id.descriptionText);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(saveDialogView);

        builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String descriptedText = descriptionText.getText().toString();
            }
        });

        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                descriptionText.setText(null);
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.popup_background);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}