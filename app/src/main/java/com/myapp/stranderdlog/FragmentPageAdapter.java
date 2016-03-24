package com.myapp.stranderdlog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

/**
 * Created by pushpika on 1/5/16.
 */
public class FragmentPageAdapter extends FragmentPagerAdapter{

    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LocalHistoryFragment();
            case 1:
                return new GlobalHistoryFragment();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position){
            case 0:
                return "Local Readings";
            case 1:
                return  "Global Readings";
        }
        return null;
    }
}
