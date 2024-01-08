package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<String> resultSaver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        Intent intent = getIntent();
        ListView historyList = findViewById(R.id.history_list);
        resultSaver = (ArrayList<String>) intent.getSerializableExtra("resultSaver");
        HistoryAdapter historyAdapter = new HistoryAdapter(getApplicationContext(), resultSaver);
        historyList.setAdapter(historyAdapter);
    }

    public void navigateBack(View view) {
        finish();
    }
}