package com.myapp.stranderdlog;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;


public class consumer_full_history_page extends FragmentActivity{
    ViewPager viewPager;
    PagerTabStrip pagerTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_full_history_page);
        FragmentPageAdapter ft = new FragmentPageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(ft);

        pagerTabStrip = (PagerTabStrip) findViewById(R.id.tab_strip);
        pagerTabStrip.setTextColor(android.R.color.white);

    }

}



/*
public class consumer_full_history_page extends FragmentActivity{
    ViewPager viewPager;
    PagerTabStrip pagerTabStrip;

    private String global_reading,global_reading_date,global_last_reading_date,last_reading_date_check;
    private int meter_id;

    int [] date_dif,usages;
    public  int [] date_usage_array = new int[31]; //hard coded for 31 days
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ArrayList<Limit_object> l_objects = new ArrayList<>();
    ArrayList<Limit_object> sorted_lobjects = new ArrayList<>();
    public static boolean is_limit_set=false;
    public static float limit_size=0;
    Date l_date_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_full_history_page);
        FragmentPageAdapter ft = new FragmentPageAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(ft);

        pagerTabStrip = (PagerTabStrip) findViewById(R.id.tab_strip);
        pagerTabStrip.setTextColor(android.R.color.white);



    }






    @Override
    public void onBackPressed() {

        final Intent intent = new Intent(this, Consumer_Home_page.class);
        startActivity(intent);
        finish();
    }

    public void set_limit_now(View view) {
        Intent intent3 = new Intent(this,  consumer_limit_show_grpahs.class);
        this.startActivity(intent3);
        finish();
    }
    public void get_readings(){
        meter_id=consumerMeterIDSet.m_id;
        //if it is wrong please set consumerMeterIDSet.bill_arr.length-1 to 0


        //sdf.parse(date_str);

        global_last_reading_date=consumerMeterIDSet.bill_arr[consumerMeterIDSet.bill_arr.length-1].Last_Reading_Date;
        global_reading_date=consumerMeterIDSet.bill_arr[consumerMeterIDSet.bill_arr.length-1].Current_Reading_Date;
        global_reading=consumerMeterIDSet.bill_arr[consumerMeterIDSet.bill_arr.length-1].Current_Reading;
        Log.d("Database Helper", "global_last_reading date = " + global_last_reading_date);
        try {
            l_date_d=sdf.parse(global_last_reading_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }



    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        //do algorithm
        int temp = 0,div;
        for (int j = 0; j < date_usage_array.length; j++) {
            if (date_usage_array[j]==0){
                temp+=1;

            }
            else{
                div=temp;
                while(temp>=0){
                    date_usage_array[j-temp]=(date_usage_array[j])/(div+1);
                    temp-=1;
                }
                temp=0;
            }
        }

        ////

        for (int i = 0; i < date_usage_array.length; i++) {
            valueSet1.add(new BarEntry((date_usage_array[i]), i));

        }

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        // BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 2");
        //barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        //dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        for (int j = 0; j < date_usage_array.length; j++) {
            xAxis.add(String.valueOf(j));
        }
        return xAxis;
    }
}

*/

/*
public class consumer_full_history_page extends FragmentActivity implements ActionBar.TabListener{

    ActionBar actionBar;
    ViewPager viewPager;
    FragmentPageAdapter ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_full_history_page);
        viewPager = (ViewPager) findViewById(R.id.pager);
        ft = new FragmentPageAdapter(getSupportFragmentManager());

        actionBar = getActionBar();
        viewPager.setAdapter(ft);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText("Global Reading").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Local Reading").setTabListener(this));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
*/




/*
public class consumer_full_history_page extends FragmentActivity implements ActionBar.TabListener{

    ActionBar actionBar;
    ViewPager viewPager;
    FragmentPageAdapter ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_full_history_page);
        viewPager = (ViewPager) findViewById(R.id.pager);
        ft = new FragmentPageAdapter(getSupportFragmentManager());

        actionBar = getActionBar();
        viewPager.setAdapter(ft);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText("Global Reading").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("Local Reading").setTabListener(this));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
*/