package com.example.calculator;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> resultSaver;
    LayoutInflater inflater;
    AdapterView.OnItemClickListener itemClickListener;


    public HistoryAdapter(Context appContext, ArrayList<String> arr) {
        this.context = appContext;
        this.resultSaver = arr;
        inflater = LayoutInflater.from(appContext);
    }

    @Override
    public int getCount() {
        return resultSaver.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.history_list, null);
        TextView calculation = convertView.findViewById(R.id.calculation);
        TextView resultCalc = convertView.findViewById(R.id.resultCalc);

        int equalSign = resultSaver.get(position).indexOf("=");
        String calc = resultSaver.get(position).substring(0, equalSign);
        String res = resultSaver.get(position).substring(equalSign + 1);

        calculation.setText(calc);
        resultCalc.setText(res);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BasicCalculatorActivity.class);
                intent.putExtra("historyCalc", calc);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
