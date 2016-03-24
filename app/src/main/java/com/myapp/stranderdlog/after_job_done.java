package com.myapp.stranderdlog;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class after_job_done extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_job_done);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        reading_data_sqlite reading_data1,reading_data2,reading_data3;
        reading_data1 = new reading_data_sqlite("LOCAL_READ",3123314,"6788","2015-12-24","6792","2015-12-25","1","December","4","480","0","31.40");
        reading_data2 = new reading_data_sqlite("LOCAL_READ",3123314,"6788","2015-12-24","6795","2015-12-26","1","December","7","480","0","54.95");
        reading_data3 = new reading_data_sqlite("LOCAL_READ",3123314,"6788","2015-12-24","6797","2015-12-27","1","December","9","480","0","70.65");

        DatabaseHelper dbhelper= new DatabaseHelper(getApplicationContext());
        dbhelper.createReadingEntry(reading_data1);
        dbhelper.createReadingEntry(reading_data2);
        dbhelper.createReadingEntry(reading_data3);
    }

}
