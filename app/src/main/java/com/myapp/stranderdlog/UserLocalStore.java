package com.myapp.stranderdlog;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Pushpika Wannniachch on 6/2/2015.
 */
public class UserLocalStore {
    public static final String SP_NAME= "UserDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME,0);
    }

    public void  storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        Log.v("happened", "meeka");
        spEditor.putString("First_name","den wath harida");
        spEditor.putString("Last_name",user.Last_name);
        spEditor.putString("Email_address",user.Email_address);
        spEditor.putString("Password",user.Password);
        spEditor.apply();
    }

    /*public User getLoggedInUser(){
        if (!userLocalDatabase.getBoolean("loggedIn", false)) { //new edited
            return null;
        }
        //String First_name="Hishara";
        //String Last_name = "Perera";
        String First_name = userLocalDatabase.getString("First_name","");
        String Last_name = userLocalDatabase.getString("Last_name","");
        String Email_address = userLocalDatabase.getString("Email_address","");
        String Password = userLocalDatabase.getString("Password","");

        return new User (First_name,Last_name,Email_address,Password);
    }*/

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn",loggedIn);
        spEditor.apply();
    }

    public boolean getUserLoggedIn(){
        if(userLocalDatabase.getBoolean("loggedIn",false)== true){
            return true;
        }
        else{
            return false;
        }
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.apply();
    }

}
