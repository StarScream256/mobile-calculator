package com.example.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> resultSaver;
    LayoutInflater inflater;

    public HistoryAdapter(Context appContext, ArrayList<String> rsltSvr) {
        this.context = appContext;
        this.resultSaver = rsltSvr;
        inflater = (LayoutInflater.from(appContext));
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
        convertView = inflater.inflate(R.layout.hisoty_list, null);
        TextView calculation = (TextView) convertView.findViewById(R.id.calculation);
        TextView resultCalc = (TextView) convertView.findViewById(R.id.resultCalc);
        TextView description = (TextView) convertView.findViewById(R.id.description);

        int equalSign = resultSaver.get(position).indexOf("=");
        String calc = resultSaver.get(position).substring(0, equalSign);
        String res = resultSaver.get(position).substring(equalSign + 1);

        if (calc.contains("*")) calc = calc.replace("*", "×");
        if (calc.contains("/")) calc = calc.replace("/", "÷");

        calculation.setText(calc);
        resultCalc.setText(res);
        description.setText("Some description goes here");
        return convertView;
    }
}
