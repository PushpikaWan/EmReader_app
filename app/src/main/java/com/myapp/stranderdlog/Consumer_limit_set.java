package com.myapp.stranderdlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Consumer_limit_set extends ActionBarActivity {
    private String global_reading,global_reading_date,global_last_reading_date,last_reading_date_check;
    private int meter_id;

    int [] date_dif,usages;
    public static  int [] date_usage_array = new int[31]; //hard coded for 31 days
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ArrayList<Limit_object> l_objects = new ArrayList<>();
    ArrayList<Limit_object> sorted_lobjects = new ArrayList<>();
    Date l_date_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_limit_set);
        get_readings();
        //get full data base entries from sqlite database
        DatabaseHelper dbhelper= new DatabaseHelper(getApplicationContext());
        List<reading_data_sqlite> rdatalst =dbhelper.getReadingEntries(consumerMeterIDSet.m_id);
        //filter and get types from data

        for (int i = 0; i < rdatalst.size(); i++) {
            //System.out.println(rdatalst.get(i));
            //Log.d("Database Helper", "row entry" + rdatalst.get(i).Current_Reading_Date);
            last_reading_date_check = rdatalst.get(i).Last_Reading_Date;
            Log.d("Database Helper", "local_last_reading date = " + last_reading_date_check);
/////we need to get sorted list according to current date from database

            if(last_reading_date_check.equals(global_last_reading_date) && rdatalst.get(i).Reading_type.equals("LOCAL_READ")) {
                Log.d("Database Helper", "row entry" + rdatalst.get(i).Current_Reading_Date + rdatalst.get(i).Usage);
               //List<String> read_data = new ArrayList<>();
                String Current_Reading_Date,Current_Reading,Usage;
                Current_Reading_Date=rdatalst.get(i).Current_Reading_Date;
                Current_Reading = rdatalst.get(i).Current_Reading;
                Usage = rdatalst.get(i).Usage;
                //readingDetail.put(rdatalst.get(i).Current_Reading_Date, read_data);

                try {
                    Log.d("Database Helper", "one object is proceed");
                    //Limit_object limit_object= new Limit_object(sdf.parse(Current_Reading_Date),Integer.parseInt(Usage));
                    l_objects.add(new Limit_object(sdf.parse(Current_Reading_Date),Integer.parseInt(Usage)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }

        Limit_object lobj= new Limit_object();
        sorted_lobjects=lobj.sort_array(l_objects); //sorted arraylist
        int dat_df,use_units;
        final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;


        for(int i=0;i<sorted_lobjects.size();i++){
            dat_df = (int) ((sorted_lobjects.get(i).c_date_d.getTime()-l_date_d.getTime())/ DAY_IN_MILLIS );
            use_units=sorted_lobjects.get(i).usage;
            //overloaded same date units checks
            date_usage_array[dat_df]=use_units;
            Log.d("Database Helper", "This is the limit graph entry :"+String.valueOf(use_units)+String.valueOf(sorted_lobjects.get(i).c_date_d));

        }

        //send data to graph
        Intent intent = new Intent(this, consumer_limit_show_grpahs.class);
        startActivity(intent);
        finish();



    }

    public void get_readings(){
            meter_id=consumerMeterIDSet.m_id;
            //if it is wrong please set consumerMeterIDSet.bill_arr.length-1 to 0


            //sdf.parse(date_str);

            global_last_reading_date=consumerMeterIDSet.bill_arr[consumerMeterIDSet.bill_arr.length-1].Last_Reading_Date;
            global_reading_date=consumerMeterIDSet.bill_arr[consumerMeterIDSet.bill_arr.length-1].Current_Reading_Date;
            global_reading=consumerMeterIDSet.bill_arr[consumerMeterIDSet.bill_arr.length-1].Current_Reading;
            Log.d("Database Helper", "global_last_reading date = " + global_last_reading_date);
        try {
            l_date_d=sdf.parse(global_last_reading_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    }





