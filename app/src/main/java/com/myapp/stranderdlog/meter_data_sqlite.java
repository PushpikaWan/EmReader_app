package com.myapp.stranderdlog;

/**
 * Created by pushpika on 1/3/16.
 */
public class meter_data_sqlite {

    int meter_id;
    int user_id;
    int set_limit;

    // constructors
    public meter_data_sqlite() {

    }

    public meter_data_sqlite(int meter_id,int user_id,int set_limit){
        this.meter_id = meter_id;
        this.user_id = user_id;
        this.set_limit = set_limit;
    }



    // setters

    public void setMeter_id(int meter_id){
        this.meter_id=meter_id;
    }

    public void setUser_id(int user_id){
        this.user_id= user_id;
    }

    public void setSet_limit(int set_limit){
        this.set_limit= set_limit;
    }

    //getters

    public int getMeter_id(){
        return this.meter_id;
    }

    public int getUser_id(){
        return this.meter_id;
    }

    public int getSet_limit(){
        return this.set_limit;
    }

}
