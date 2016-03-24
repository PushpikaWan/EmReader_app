package com.myapp.stranderdlog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class ConsumerSelectMeterId extends ActionBarActivity {
    public static String cur_id;
    private Button nextbtn;
    private Spinner sp;
    private UserLocalStore userLocalStore;
    private int meter_id; // there should be a array of meter ids;
    private String meter_idstr,current_MID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_select_meter_id);
        Button nextbtn = (Button)findViewById(R.id.button);
        userLocalStore = new UserLocalStore(this);
        SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
        final SharedPreferences.Editor prefsEditor1 = myPrefs.edit();
        int i;
        int No_of_ID = myPrefs.getInt("No_Of_Meters",1);
        String m_id_arr[]=new String[No_of_ID];
        for(i=0;i<No_of_ID;i++){
            String mid_name="Meter_ID"+String.valueOf(i+1);
            m_id_arr[i]=myPrefs.getString(mid_name,"");
        }


        sp = (Spinner) findViewById(R.id.spinner2);
        sp.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,m_id_arr));
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                cur_id= sp.getSelectedItem().toString();
                Log.v("happened_first", cur_id);
                prefsEditor1.putString("current_MID",cur_id);


            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    @Override
    public void onBackPressed() {

        final Intent intent = new Intent(this, Consumer_Home_page.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_consumer_select_meter_id, menu);
        return true;
    }
    public void go_camera_activity(View view) {
        Intent intent = new Intent(this, ConsumerCameraActivity.class);
        startActivity(intent);
        finish();
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
    }
}
