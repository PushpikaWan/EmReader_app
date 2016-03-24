package com.myapp.stranderdlog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;

/**
 * Created by pushpika on 11/17/15.
 */
//not related
public class ConsumerUsageGraph {
    public Intent getIntent(Context context){
        //int y []= {124,85,80,96,104,120,82,94,123,103,124,94};

        CategorySeries series =new CategorySeries("Demo Bar Graph");
        for (int i =0;i<consumerMeterIDSet.bill_arr.length;i++){
            series.add(consumerMeterIDSet.bill_arr[i].Month,Integer.parseInt(consumerMeterIDSet.bill_arr[i].Usage));
        }
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series.toXYSeries());

        //customize bars
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        renderer.setDisplayChartValues(true);
        renderer.setChartValuesSpacing((float) 1.5);
        renderer.setColor(Color.YELLOW);
        renderer.setDisplayChartValues(true);



        //customize graph
        XYMultipleSeriesRenderer mRender = new XYMultipleSeriesRenderer();
        mRender.addSeriesRenderer(renderer);
        mRender.setChartTitle("Electricity Usage");
        mRender.setXTitle("Months");
        mRender.setYTitle("Units /KWH");
        mRender.setZoomButtonsVisible(true);
        Intent intent = ChartFactory.getBarChartIntent(context,dataset,mRender, BarChart.Type.DEFAULT);
        return intent;
    }
}



/*
public class ConsumerUsageGraph {
    public Intent getIntent(Context context) {
        //int y []= {124,85,80,96,104,120,82,94,123,103,124,94};

        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < consumerMeterIDSet.bill_arr.length; i++) {
            entries.add(new BarEntry(Float.parseFloat(consumerMeterIDSet.bill_arr[i].Usage), i));
        }

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        for (int j = 0; j < consumerMeterIDSet.bill_arr.length; j++) {
            labels.add(consumerMeterIDSet.bill_arr[j].Month);
        }

        BarChart chart = new BarChart(context);
        setContentView(chart);

        BarData data = new BarData(labels, dataset);
        chart.setData(data);

        chart.setDescription("# of times Alice called Bob");


        Intent intent = ChartFactory.getBarChartIntent(context,dataset,mRender, BarChart.Type.DEFAULT);
        return intent;
    }
}
*/