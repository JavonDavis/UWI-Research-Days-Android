package com.uwics.uwidiscover.classes.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.classes.models.Event;
import com.uwics.uwidiscover.fragments.EventDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shane on 2/12/16.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private List<Event> eventList;
    private Context context;

    public EventAdapter(Context context) {
        this.context = context;
        eventList = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_item, parent, false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {
        final Event event = eventList.get(position);

        holder.linearLayoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventDialogFragment eventDialogFragment = new EventDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("eventStartTime", event.getStartTime());
                bundle.putString("eventEndTime", event.getEndTime());
                bundle.putString("eventDate", event.getDate());
                bundle.putString("eventDetails", event.getDetails());
                bundle.putString("eventVenue", event.getVenue());
                bundle.putString("eventType", event.getType());

                eventDialogFragment.setArguments(bundle);
                eventDialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "eventDialogFragment");
            }
        });
        holder.details.setText(event.getDetails());
        holder.startTime.setText(event.getStartTime());
        holder.endTime.setText(event.getEndTime());
        holder.venue.setText(event.getVenue());
        holder.type.setText(event.getType());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void setEvents(List<Event> events) {
        eventList = events;
        notifyDataSetChanged();
    }

//    public Event removeItem(int position) {
//        final Event model = eventList.remove(position);
//        notifyItemRemoved(position);
//        return model;
//    }
//
//    public void addItem(int position, Event model) {
//        eventList.add(position, model);
//        notifyItemInserted(position);
//    }
//
//    public void moveItem(int fromPosition, int toPosition) {
//        final Event model = eventList.remove(fromPosition);
//        eventList.add(toPosition, model);
//        notifyItemMoved(fromPosition, toPosition);
//    }

    public class EventHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayoutContainer;
        TextView startTime, endTime, type, venue, details;

        public EventHolder(View itemView) {
            super(itemView);
            linearLayoutContainer = (LinearLayout) itemView.findViewById(R.id.schedule_linear_layout_container);
            startTime = (TextView) itemView.findViewById(R.id.event_start_time);
            endTime = (TextView) itemView.findViewById(R.id.event_end_time);
            type = (TextView) itemView.findViewById(R.id.event_type);
            venue = (TextView) itemView.findViewById(R.id.event_venue);
            details = (TextView) itemView.findViewById(R.id.event_details);
        }
    }
}
