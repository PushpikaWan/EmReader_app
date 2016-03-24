package com.myapp.stranderdlog;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pushpika on 1/4/16.
 */
public class DataProvider {

    public static HashMap<String ,List<String>> getInfo(String type_data,List<reading_data_sqlite> rdatalst){

        HashMap<String ,List<String>> readingDetails = new HashMap<String ,List<String>>();

        if (type_data=="GLOBAL_READ") {
            for (int i = 0; i < consumerMeterIDSet.bill_arr.length; i++) {
                List<String> read_data = new ArrayList<>();
                read_data.add("Meter ID : " + consumerMeterIDSet.m_id);
                read_data.add("Last_Reading_Date : " + consumerMeterIDSet.bill_arr[i].Last_Reading_Date);
                read_data.add("Last_Reading : " + consumerMeterIDSet.bill_arr[i].Last_Reading);
                read_data.add("Current_Reading_Date : " + consumerMeterIDSet.bill_arr[i].Current_Reading_Date);
                read_data.add("Current_Reading : " + consumerMeterIDSet.bill_arr[i].Current_Reading);
                //read_data.add("Period : "+consumerMeterIDSet.bill_arr[i].Period);   //need to create
                read_data.add("Month : " + consumerMeterIDSet.bill_arr[i].Month);
                read_data.add("Usage : " + consumerMeterIDSet.bill_arr[i].Usage);
                read_data.add("Rental : rs." + consumerMeterIDSet.bill_arr[i].Rental);
                read_data.add("Arrears : rs." + consumerMeterIDSet.bill_arr[i].Arrears);
                read_data.add("Full_Amount : rs." + consumerMeterIDSet.bill_arr[i].Full_Amount);


                readingDetails.put(consumerMeterIDSet.bill_arr[i].Current_Reading_Date, read_data);
            }

        }


        else if (type_data=="LOCAL_READ") {


            for (int i = 0; i < rdatalst.size(); i++) {
                //System.out.println(rdatalst.get(i));
                Log.d("Database Helper", "row entry"+rdatalst.get(i).Current_Reading_Date);
                List<String> read_data = new ArrayList<>();
                read_data.add("Meter ID : " + consumerMeterIDSet.m_id);
                read_data.add("Last_Reading_Date : " + rdatalst.get(i).Last_Reading_Date);
                read_data.add("Last_Reading : " + rdatalst.get(i).Last_Reading);
                read_data.add("Current_Reading_Date : " + rdatalst.get(i).Current_Reading_Date);
                read_data.add("Current_Reading : " + rdatalst.get(i).Current_Reading);
                //read_data.add("Period : "+consumerMeterIDSet.bill_arr[i].Period);   //need to create
                read_data.add("Month : " + rdatalst.get(i).Month);
                read_data.add("Usage : " + rdatalst.get(i).Usage);
                read_data.add("Rental : rs." + rdatalst.get(i).Rental);
                read_data.add("Arrears : rs." + rdatalst.get(i).Arrears);
                read_data.add("Full_Amount : rs." + rdatalst.get(i).Full_Amount);

                readingDetails.put(rdatalst.get(i).Current_Reading_Date, read_data);

            }



        }



        return readingDetails;


     }
}
