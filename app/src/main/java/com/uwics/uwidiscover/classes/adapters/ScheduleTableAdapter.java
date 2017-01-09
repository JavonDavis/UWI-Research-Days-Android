package com.uwics.uwidiscover.classes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.classes.models.Schedule;

import java.util.ArrayList;

/**
 * Created by Howard on 1/23/2016.
 */
public class ScheduleTableAdapter extends BaseAdapter {

    private final ArrayList<Schedule> scheduleArrayList;
    private final LayoutInflater mInflater;

    public ScheduleTableAdapter(Context context, ArrayList<Schedule> scheduleArrayList) {
        mInflater = LayoutInflater.from(context);
        this.scheduleArrayList = scheduleArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Schedule schedule = (Schedule) getItem(position);

        if (convertView == null)
            convertView = mInflater.inflate(R.layout.fragment_schedule_item, null);

        TextView scheduleTime = (TextView) convertView.findViewById(R.id.schedule_table_time);
        TextView scheduleType = (TextView) convertView.findViewById(R.id.schedule_table_type);
        TextView scheduleDetails = (TextView) convertView.findViewById(R.id.schedule_table_details);
        TextView scheduleLocation = (TextView) convertView.findViewById(R.id.schedule_table_location);

        scheduleTime.setText(schedule.getTime());
        scheduleType.setText(schedule.getType());
        scheduleDetails.setText(schedule.getDetails());
        scheduleLocation.setText(schedule.getLocation());

        return convertView;
    }

    @Override
    public int getCount() {
        return scheduleArrayList.size();
    }

    @Override
    public Schedule getItem(int position) {
        return scheduleArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class EventViewHolder
    {
        public TextView time;
        public TextView type;
        public TextView details;
        public TextView venue;

        public EventViewHolder(View itemView)
        {

        }
    }
}
