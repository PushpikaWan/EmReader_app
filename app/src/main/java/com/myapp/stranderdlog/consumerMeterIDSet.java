package com.myapp.stranderdlog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

import java.util.ArrayList;

public class consumerMeterIDSet extends ActionBarActivity {

    private Button nextbtn;
    private Spinner sp;
    private UserLocalStore userLocalStore;
    private int meter_id; // there should be a array of meter ids;
    private String meter_idstr, Globalcurrent_MID;
    public static String set_meter=null;
    public static Bill[] bill_arr = new Bill[5];
    public reading_data_sqlite reading_data;
    public static final int CONNECTION_TIMEOUT = 1000 * 30000;
    public static int m_id;
    public static boolean meter_id_set= false;
    //
    //public static final String SERVER_ADDRESS = "http://ec2-52-27-23-106.us-west-2.compute.amazonaws.com/";
    private UserLoginTask mAuthTask = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_meter_idset);
        Button nextbtn = (Button) findViewById(R.id.button);
        userLocalStore = new UserLocalStore(this);
        SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
        final SharedPreferences.Editor prefsEditor = myPrefs.edit();
        int i;
        int No_of_ID = myPrefs.getInt("No_Of_Meters", 1);
        String m_id_arr[] = new String[No_of_ID];
        for (i = 0; i < No_of_ID; i++) {
            String mid_name = "Meter_ID" + String.valueOf(i + 1);
            m_id_arr[i] = myPrefs.getString(mid_name, "");
        }


        sp = (Spinner) findViewById(R.id.spinner2);
        sp.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, m_id_arr));
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                meter_idstr = sp.getSelectedItem().toString();
                set_meter = meter_idstr;


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

    }


    public void go_Home(View view) {

        mAuthTask = new UserLoginTask(set_meter);
        mAuthTask.execute((Void) null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_consumer_select_meter_id, menu);
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
    }


    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String MeterID;
        public boolean stmt = true;


        UserLoginTask(String meterid) {
            MeterID = meterid;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("Meter_ID", set_meter));
            m_id=Integer.parseInt(MeterID);
            // dataToSend.add(new BasicNameValuePair("M_ID",3123314+""));
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(MainActivity.WEB_SERVER + "historyget.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);
                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);

                if (jObject.length() != 0) {
//                    Toast.makeText(getApplicationContext(), "Enter j object ", Toast.LENGTH_LONG).show();
                    Log.v("happened", "2");
                    String Last_Reading, Last_Reading_Date, Current_Reading, Current_Reading_Date, Period, Month, Usage,Rental,Arrears,Full_Amount;
                    for (int i = 0; i < bill_arr.length; i++) {
                        Last_Reading = jObject.getString(String.valueOf(bill_arr.length-i)+"Last_Reading");
                        Last_Reading_Date = jObject.getString(String.valueOf(bill_arr.length-i)+"Last_Reading_Date");
                        Current_Reading = jObject.getString(String.valueOf(bill_arr.length-i)+"Current_Reading");
                        Current_Reading_Date = jObject.getString(String.valueOf(bill_arr.length-i)+"Current_Reading_Date");
                        Period = jObject.getString(String.valueOf(bill_arr.length-i)+"Period");
                        Month = jObject.getString(String.valueOf(bill_arr.length-i)+"Month");
                        Usage = jObject.getString(String.valueOf(bill_arr.length-i)+"Usage");
                        Rental = jObject.getString(String.valueOf(bill_arr.length-i) + "Rental");
                        Arrears = jObject.getString(String.valueOf(bill_arr.length-i) + "Arrears");
                        Full_Amount = jObject.getString(String.valueOf(bill_arr.length-i) + "Full_Amount");

                        Log.v("happened_first", Month);

                        Bill bill = new Bill(Last_Reading, Last_Reading_Date, Current_Reading, Current_Reading_Date, Period, Month, Usage,Rental,Arrears,Full_Amount);
                        bill_arr[i] = bill;
                        if (i==bill_arr.length-1)
                        {
                            reading_data = new reading_data_sqlite("GLOBAL_READ", Integer.parseInt(MeterID), Last_Reading, Last_Reading_Date, Current_Reading, Current_Reading_Date, Period, Month, Usage, Rental, Arrears, Full_Amount);
                            Log.d("Database Helper", "row inserted sucessfully" +Month+i);
                        }
                    }

                } else {
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
                DatabaseHelper dbhelper= new DatabaseHelper(getApplicationContext());
                dbhelper.updateGlobalEntry(reading_data);
                meter_id_set=true;
                go_menu();
                //startActivity(intent1);
                //finish();

            } else {
                Toast.makeText(getApplicationContext(), "There is an Error ,please check the internet connection ", Toast.LENGTH_LONG).show();
            }
        }


        @Override
        protected void onCancelled() {
            //showProgress(false);
        }
    }

    public  void go_menu(){
        Intent intent3 = new Intent(this, Consumer_Home_page.class);
        this.startActivity(intent3);
        finish();
    }

}
