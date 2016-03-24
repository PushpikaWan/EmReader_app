package com.myapp.stranderdlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class consumer_type_set_limit extends ActionBarActivity {

    public static boolean is_limit_set=false;
    public static float limit_size=0;
    EditText txt_fld;
    Button set_lim,get_graph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_type_set_limit);
        txt_fld = (EditText) findViewById(R.id.limit_field);
        set_lim = (Button) findViewById(R.id.b_set);
        get_graph = (Button) findViewById(R.id.bcrt_graph);
        get_graph.setVisibility(View.GONE);

    }


    @Override
    public void onBackPressed() {
        final Intent intent = new Intent(this, Consumer_Home_page.class);
        startActivity(intent);
        finish();
    }

    public void set_limit_now(View view) {

        limit_size =Integer.parseInt(String.valueOf(txt_fld.getText()));
        if(limit_size>0){
            is_limit_set = true;
            get_graph.setVisibility(View.VISIBLE);
        }
        else{
            Toast.makeText(getApplicationContext(), "There is an Error ,please set valid limit", Toast.LENGTH_LONG).show();
        }
    }

    public void create_grpah_now(View view) {
        Intent intent3 = new Intent(this,  consumer_limit_show_grpahs.class);
        this.startActivity(intent3);
        finish();
    }

}
