package com.myapp.stranderdlog;

/**
 * Created by Pushpika Wannniachch on 6/2/2015.
 */
public class User {
   String Type,First_name,Last_name,address_no,address_Street,address_city,Email_address,Password;
   int Contact;

    public User(String First_name,String Last_name,String address_no,String address_Street,String address_city,String Email_address,String Password,int contact){
        this.First_name = First_name;
        this.Last_name = Last_name;
        this.address_no = address_no;
        this.address_Street = address_Street;
        this.address_city = address_city;
        this.Password = Password;
        this.Email_address= Email_address;
        this.Contact=contact;
    }

    public User(String Email_address,String Password){
        First_name = "";
        Last_name = "";
        this.Password= Password;
        this.Email_address = Email_address;
    }

    public User(String Fname,String lname,String Email_address,String Password){
        First_name = Fname;
        Last_name = lname;
        this.Password= Password;
        this.Email_address = Email_address;
    }

}

class meterReader extends User{
    String Covering_areas;
    public meterReader(String First_name, String Last_name, String address_no, String address_Street, String address_city, String Email_address, String Password, int contact,String Covering_areas) {
        super(First_name, Last_name, address_no, address_Street, address_city, Email_address, Password, contact);
        this.Covering_areas = Covering_areas;
    }
}

class Cosumer extends User{
    int No_of_meters,Meter_ID;

    public Cosumer(String First_name, String Last_name, String address_no, String address_Street, String address_city, String Email_address, String Password, int contact,int no_of_meters) {
        super(First_name, Last_name, address_no, address_Street, address_city, Email_address, Password, contact);
        this.No_of_meters=no_of_meters;

    }
}