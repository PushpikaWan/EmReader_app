package com.myapp.stranderdlog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity {
    //public static String WEB_SERVER="10.22.200.30";
    public static String WEB_SERVER="http://ec2-52-27-23-106.us-west-2.compute.amazonaws.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        final Intent intent = new Intent(this, LoginActivity.class);
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 3000);

    }




    /*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    } */
}