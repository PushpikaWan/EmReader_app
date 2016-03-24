package com.myapp.stranderdlog;

/**
 * Created by pushpika on 1/3/16.
 */
public class reading_data_sqlite {

    int id;
    int meter_id;
    String Last_Reading, Last_Reading_Date, Current_Reading, Current_Reading_Date, Period, Month, Usage,Rental,Arrears,Full_Amount;
    String Reading_type; //global,local
    // constructors
    public reading_data_sqlite(){

    }

    public reading_data_sqlite(String Reading_type,int meter_id ,String Last_Reading,String Last_Reading_Date,String Current_Reading,String Current_Reading_Date,
                               String Period,String Month,String Usage,String rental,String arrears,String full_Amount) {
        this.Reading_type=Reading_type;
        this.meter_id= meter_id;
        this.Last_Reading = Last_Reading;
        this.Last_Reading_Date = Last_Reading_Date;
        this.Current_Reading = Current_Reading;
        this.Current_Reading_Date = Current_Reading_Date;
        this.Period = Period;
        this.Month = Month;
        this.Usage = Usage;
        this.Rental = rental;
        this.Arrears = arrears;
        this.Full_Amount = full_Amount;
    }

    // setters

    public void setid(int id) {
        this.id=id;
    }

    public void setMeter_id(int meter_id) {
        this.meter_id=meter_id;
    }

    public void setLast_Reading(String last_reading) {
        this.Last_Reading=last_reading;
    }

    public void setLast_Reading_Date(String last_Reading_Date) {
        this.Last_Reading_Date=last_Reading_Date;
    }
    public void setCurrent_Reading(String current_Reading) {
        this.Current_Reading=current_Reading;
    }
    public void setCurrent_Reading_Date(String current_Reading_Date) {
        this.Current_Reading_Date=current_Reading_Date;
    }
    public void setPeriod(String period) {
        this.Period=period;
    }
    public void setMonth(String month) {
        this.Month=month;
    }
    public void setUsage(String usage) {
        this.Usage=usage;
    }
    public void setReading_type(String reading_type) {
        this.Reading_type=reading_type;
    }

    public void setRental(String rental) {
        this.Rental=rental;
    }
    public void setArrears(String arrears) {
        this.Arrears=arrears;
    }
    public void setFull_Amount(String full_Amount) {
        this.Full_Amount=full_Amount;
    }

    // getters
    public int getid() {

        return this.id;
    }

    public int getMeter_id() {

        return this.meter_id;
    }


    public String getLast_Reading() {
        return this.Last_Reading;
    }

    public String getLast_Reading_Date() {
        return this.Last_Reading_Date;
    }
    public String getCurrent_Reading() {
        return this.Current_Reading;
    }
    public String getCurrent_Reading_Date() {
        return this.Current_Reading_Date;
    }
    public String getPeriod() {
        return this.Period;
    }
    public String getMonth() {
        return this.Month;
    }
    public String getUsage() {
        return this.Usage;
    }
    public String getReading_type() {
        return this.Reading_type;
    }
    public String getRental() {
        return this.Rental;
    }
    public String getArrears() {
        return this.Arrears;
    }
    public String getFull_Amount() {
        return this.Full_Amount;
    }

}

