package com.myapp.stranderdlog;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class enterDataManually extends ActionBarActivity implements View.OnClickListener {

    private EditText meter_id_ent,meter_reading_ent;
    private String meter_id_str,meter_reading_str;
    private int meter_id_int,meter_reading_int;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_data_manually);
        Button save_send = (Button) findViewById(R.id.save_send_data_btn);
        meter_id_ent = (EditText) findViewById(R.id.meter_id_entry);
        meter_reading_ent = (EditText) findViewById(R.id.meter_reading_entry);

        save_send.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

        final Intent intent = new Intent(this, Consumer_Home_page.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.save_send_data_btn){
            meter_id_str=meter_id_ent.getText().toString();
            meter_reading_str = meter_reading_ent.getText().toString();
            boolean meter_id=false,meter_reading=false;

            // Check for a valid Contact.
            if (TextUtils.isEmpty(meter_id_str)) {
                meter_id_ent.setError(getString(R.string.error_field_required));

            }else{
                meter_id_int = Integer.parseInt(meter_id_str);
                meter_id = true;
            }

            if (TextUtils.isEmpty(meter_reading_str)) {
                meter_reading_ent.setError(getString(R.string.error_field_required));

            }else{
                meter_reading_int = Integer.parseInt(meter_reading_str);
                meter_reading = true;
            }

            if(meter_id && meter_reading){
                Intent intent1 = new Intent(this, AfterDataSend.class);
                startActivity(intent1);
                finish();
            }

        }
    }
}
