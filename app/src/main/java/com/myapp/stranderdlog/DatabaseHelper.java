package com.myapp.stranderdlog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pushpika on 1/3/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database Helper","Database created sucessfully");
    }
    // Logcat tag
    private static final String LOG = "com.myapp.stranderdlog.DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 7;

    // Database Name
    private static final String DATABASE_NAME = "EM_new_one";

    // Table Names
    private static final String TABLE_USER_DATA = "user_data";
    private static final String TABLE_METER_DATA = "meter_data";
    private static final String TABLE_READING_DATA = "reading_data";


    // Common column names
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_METER_ID = "meter_id";


    // user_data Table - column names
    private static final String KEY_FIRST_NAME = "First_name";
    private static final String KEY_LAST_NAME = "Last_name";
    private static final String KEY_ADDRESS_NO = "address_no";
    private static final String KEY_ADDRESS_STREET = "address_Street";
    private static final String KEY_ADDRESS_CITY = "address_city";
    private static final String KEY_EMAIL_ADDRESS = "Email_address";
    private static final String KEY_PASSWORD = "Password";
    private static final String KEY_Contact = "Contact";


    // meter_data Table - column names
    private static final String KEY_SET_LIMIT = "set_limit";

    //reading_data Table - column names
    private static final String KEY_LAST_READING = "Last_Reading";
    private static final String KEY_LAST_READING_DATE = "Last_Reading_Date";
    private static final String KEY_CURRENT_READING = "Current_Reading";
    private static final String KEY_CURRENT_READING_DATE = "Current_Reading_Date";
    private static final String KEY_PERIOD = "Period";
    private static final String KEY_MONTH = "Month";
    private static final String KEY_USAGE = "Usage";
    private static final String KEY_RENTAL = "Rental";
    private static final String KEY_ARREARS = "Arrears";
    private static final String KEY_FULL_AMOUNT = "Full_Amount";
    private static final String KEY_RTABLE_ID = "rtable_id";
    private static final String KEY_READING_TYPE = "reading_type";


    // Table Create Statements
    // user_data table create statement
    private static final String CREATE_TABLE_USER_DATA = "CREATE TABLE IF NOT EXISTS " +
            TABLE_USER_DATA + "(" + KEY_USER_ID + " INTEGER PRIMARY KEY," +
            KEY_FIRST_NAME + " TEXT," + KEY_LAST_NAME + " TEXT," +
            KEY_ADDRESS_NO + " TEXT," + KEY_ADDRESS_STREET + " TEXT," +
            KEY_ADDRESS_CITY + " TEXT," + KEY_EMAIL_ADDRESS + " TEXT," +
            KEY_PASSWORD + " TEXT," + KEY_Contact + " INTEGER" +
            ");";

    // meter_data table create statement
    private static final String CREATE_TABLE_METER_DATA = "CREATE TABLE IF NOT EXISTS " +
            TABLE_METER_DATA + "(" + KEY_METER_ID + " INTEGER PRIMARY KEY," +
            KEY_USER_ID + " INTEGER," +
            KEY_SET_LIMIT + " TEXT" +
            ");";

    // reading_data table create statement
    private static final String CREATE_TABLE_READING_DATA = "CREATE TABLE IF NOT EXISTS " +
            TABLE_READING_DATA + "(" + KEY_RTABLE_ID + " INTEGER AUTO INCREMENT," +
            KEY_METER_ID + " INTEGER," +
            KEY_LAST_READING + " TEXT," + KEY_LAST_READING_DATE + " TEXT," +
            KEY_CURRENT_READING + " TEXT," + KEY_CURRENT_READING_DATE+ " TEXT," +
            KEY_PERIOD + " TEXT," + KEY_MONTH + " TEXT," +
            KEY_USAGE + " TEXT," + KEY_READING_TYPE + " TEXT ," +
            KEY_RENTAL + " TEXT,"+KEY_ARREARS + " TEXT,"+KEY_FULL_AMOUNT + " TEXT,"+
            "PRIMARY KEY ("+KEY_METER_ID + ","+KEY_RTABLE_ID+" )"+
            ");";




    @Override
    public void onCreate(SQLiteDatabase db) {


        // creating required tables
        db.execSQL(CREATE_TABLE_USER_DATA);
        Log.d("Database Helper", "user data tabel created sucessfully");
        db.execSQL(CREATE_TABLE_METER_DATA);
        Log.d("Database Helper", "meter data table created sucessfully");

        db.execSQL(CREATE_TABLE_READING_DATA);
        Log.d("Database Helper", "reading data table created sucessfully");

    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_METER_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_READING_DATA);

        // create new tables
        onCreate(db);
    }


    /*
 * Creating reading data
 */
    public void createReadingEntry(reading_data_sqlite rdata) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(KEY_TODO, todo.getNote());
        //values.put(KEY_STATUS, todo.getStatus());
        //values.put(KEY_CREATED_AT, getDateTime());
        values.put(KEY_METER_ID,rdata.getMeter_id());
        values.put(KEY_LAST_READING,rdata.getLast_Reading());
        values.put(KEY_LAST_READING_DATE,rdata.getLast_Reading_Date());
        values.put(KEY_CURRENT_READING,rdata.getCurrent_Reading());
        values.put(KEY_CURRENT_READING_DATE,rdata.getCurrent_Reading_Date());
        values.put(KEY_PERIOD,rdata.getPeriod());
        values.put(KEY_MONTH,rdata.getMonth());
        values.put(KEY_USAGE,rdata.getUsage());
        values.put(KEY_RENTAL,rdata.getRental());
        values.put(KEY_ARREARS,rdata.getArrears());
        values.put(KEY_FULL_AMOUNT,rdata.getFull_Amount());
        //values.put(KEY_RTABLE_ID, rdata.getid());
        values.put(KEY_READING_TYPE,rdata.getReading_type());

        // insert row
        long todo_id = db.insert(TABLE_READING_DATA, null, values);
        Log.d("Database Helper", "row inserted sucessfully" + rdata.getMonth());
      /*  // assigning tags to to
        for (long tag_id : tag_ids) {
            createTodoTag(todo_id, tag_id);
        }
        */
        //return todo_id;
    }

    /*
 * getting all todos
 * */
    public List<reading_data_sqlite> getReadingEntries(int meter_id) {

        List<reading_data_sqlite> meter_readings = new ArrayList<reading_data_sqlite>();
        String selectQuery = "SELECT  * FROM " + TABLE_READING_DATA+" WHERE "+KEY_METER_ID+" = "+meter_id+" ;";

       //Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                reading_data_sqlite td = new reading_data_sqlite();
                td.setLast_Reading(c.getString((c.getColumnIndex(KEY_LAST_READING))));
                td.setLast_Reading_Date(c.getString((c.getColumnIndex(KEY_LAST_READING_DATE))));
                td.setCurrent_Reading(c.getString((c.getColumnIndex(KEY_CURRENT_READING))));
                td.setCurrent_Reading_Date(c.getString((c.getColumnIndex(KEY_CURRENT_READING_DATE))));
                td.setPeriod(c.getString((c.getColumnIndex(KEY_PERIOD))));
                td.setMonth(c.getString((c.getColumnIndex(KEY_MONTH))));
                td.setUsage(c.getString((c.getColumnIndex(KEY_USAGE))));
                td.setRental(c.getString((c.getColumnIndex(KEY_RENTAL))));
                td.setArrears(c.getString((c.getColumnIndex(KEY_ARREARS))));
                td.setFull_Amount(c.getString((c.getColumnIndex(KEY_FULL_AMOUNT))));
                td.setReading_type((c.getString(c.getColumnIndex(KEY_READING_TYPE))));
                td.setid(c.getInt(c.getColumnIndex(KEY_RTABLE_ID)));

                // adding to todo list
                meter_readings.add(td);
            } while (c.moveToNext());
        }

        return meter_readings;
    }




    public void updateGlobalEntry(reading_data_sqlite rdata) {
        List<reading_data_sqlite> todos = new ArrayList<reading_data_sqlite>();




        String selectQuery = "SELECT  * FROM " + TABLE_READING_DATA + " WHERE "
                + KEY_METER_ID + " = ? " +" AND "+ KEY_READING_TYPE+" = ? "+ ";" ;
        //Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, new String[] {String.valueOf(rdata.getMeter_id()),"GLOBAL_READ"});

        Log.d("Database Helper", "in middle okkk");

        int tableID=10; //not valid
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                tableID=c.getInt(c.getColumnIndex(KEY_RTABLE_ID));

                Log.d("Database Helper", "in middle okkk ----2");         /*
 * Deleting a todo
 */
                Log.d("Database Helper", "row deleted created sucessfully"+rdata.getMonth()+rdata.getMeter_id()+rdata.getCurrent_Reading()+rdata.getLast_Reading()+"deleted id :"+String.valueOf(tableID));
                db.delete(TABLE_READING_DATA, KEY_RTABLE_ID + " = ?", new String[] { String.valueOf(tableID) });
                Log.d("Database Helper", "row deleted created sucessfully");
            } while (c.moveToNext());
        }




        //insert new data

        ContentValues values = new ContentValues();
        //values.put(KEY_TODO, todo.getNote());
        //values.put(KEY_STATUS, todo.getStatus());
        //values.put(KEY_CREATED_AT, getDateTime());
        values.put(KEY_METER_ID,rdata.getMeter_id());
        values.put(KEY_LAST_READING,rdata.getLast_Reading());
        values.put(KEY_LAST_READING_DATE,rdata.getLast_Reading_Date());
        values.put(KEY_CURRENT_READING,rdata.getCurrent_Reading());
        values.put(KEY_CURRENT_READING_DATE,rdata.getCurrent_Reading_Date());
        values.put(KEY_PERIOD,rdata.getPeriod());
        values.put(KEY_MONTH,rdata.getMonth());
        values.put(KEY_USAGE,rdata.getUsage());
        values.put(KEY_RENTAL,rdata.getRental());
        values.put(KEY_ARREARS,rdata.getArrears());
        values.put(KEY_FULL_AMOUNT,rdata.getFull_Amount());
        //values.put(KEY_RTABLE_ID,tableID);
        values.put(KEY_READING_TYPE,rdata.getReading_type());

        // insert row
        long todo_id = db.insert(TABLE_READING_DATA, null, values);
        Log.d("Database Helper", "row inserted sucessfully" +rdata.getMonth()+rdata.getMeter_id()+rdata.getCurrent_Reading()+rdata.getLast_Reading()+"inserted id : "+tableID);
      /*  // assigning tags to
        for (long tag_id : tag_ids) {
            createTodoTag(todo_id, tag_id);
        }
        */
        //return todo_id;

        }


    }




