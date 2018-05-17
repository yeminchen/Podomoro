package com.example.podomoro;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cym on 3/25/18.
 */

public class StatisticFragment extends Fragment {

    static HashMap<String, Integer> history = new HashMap<String, Integer>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_statistic, container, false);

        PieChart pieChart = (PieChart) v.findViewById(R.id.consume_pie_chart);
        List<PieEntry> entries = new ArrayList<>();
        entries = addEntry2Pie();

        PieDataSet set = new PieDataSet(entries, "Podomoro History");
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate();

        return v;
    }

    private List<PieEntry> addEntry2Pie(){
        List<PieEntry> entries = new ArrayList<>();

        SharedPreferences pref = this.getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        Map record = pref.getAll();
        for (Object key : record.keySet()) {

            Object value = record.get(key);

            entries.add(new PieEntry(Integer.parseInt(value.toString()), key.toString()));

        }

        return entries;
    }
}
