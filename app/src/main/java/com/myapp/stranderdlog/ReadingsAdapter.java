package com.myapp.stranderdlog;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by pushpika on 1/4/16.
 */
public class ReadingsAdapter extends BaseExpandableListAdapter {

    private Context ctx;
    private HashMap<String , List<String>> Readings_details;
    private List<String>  readings_list;

    public ReadingsAdapter(Context ctx,HashMap<String ,List<String >> Readings_details,List<String> readings_list){

        this.ctx=ctx;
        this.Readings_details = Readings_details;
        this.readings_list = readings_list;

    }

    @Override
    public int getGroupCount() {
        return readings_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return Readings_details.get(readings_list.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return readings_list.get(groupPosition);
    }

    @Override
    public Object getChild(int parent, int child) {
        return Readings_details.get(readings_list.get(parent)).get(child) ;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertView, ViewGroup parentView) {
        String group_title = (String) getGroup(parent);

        if(convertView == null){

            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parent_layout,parentView,false);
        }
        TextView parent_txt_view = (TextView) convertView.findViewById(R.id.parent_txt);
        parent_txt_view.setTypeface(null, Typeface.BOLD);
        parent_txt_view.setText(group_title);

        return convertView;
    }

    @Override
    public View getChildView(int parent, int child, boolean isLastChild, View convertView, ViewGroup parentView) {
        String child_title = (String)getChild(parent,child);
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_layout, parentView, false);
        }
        TextView child_txt_view = (TextView) convertView.findViewById(R.id.child_txt);
        child_txt_view.setText(child_title);


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
