package com.myapp.stranderdlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class consumer_history_page extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_history_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {

        final Intent intent = new Intent(this, Consumer_Home_page.class);
        startActivity(intent);
        finish();
    }


    public void Global_read(View view) {
        Intent intent3 = new Intent (this,consumer_global_historylist.class);
        //Intent intent3 = new Intent (this,consumer_full_history_page.class);
        this.startActivity(intent3);
    }
    public void Local_read(View view) {
        Intent intent3 = new Intent (this,consumer_local_historylist.class);
        //Intent intent3 = new Intent (this,consumer_full_history_page.class);
        this.startActivity(intent3);
    }

}
