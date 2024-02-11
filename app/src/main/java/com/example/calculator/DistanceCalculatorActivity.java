package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class DistanceCalculatorActivity extends AppCompatActivity {

    private final String[] distanceItems = {"km", "m", "cm", "mm", "mi", "nmi", "yd", "in", "ft"};
    private String distanceFromState, distanceToState;
    private AutoCompleteTextView distanceFromAuto, distanceToAuto;
    private TextInputEditText distanceFrom, distanceTo;
    private ArrayAdapter<String> adapterItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.distance_calculator);

        distanceFrom = findViewById(R.id.distanceFrom);
        distanceTo = findViewById(R.id.distanceTo);
        distanceFromAuto = findViewById(R.id.distanceFromAuto);
        distanceToAuto = findViewById(R.id.distanceToAuto);

        distanceFromState = distanceItems[0];
        distanceToState = distanceItems[1];
        distanceFrom.setText("1");
        distanceFromAuto.setText(distanceFromState);
        distanceToAuto.setText(distanceToState);

        adapterItem = new ArrayAdapter<String>(this, R.layout.distance_item_lists, distanceItems);
        distanceFromAuto.setAdapter(adapterItem);
        distanceToAuto.setAdapter(adapterItem);

        distanceFromAuto.setOnItemClickListener((parent, view, position, id) -> {
            distanceFromState = parent.getItemAtPosition(position).toString();
            calculateResult();
        });

        distanceToAuto.setOnItemClickListener((parent, view, position, id) -> {
            distanceToState = parent.getItemAtPosition(position).toString();
            calculateResult();
        });

        distanceFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable distanceFromText = distanceFrom.getText();
                if (distanceFromText.toString().length() < 1) {
                    distanceFrom.setText("0");
                } else if (
                        distanceFromText.toString().length() > 1
                        && distanceFromText.toString().charAt(0) == '0'
                        && !s.toString().contains(".")
                    ) {
                    if (s.toString().equals("00")) {
                        distanceFrom.setText("0");
                    } else {
//                        Toast.makeText(
//                                DistanceCalculatorActivity.this,
//                                s.toString().contains(".") + " instead " + s,
//                                Toast.LENGTH_SHORT
//                        ).show();
                        int nonZeroIndex = findFirstNonZeroIndex(distanceFromText.toString());
                        String notStartWithZeroText = distanceFromText.toString().substring(nonZeroIndex);
                        distanceFrom.setText(notStartWithZeroText);
                    }
                }
                distanceFrom.setSelection(distanceFrom.getText().toString().length());
                calculateResult();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private static int findFirstNonZeroIndex(String string) {
        int index = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != '0') {
                index = i;
                break;
            }
        }
        return index;
    }

    private void calculateResult() {
        if (!distanceFrom.getText().toString().equals("0.")) {
            Double from = Double.parseDouble(Objects.requireNonNull(distanceFrom.getText()).toString());
            Double result = 0.0;
            if (distanceFromState.equals(distanceItems[0])) { // km
                if (distanceToState.equals(distanceItems[0])) result = from; // km
                else if (distanceToState.equals(distanceItems[1])) result = from * 1_000; // m
                else if (distanceToState.equals(distanceItems[2])) result = from * 100_000; // cm
                else if (distanceToState.equals(distanceItems[3])) result = from * 1_000_000; // mm
                else if (distanceToState.equals(distanceItems[4])) result = from / 1.609; // mi
                else if (distanceToState.equals(distanceItems[5])) result = from / 1.852; // nmi
                else if (distanceToState.equals(distanceItems[6])) result = from * 1094; //yd
                else if (distanceToState.equals(distanceItems[7])) result = from * 39370; // in
                else if (distanceToState.equals(distanceItems[8])) result = from * 3281; // ft
            } else if (distanceFromState.equals(distanceItems[1])) { // m
                if (distanceToState.equals(distanceItems[0])) result = from / 1_000; // km
                else if (distanceToState.equals(distanceItems[1])) result = from; // m
                else if (distanceToState.equals(distanceItems[2])) result = from * 100; // cm
                else if (distanceToState.equals(distanceItems[3])) result = from * 1_000; // mm
                else if (distanceToState.equals(distanceItems[4])) result = from / 1609; // mi
                else if (distanceToState.equals(distanceItems[5])) result = from / 1852; // nmi
                else if (distanceToState.equals(distanceItems[6])) result = from * 1.094; //yd
                else if (distanceToState.equals(distanceItems[7])) result = from * 39.37; // in
                else if (distanceToState.equals(distanceItems[8])) result = from * 3.281; // ft
            } else if (distanceFromState.equals(distanceItems[2])) { // cm
                if (distanceToState.equals(distanceItems[0])) result = from / 100_000; // km
                else if (distanceToState.equals(distanceItems[1])) result = from / 100; // m
                else if (distanceToState.equals(distanceItems[2])) result = from; // cm
                else if (distanceToState.equals(distanceItems[3])) result = from * 10; // mm
                else if (distanceToState.equals(distanceItems[4])) result = from / 160900; // mi
                else if (distanceToState.equals(distanceItems[5])) result = from / 185200; // nmi
                else if (distanceToState.equals(distanceItems[6])) result = from / 91.44; //yd
                else if (distanceToState.equals(distanceItems[7])) result = from / 2.54; // in
                else if (distanceToState.equals(distanceItems[8])) result = from / 30.48; // ft
            } else if (distanceFromState.equals(distanceItems[3])) { // mm
                if (distanceToState.equals(distanceItems[0])) result = from / 1_000_000; // km
                else if (distanceToState.equals(distanceItems[1])) result = from / 1_000; // m
                else if (distanceToState.equals(distanceItems[2])) result = from / 10; // cm
                else if (distanceToState.equals(distanceItems[3])) result = from; // mm
                else if (distanceToState.equals(distanceItems[4])) result = from / 1.609e+6; // mi
                else if (distanceToState.equals(distanceItems[5])) result = from / 1.852e+6; // nmi
                else if (distanceToState.equals(distanceItems[6])) result = from / 914.4; //yd
                else if (distanceToState.equals(distanceItems[7])) result = from / 25.4; // in
                else if (distanceToState.equals(distanceItems[8])) result = from / 304.8; // ft
            } else if (distanceFromState.equals(distanceItems[4])) { // mi
                if (distanceToState.equals(distanceItems[0])) result = from * 1.609; // km
                else if (distanceToState.equals(distanceItems[1])) result = from * 1_609; // m
                else if (distanceToState.equals(distanceItems[2])) result = from * 160_900; // cm
                else if (distanceToState.equals(distanceItems[3])) result = from * 1_609_000; // mm
                else if (distanceToState.equals(distanceItems[4])) result = from; // mi
                else if (distanceToState.equals(distanceItems[5])) result = from * 1.151; // nmi
                else if (distanceToState.equals(distanceItems[6])) result = from * 1760; //yd
                else if (distanceToState.equals(distanceItems[7])) result = from * 63360; // in
                else if (distanceToState.equals(distanceItems[8])) result = from * 5280; // ft
            } else if (distanceFromState.equals(distanceItems[5])) { // nmi
                if (distanceToState.equals(distanceItems[0])) result = from * 1.852; // km
                else if (distanceToState.equals(distanceItems[1])) result = from * 1_852; // m
                else if (distanceToState.equals(distanceItems[2])) result = from * 185_200; // cm
                else if (distanceToState.equals(distanceItems[3])) result = from * 1_852_000; // mm
                else if (distanceToState.equals(distanceItems[4])) result = from * 1.151; // mi
                else if (distanceToState.equals(distanceItems[5])) result = from; // nmi
                else if (distanceToState.equals(distanceItems[6])) result = from * 2025; //yd
                else if (distanceToState.equals(distanceItems[7])) result = from * 72910; // in
                else if (distanceToState.equals(distanceItems[8])) result = from * 6076; // ft
            } else if (distanceFromState.equals(distanceItems[6])) { // yd
                if (distanceToState.equals(distanceItems[0])) result = from * 1094; // km
                else if (distanceToState.equals(distanceItems[1])) result = from * 1.094; // m
                else if (distanceToState.equals(distanceItems[2])) result = from * 91.44; // cm
                else if (distanceToState.equals(distanceItems[3])) result = from * 914.4; // mm
                else if (distanceToState.equals(distanceItems[4])) result = from / 1760; // mi
                else if (distanceToState.equals(distanceItems[5])) result = from / 2025; // nmi
                else if (distanceToState.equals(distanceItems[6])) result = from; //yd
                else if (distanceToState.equals(distanceItems[7])) result = from * 36; // in
                else if (distanceToState.equals(distanceItems[8])) result = from * 3; // ft
            } else if (distanceFromState.equals(distanceItems[7])) { // in
                if (distanceToState.equals(distanceItems[0])) result = from / 39370; // km
                else if (distanceToState.equals(distanceItems[1])) result = from / 39.37; // m
                else if (distanceToState.equals(distanceItems[2])) result = from * 2.54; // cm
                else if (distanceToState.equals(distanceItems[3])) result = from * 25.4; // mm
                else if (distanceToState.equals(distanceItems[4])) result = from / 63360; // mi
                else if (distanceToState.equals(distanceItems[5])) result = from / 72910; // nmi
                else if (distanceToState.equals(distanceItems[6])) result = from / 36; //yd
                else if (distanceToState.equals(distanceItems[7])) result = from; // in
                else if (distanceToState.equals(distanceItems[8])) result = from / 12; // ft
            } else if (distanceFromState.equals(distanceItems[8])) { // ft
                if (distanceToState.equals(distanceItems[0])) result = from / 3281; // km
                else if (distanceToState.equals(distanceItems[1])) result = from / 3.281; // m
                else if (distanceToState.equals(distanceItems[2])) result = from * 30.48; // cm
                else if (distanceToState.equals(distanceItems[3])) result = from * 304.8; // mm
                else if (distanceToState.equals(distanceItems[4])) result = from / 5280; // mi
                else if (distanceToState.equals(distanceItems[5])) result = from / 6076; // nmi
                else if (distanceToState.equals(distanceItems[6])) result = from / 3; //yd
                else if (distanceToState.equals(distanceItems[7])) result = from * 12; // in
                else if (distanceToState.equals(distanceItems[8])) result = from; // ft
            }
            distanceTo.setText(String.valueOf(result));
        }
    }
}