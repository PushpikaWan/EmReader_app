package com.myapp.stranderdlog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class UserProfile extends ActionBarActivity {

    UserLocalStore userLocalStore;
    TextView bFirst_name,bLast_name,cont,Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        bFirst_name = (TextView) findViewById(R.id.etFirst_name);
        bLast_name = (TextView) findViewById(R.id.etLast_name);
        Email = (TextView) findViewById(R.id.etEmailId);
        //cont = (TextView) findViewById(R.id.etContactNo);
        displayUserDetails();
        userLocalStore = new UserLocalStore(this);
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
        getMenuInflater().inflate(R.menu.menu_user_profile, menu);
        return true;
    }



    public void go_login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void displayUserDetails(){
        //User user_new = userLocalStore.getLoggedInUser();
        SharedPreferences myPrefs2 = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);

        String First_name = myPrefs2.getString("First_name", "");
        String Last_name = myPrefs2.getString("Last_name","");
        String EmailID =myPrefs2.getString("Email_address","");
        //int Cont = myPrefs2.getInt("contact",0);
        //bFirst_name.setText("Pushpika");
        //bLast_name.setText("Wanniachchi");
        //String first ="Pushpikaaa";

        bFirst_name.setText(First_name);
        bLast_name.setText(Last_name);
        Email.setText(EmailID);
        //cont.setText(Integer.toString(Cont));
        //etLast_name.setText(user_new.Last_name);
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
