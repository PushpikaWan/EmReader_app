package com.myapp.stranderdlog;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by pushpika on 1/7/16.
 */
public class Limit_object {
    String c_date_str;
    int usage;
    Date c_date_d;

    public Limit_object(){

    }

    public  Limit_object(String c_date_str,int usage){
        this.c_date_str = c_date_str;
        this.usage = usage;
    }
    public  Limit_object(Date c_date_d,int usage){
        this.c_date_d=c_date_d;
        this.usage = usage;
    }

    public ArrayList<Limit_object> sort_array(ArrayList<Limit_object> a_list){
        Collections.sort(a_list, new CustomComparator_list_object());
        return  a_list;
    }
}

class CustomComparator_list_object implements Comparator<Limit_object> {

    @Override
    public int compare(Limit_object lhs, Limit_object rhs) {
        Log.d("Database Helper", "Compare data object");
        int returnint=0;

            boolean work = ((rhs.c_date_d).before(lhs.c_date_d));
            if (work) {
                returnint=1;
            } else {
                returnint =-1;
            }

        return returnint;
        ////return lhs.getCurrent_Reading_Date().compareTo(rhs.getCurrent_Reading_Date());}
    }
}

