package com.myapp.stranderdlog;

/**
 * Created by pushpika on 11/17/15.
 */
public class Bill {
    String Last_Reading,Last_Reading_Date,Current_Reading,Current_Reading_Date,Period,Month,Usage,Rental,Arrears,Full_Amount;

    public Bill(String L_read,String L_read_date,String C_read,String C_read_date,String period,String month,
                String usage,String rental,String arrears,String full_Amount){
        this.Last_Reading=L_read;
        this.Last_Reading_Date=L_read_date;
        this.Current_Reading=C_read;
        this.Current_Reading_Date = C_read_date;
        this.Period = period;
        this.Month = month;
        this.Usage = usage;
        this.Rental = rental;
        this.Arrears = arrears;
        this.Full_Amount = full_Amount;

    }

}
