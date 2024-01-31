package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        Intent intent = getIntent();
        ArrayList<String> resultSaver = (ArrayList<String>) intent.getSerializableExtra("resultSaver");
        ListView historyList = findViewById(R.id.history_list);
        HistoryAdapter historyAdapter = new HistoryAdapter(getApplicationContext(), resultSaver);
        historyList.setAdapter(historyAdapter);
    }

    public void navigateBack(View view) {
        finish();
    }
}