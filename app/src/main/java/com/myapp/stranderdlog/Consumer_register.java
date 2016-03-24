package com.myapp.stranderdlog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.util.ArrayList;


public class Consumer_register extends ActionBarActivity {

    private Button bRegister;
    private EditText etMeter_ID_List,etFirst_name,etLast_name,etEmail_address,etPassword,etAddress_no,etAddress_street,etAddress_city,etNo_Of_Meters,et_Meter_ID,etContact,etConfirmpwd;
    private String  meter_id_list,Type,First_name,Last_name,Address_no,Address_Street,Address_city,Meter_ID,Email_address,Password,Confirmpwd,Contactstr,No_Of_Meterstr,Meter_IDstr;;
    private int Contact,No_Of_Meters,array_index=0;
    private String current_list="", Meter_ID_Arr[]=new String[10],n_o_meter_from_spinner="1"; //maximum number of  10 meter id per consumer;
    private ProgressDialog progressDialog;
    private UserLoginTask mAuthTask = null;
    public static final int CONNECTION_TIMEOUT = 1000*15;
    //public static final String SERVER_ADDRESS = "http://ec2-52-27-23-106.us-west-2.compute.amazonaws.com/";
    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_register);
        etFirst_name = (EditText)findViewById(R.id.First_name);
        etLast_name = (EditText) findViewById(R.id.Last_name);
        etAddress_no = (EditText)findViewById(R.id.Address_no);
        etAddress_street = (EditText)findViewById(R.id.Address_street);
        etAddress_city = (EditText)findViewById(R.id.Address_city);
        //etNo_Of_Meters = (EditText)findViewById(R.id.No_Of_Meters);
        et_Meter_ID = (EditText)findViewById(R.id.Meter_ID);
        etMeter_ID_List = (EditText)findViewById(R.id.meter_id_list);
        etContact = (EditText)findViewById(R.id.Contact);
        etEmail_address =(EditText) findViewById(R.id.Email_address);
        etPassword = (EditText) findViewById(R.id.Password);
        bRegister = (Button) findViewById(R.id.bRegister);
        etConfirmpwd=(EditText)findViewById(R.id.Confirm_password);

        sp = (Spinner) findViewById(R.id.spinner);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                n_o_meter_from_spinner=sp.getSelectedItem().toString();
                No_Of_Meters = Integer.parseInt(n_o_meter_from_spinner);

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_consumer_register, menu);
        return true;
    }

    public void next_idInsert(View view) {
        String current_id;
        current_id= String.valueOf(et_Meter_ID.getText());
        Meter_ID_Arr[array_index]=current_id;
        array_index++;
        current_list=current_list+current_id;
        etMeter_ID_List.setText(current_list);
        current_list=current_list+"\n";
        et_Meter_ID.setText("");
    }

    @Override
    public void onBackPressed() {

        final Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    protected boolean isOnLine(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnected()){
            return true;
        }
        else
            return false;

    }

    public void send_data(View view) {
        if(!isOnLine()){
            Toast.makeText(this, "Internet is not available", Toast.LENGTH_LONG).show();
            return;
        }
        attemptLogin();
    }


    ///////////////////////////////////////////////////////////////

    public void attemptLogin() {

        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        etEmail_address.setError(null);
        etPassword.setError(null);


        // Store values at the time of the login attempt.
        First_name = etFirst_name.getText().toString();
        Last_name = etLast_name.getText().toString();
        Address_no =etAddress_no.getText().toString();
        Address_Street =etAddress_street.getText().toString();
        Address_city = etAddress_city.getText().toString();
        No_Of_Meterstr = n_o_meter_from_spinner;
        //No_Of_Meterstr = etNo_Of_Meters.getText().toString(); // int
        Meter_IDstr =  et_Meter_ID.getText().toString();
        Contactstr =  etContact.getText().toString(); //for integer
        Email_address =etEmail_address.getText().toString();
        Password =  etPassword.getText().toString();
        Confirmpwd = etConfirmpwd.getText().toString();



        boolean cancel = false;
        View focusView = null;




///contacts shouldn`t be empty
        // Check for valid first name.
        if (TextUtils.isEmpty(First_name)) {
            etFirst_name.setError(getString(R.string.error_field_required));
            focusView = etFirst_name;
            cancel = true;
        }

        // Check for a valid last name.
        if (TextUtils.isEmpty(Last_name)) {
            etLast_name.setError(getString(R.string.error_field_required));
            focusView = etLast_name;
            cancel = true;
        }

        // Check for a valid No of meters.
//        if (TextUtils.isEmpty(No_Of_Meterstr)) {
//            etNo_Of_Meters.setError(getString(R.string.error_field_required));
//            focusView = etNo_Of_Meters;
//            cancel = true;
//        }else{
//            No_Of_Meters = Integer.parseInt(No_Of_Meterstr);
//        }

        // Check for a valid MeterIds.
        if (array_index!=No_Of_Meters) {
            et_Meter_ID.setError("Invalid no of meters");
            focusView = et_Meter_ID;
            cancel = true;
        }

        // Check for a valid Contact.
        if (TextUtils.isEmpty(Contactstr)) {
            etContact.setError(getString(R.string.error_field_required));
            focusView = etContact;
            cancel = true;
        }else{
            Contact = Integer.parseInt(Contactstr);
        }


        // Check for a valid email address.
        if (TextUtils.isEmpty(Email_address)) {
            etEmail_address.setError(getString(R.string.error_field_required));
            focusView = etEmail_address;
            cancel = true;
        } else if (!isEmailValid(Email_address)) {
            etEmail_address.setError(getString(R.string.error_invalid_email));
            focusView = etEmail_address;
            cancel = true;
        }

        // Check for a valid password.
        if (TextUtils.isEmpty(Password)) {
            etPassword.setError(getString(R.string.error_field_required));
            focusView = etPassword;
            cancel = true;
        }
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(Password) && !isPasswordValid(Password)) {
            etPassword.setError(getString(R.string.error_invalid_password));
            focusView = etPassword;
            cancel = true;
        }

        if(TextUtils.isEmpty(Confirmpwd)){
            etConfirmpwd.setError(getString(R.string.error_invalid_password));
            focusView = etConfirmpwd;
            cancel = true;
        }

        if(!Password.equals(Confirmpwd)){
            etConfirmpwd.setError(getString(R.string.error_incorrect_password));
            focusView = etConfirmpwd;
            cancel = true;
        }

     /*   // Check for a valid password.
        if (TextUtils.isEmpty(Confirmpwd)) {
            etConfirmpwd.setError(getString(R.string.error_field_required));
            focusView = etConfirmpwd;
            cancel = true;
        }
*/
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mAuthTask = new UserLoginTask();
            mAuthTask.execute((Void) null);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /////////////////////////////////////////////////////////////////////




    public class UserLoginTask extends AsyncTask<Void, Void, Void> {

        User user;

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("Type","Consumer"));
            dataToSend.add(new BasicNameValuePair("First_name",First_name));
            dataToSend.add(new BasicNameValuePair("Last_name", Last_name));
            dataToSend.add(new BasicNameValuePair("Address_no",Address_no));
            dataToSend.add(new BasicNameValuePair("Address_street", Address_Street));
            dataToSend.add(new BasicNameValuePair("Address_city",Address_city));
            dataToSend.add(new BasicNameValuePair("No_Of_Meters",No_Of_Meters+""));
            dataToSend.add(new BasicNameValuePair("Meter_ID",Meter_ID));
            dataToSend.add(new BasicNameValuePair("Meter_ID_Array_0", Meter_ID_Arr[0]));
            dataToSend.add(new BasicNameValuePair("Meter_ID_Array_1", Meter_ID_Arr[1]));
            dataToSend.add(new BasicNameValuePair("Meter_ID_Array_2", Meter_ID_Arr[2]));
            dataToSend.add(new BasicNameValuePair("Meter_ID_Array_3", Meter_ID_Arr[3]));
            dataToSend.add(new BasicNameValuePair("Meter_ID_Array_4", Meter_ID_Arr[4]));
            dataToSend.add(new BasicNameValuePair("Meter_ID_Array_5", Meter_ID_Arr[5]));
            dataToSend.add(new BasicNameValuePair("Meter_ID_Array_6", Meter_ID_Arr[6]));
            dataToSend.add(new BasicNameValuePair("Meter_ID_Array_7", Meter_ID_Arr[7]));
            dataToSend.add(new BasicNameValuePair("Meter_ID_Array_8", Meter_ID_Arr[8]));
            dataToSend.add(new BasicNameValuePair("Meter_ID_Array_9", Meter_ID_Arr[9]));
            dataToSend.add(new BasicNameValuePair("Contact", Contact + ""));
            dataToSend.add(new BasicNameValuePair("Email_address", Email_address));
            dataToSend.add(new BasicNameValuePair("Password", Password));
            //hard code


            //HttpParams httpRequestParams = new BasicHttpParams();
            //HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            //HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpParams httpRequestParams = getHttpRequestParams();

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post1 = new HttpPost(MainActivity.WEB_SERVER + "ConsumerType.php");
            HttpPost post2 = new HttpPost(MainActivity.WEB_SERVER + "meterID_entry.php");

            try {
                post1.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post1);


            } catch (Exception e) {
                e.printStackTrace();
                //Toast.makeText(getApplicationContext(),"Error, Registration failure", Toast.LENGTH_LONG).show();
            }

            try {
                post2.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post2);

            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }
        private HttpParams getHttpRequestParams() {
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            return httpRequestParams;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //Toast.makeText(getApplicationContext(),"Registration successfully", Toast.LENGTH_LONG).show();


//            progressDialog.dismiss();


        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




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
