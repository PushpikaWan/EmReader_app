package com.myapp.stranderdlog;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class consumer_usage_graph extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_usage_graph);
        BarChart chart = (BarChart) findViewById(R.id.chart);

        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.setDescription("Electricity Usage");
        chart.animateXY(2000, 2000);
        chart.invalidate();
    }

    @Override
    public void onBackPressed() {

        final Intent intent = new Intent(this, Consumer_Home_page.class);
        startActivity(intent);
        finish();
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        for (int i = 0; i < consumerMeterIDSet.bill_arr.length; i++) {
            valueSet1.add(new BarEntry(Float.parseFloat(consumerMeterIDSet.bill_arr[i].Usage), i));
        }

      /*  ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);
*/
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
       // BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 2");
        //barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        //dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        for (int j = 0; j < consumerMeterIDSet.bill_arr.length; j++) {
            xAxis.add(consumerMeterIDSet.bill_arr[j].Month);
        }
        return xAxis;
    }

}
