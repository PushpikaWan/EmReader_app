package com.myapp.stranderdlog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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


public class register extends ActionBarActivity {

    Button bRegister;
    EditText etFirst_name,etLast_name,etEmail_address,etPassword;
    String Fname,Lname,Email,Pwd;
    ProgressDialog progressDialog;
    private UserLoginTask mAuthTask = null;
    public static final int CONNECTION_TIMEOUT = 1000*15;
    public static final String SERVER_ADDRESS = "http://ec2-52-27-23-106.us-west-2.compute.amazonaws.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirst_name = (EditText)findViewById(R.id.etFirst_name);
        etLast_name = (EditText) findViewById(R.id.etLast_name);
        etEmail_address =(EditText) findViewById(R.id.etEmail_address);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);

        //bRegister.setOnClickListener(this);

    }

    public void send_data(View view) {
        Fname = etFirst_name.getText().toString();
        Lname = etLast_name.getText().toString();
        Email =etEmail_address.getText().toString();
        Pwd =  etPassword.getText().toString();

        //User user = new User(Fname,Lname,Email,Pwd);
        mAuthTask = new UserLoginTask();
        mAuthTask.execute((Void) null);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Void> {

        User user;

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("First_name",Fname));
            dataToSend.add(new BasicNameValuePair("Last_name", Lname));
            dataToSend.add(new BasicNameValuePair("Email_address", Email));
            dataToSend.add(new BasicNameValuePair("Password", Pwd));

            //HttpParams httpRequestParams = new BasicHttpParams();
            //HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            //HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpParams httpRequestParams = getHttpRequestParams();

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "reg.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);

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
//            progressDialog.dismiss();


        }

    }

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
}
