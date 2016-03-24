package com.myapp.stranderdlog;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ExpandableListView;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class consumer_local_historylist extends ActionBarActivity {
    HashMap<String ,List<String>> reading_history;
    List<String> readings;
    ExpandableListView Exp_list;
    ReadingsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_local_historylist);
        Exp_list = (ExpandableListView) findViewById(R.id.exp_list);
        //reading_history = DataProvider.getInfo("GLOBAL_READ",null);

        DatabaseHelper dbhelper= new DatabaseHelper(getApplicationContext());
        List<reading_data_sqlite> rdatalst =dbhelper.getReadingEntries(consumerMeterIDSet.m_id);

        Collections.sort(rdatalst, new CustomComparator());

        reading_history = DataProvider.getInfo("LOCAL_READ",rdatalst);
        //need to sort key list for sorting expendable array
        readings = new ArrayList<String>(reading_history.keySet());
        Collections.sort(readings, new CustomComparator2());
        adapter = new ReadingsAdapter(this,reading_history,readings);
        Exp_list.setAdapter(adapter);

    }

}

class CustomComparator implements Comparator<reading_data_sqlite> {

    @Override
    public int compare(reading_data_sqlite lhs, reading_data_sqlite rhs) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Log.d("Database Helper", "Compare data object");
        int returnint=0;
        try {
            boolean work = (sdf.parse(lhs.getCurrent_Reading_Date()).before(sdf.parse(rhs.getCurrent_Reading_Date())));
            if (work) {
                returnint=1;
            } else {
                returnint =-1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnint;
            ////return lhs.getCurrent_Reading_Date().compareTo(rhs.getCurrent_Reading_Date());}
    }
}

class CustomComparator2 implements Comparator<String> {


    @Override
    public int compare(String lhs, String rhs) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //Log.d("Database Helper", "Compare data object");
        int returnint=0;
        try {
            boolean work = (sdf.parse(lhs).before(sdf.parse(rhs)));
            if (work) {
                returnint=1;
            } else {
                returnint =-1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnint;
        ////return lhs.getCurrent_Reading_Date().compareTo(rhs.getCurrent_Reading_Date());}
    }
}



