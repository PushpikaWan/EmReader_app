package com.myapp.stranderdlog;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.net.NetworkInterface;
import java.util.ArrayList;

public class ConsumerCameraActivity extends Activity implements View.OnClickListener {
    //private static final String SERVER_ADDRESS = "http://ec2-52-27-23-106.us-west-2.compute.amazonaws.com/";
    //keep track of camera capture intent
    final int CAMERA_CAPTURE = 1;
    private Uri picUri;
    //keep track of cropping intent
    final int PIC_CROP = 2;
    UserLocalStore userLocalStore;
    private String Meter_ID;
    private Bitmap crop_4to=null,full_4to=null,crop_4to_id=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_camera_activity_main);
        //retrieve a reference to the UI button
        Button captureBtn = (Button)findViewById(R.id.capture_btn);
        Button save_sendBtn = (Button)findViewById(R.id.save_send_btn);
        Button manually_enterBtn = (Button) findViewById(R.id.manual_enter);

        captureBtn.setOnClickListener(this);
        save_sendBtn.setOnClickListener(this);
        manually_enterBtn.setOnClickListener(this);


        //userLocalStore = new UserLocalStore(this);
        //SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
        //final SharedPreferences.Editor prefsEditor1 = myPrefs.edit();


        Meter_ID = ConsumerSelectMeterId.cur_id;
        Log.v("happened_first", Meter_ID);
       // String Last_name = myPrefs2.getString("Last_name","");


    }

    @Override
    public void onBackPressed() {

        final Intent intent = new Intent(this, Consumer_Home_page.class);
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

    public void onClick(View v) {
        if (v.getId() == R.id.capture_btn) {
            try {
                //use standard intent to capture an image
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //we will handle the returned data in onActivityResult
                startActivityForResult(captureIntent, CAMERA_CAPTURE);
            }
            catch(ActivityNotFoundException anfe){
                //display an error message
                String errorMessage = "Whoops - your device doesn't support capturing images!";
                Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
                toast.show();
            }

        }

        if(v.getId()==R.id.save_send_btn){
            //need try catch block if there should n`t be null
            if(isOnLine()) {
                if (full_4to != null && crop_4to != null) {
                    new uploadImage(full_4to, (Meter_ID + "_full")).execute();
                    new uploadImage(crop_4to, (Meter_ID)).execute();
                    Intent intent1 = new Intent(this, Consumer_image_data_get.class);
                    startActivity(intent1);
                    finish();
                } else {
                    if (full_4to == null)
                        Toast.makeText(getApplicationContext(), "Error,full image is not available ", Toast.LENGTH_SHORT).show();
                    else if (crop_4to == null)
                        Toast.makeText(getApplicationContext(), "Error,crop_reading image is not available", Toast.LENGTH_SHORT).show();

                }
            }
            else{
                Toast.makeText(this, "Internet is not available", Toast.LENGTH_LONG).show();
                return;
            }

        }

        if(v.getId()==R.id.manual_enter) {
            Intent intent2 = new Intent(this, enterDataManually.class);
            startActivity(intent2);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            //user is returning from capturing an image using the camera
            if(requestCode == CAMERA_CAPTURE && data!=null){
                //get the Uri for the captured image
                picUri = data.getData();
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                full_4to = photo;
                //new uploadImage(photo,(Meter_ID+"_full")).execute();   //upload 4to

                ImageView picView = (ImageView)findViewById(R.id.full_image);
                //display the returned full image
                picView.setImageBitmap(photo);
                Toast.makeText(getApplicationContext(),"crop meter reading", Toast.LENGTH_LONG).show();
                performCrop();
            }
            else if(requestCode == PIC_CROP){
                //get the returned data
                Bundle extras = data.getExtras();
                //get the cropped bitmap
                Bitmap thePic = extras.getParcelable("data");
                Bitmap photo_2 = (Bitmap) data.getExtras().get("data");
                crop_4to = photo_2;

                //new uploadImage(photo,(Meter_ID+"_crop")).execute();  //upload cropped 4to

                //retrieve a reference to the ImageView
                ImageView picView = (ImageView)findViewById(R.id.picture_crop_reading);
                //display the returned cropped image
                picView.setImageBitmap(thePic);
            }

        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void performCrop(){
        try {
            //call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");


//            //set crop properties
//            cropIntent.putExtra("crop", "true");
//            //indicate aspect of desired crop
//            cropIntent.putExtra("aspectX", 1);
//            cropIntent.putExtra("aspectY", 1);
//            //indicate output X and Y
//            cropIntent.putExtra("outputX", 128);
//            cropIntent.putExtra("outputY", 128);

            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);

        }
        catch(ActivityNotFoundException anfe){
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private class uploadImage extends AsyncTask<Void,Void,Void> {
        Bitmap image;
        String name;

        public uploadImage (Bitmap image, String name){
            this.image=image;
            this.name=name;
        }
        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("image",encodedImage));
            dataToSend.add(new BasicNameValuePair("name",name));

            HttpParams httpRequestParams = getHttpRequestParams();
            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(MainActivity.WEB_SERVER+ "SavePicture.php");
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);

            }
            catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Failed", Toast.LENGTH_SHORT).show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(),"Image uploaded", Toast.LENGTH_SHORT).show();

        }

        private HttpParams getHttpRequestParams(){
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, 1000 * 30);
            HttpConnectionParams.setSoTimeout(httpRequestParams, 1000 * 30);
            return httpRequestParams;
        }
    }


}
