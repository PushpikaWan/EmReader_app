package com.myapp.stranderdlog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Consumer_image_data_get extends ActionBarActivity {

    private TextView image_units,cur_date,l_reading,l_date,view_usage,rental,arrears,amount;
    private Button jobDone,cancelRead;
    //private ProgressBar pb;
    private String MeterID;
    private String Meter_Unit,Usages,Meter_ID,LastReadingDate,LastReading,CurrentDate,Rental,Arrears,FullAmount,Month,Period;
    public static final int CONNECTION_TIMEOUT = 1000*30000;
    //
    //public static final String SERVER_ADDRESS = "http://ec2-52-27-23-106.us-west-2.compute.amazonaws.com/";
    private UserLoginTask mAuthTask = null;
//    Intent intent1 = new Intent(this, AfterDataSend.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_image_data_get);
        image_units = (TextView) findViewById(R.id.image_data_units_viewID);
        cur_date= (TextView)findViewById(R.id.cur_date);
        l_reading = (TextView)findViewById(R.id.last_reading);
        l_date = (TextView)findViewById(R.id.lst_date);
        view_usage = (TextView)findViewById(R.id.view_usageID);
        rental = (TextView)findViewById(R.id.rental);
        arrears = (TextView)findViewById(R.id.arrears);
        amount= (TextView)findViewById(R.id.amount);
        //jobDone =  (Button)findViewById(R.id.bjobDone);
        //cancelRead = (Button)findViewById(R.id.b_cancel);
        //jobDone.setVisibility(View.GONE);
        Meter_ID =ConsumerSelectMeterId.cur_id;
        Log.v("happened_first", Meter_ID);
        mAuthTask = new UserLoginTask(Meter_ID);
        mAuthTask.execute((Void) null);
    }


    @Override
    public void onBackPressed() {

        final Intent intent = new Intent(this, Consumer_Home_page.class);
        startActivity(intent);
        finish();
    }


    private void job_done(){
        reading_data_sqlite reading_data;
        reading_data = new reading_data_sqlite("LOCAL_READ",Integer.parseInt(MeterID),LastReading,LastReadingDate,Meter_Unit,CurrentDate, Period, Month,Usages,Rental,Arrears,FullAmount);
        DatabaseHelper dbhelper= new DatabaseHelper(getApplicationContext());
        dbhelper.createReadingEntry(reading_data);
    }
    private void cancel_read(){
        Intent intent = new Intent(this, Consumer_Home_page.class);
        startActivity(intent);
        finish();
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {


        public boolean stmt = true;


        UserLoginTask(String meterid) {
            MeterID = meterid;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("Meter_ID", Meter_ID));
           // dataToSend.add(new BasicNameValuePair("M_ID",3123314+""));
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(MainActivity.WEB_SERVER + "getbyid2.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);
                HttpEntity entity= httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);

                if(jObject.length()!=0){
//                    Toast.makeText(getApplicationContext(), "Enter j object ", Toast.LENGTH_LONG).show();
                    Log.v("happened", "2");
                    Meter_Unit =jObject.getString("Meter_Unit");
                    Usages = jObject.getString("Usage");
                    LastReadingDate=jObject.getString("LastReadingDate");
                    LastReading=jObject.getString("LastReading");
                    CurrentDate=jObject.getString("CurrentDate");
                    Rental=jObject.getString("Rental");
                    Arrears=jObject.getString("Arrears");
                    FullAmount=jObject.getString("FullAmount");
                    Log.v("happened_first",Meter_Unit);
                    Log.v("happened_last",Usages);
                    SharedPreferences myPrefs = getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
                    SharedPreferences.Editor prefsEditor = myPrefs.edit();
                    prefsEditor.putString("Meter_Unit",Meter_Unit);
                    prefsEditor.putString("Usage",Usages);
                    prefsEditor.putString("LastReadingDate",LastReadingDate);
                    prefsEditor.putString("LastReading",LastReading);
                    prefsEditor.putString("CurrentDate",CurrentDate);
                    prefsEditor.putString("Rental", Rental);
                    prefsEditor.putString("Arrears",Arrears);
                    prefsEditor.putString("FullAmount",FullAmount);
                    prefsEditor.putString("Period",Period);
                    prefsEditor.putString("Period",Month);   //change that dummy data
                    //prefsEditor.putString("Month",Month);

                    prefsEditor.commit();

                }
                else{
                    Toast.makeText(getApplicationContext(), "Error,Fetching data ", Toast.LENGTH_LONG).show();
                }


            } catch (Exception e) {
                e.printStackTrace();
                Log.v("happened", "3");
                return false;

                //return false;
            }
            return stmt;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

//            showProgress(false);


            if (success) {

                set_val();
                //jobDone.setVisibility(View.VISIBLE);
                //startActivity(intent1);
                //finish();

            } else {
                Toast.makeText(getApplicationContext(), "Error,There is a Error ", Toast.LENGTH_LONG).show();
            }
        }

        private void set_val() {
            image_units.setText(Meter_Unit);
            cur_date.setText(CurrentDate);
            l_reading.setText(LastReading);
            l_date.setText(LastReadingDate);
            view_usage.setText(Usages);
            rental.setText(Rental);
            arrears.setText(Arrears);
            amount.setText(FullAmount);
            //usage.setText(Usages);

        }




        @Override
        protected void onCancelled() {
            //showProgress(false);
        }
    }





}
