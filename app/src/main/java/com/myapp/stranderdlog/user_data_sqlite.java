package com.myapp.stranderdlog;

/**
 * Created by pushpika on 1/3/16.
 */
public class user_data_sqlite {

    int user_id;
    String First_name,Last_name,address_no,address_Street,address_city,Email_address,Password;
    int Contact;

    // constructors
    public user_data_sqlite() {

    }

    //for meter readers
    public user_data_sqlite(int user_id,String First_name,String Last_name,String address_no,String address_Street,String address_city,String Email_address,int Contact){
        this.user_id=user_id;
        this.First_name = First_name;
        this.Last_name = Last_name;
        this.address_no = address_no;
        this.address_Street = address_Street;
        this.address_city = address_city;
        this.Email_address= Email_address;
        this.Contact=Contact;
    }



    public user_data_sqlite(String First_name,String Last_name,String address_no,String address_Street,String address_city,String Email_address,String Password,int Contact){
        this.First_name = First_name;
        this.Last_name = Last_name;
        this.address_no = address_no;
        this.address_Street = address_Street;
        this.address_city = address_city;
        this.Password = Password;
        this.Email_address= Email_address;
        this.Contact=Contact;
    }


    // setters


    // getters
    public int getUser_id() {

        return this.user_id;
    }

    public String getFirst_name() {
        return this.First_name;
    }


    public String getLast_name() {
        return this.Last_name;
    }

    public String getaddress_no() {
        return this.address_no;
    }
    public String getaddress_Street() {
        return this.address_Street;
    }
    public String getaddress_city() {
        return this.address_city;
    }
    public String getPassword() {
        return this.Password;
    }
    public String getEmail_address() {
        return this.Email_address;
    }
    public int getContact() {
        return this.Contact;
    }


}