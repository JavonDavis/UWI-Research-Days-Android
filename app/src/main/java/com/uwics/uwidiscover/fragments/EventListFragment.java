package com.uwics.uwidiscover.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.classes.models.Event;
import com.uwics.uwidiscover.fragments.navdrawerfragments.EventDialogFragment;
import com.uwics.uwidiscover.utils.ParseController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 02/02/16.
 */
public class EventListFragment extends Fragment {

    private final static Calendar DAY_ONE = new GregorianCalendar(2016, 1, 17);
    private final static Calendar DAY_TWO = new GregorianCalendar(2016, 1, 18);
    private final static Calendar DAY_THREE = new GregorianCalendar(2016, 1, 19);
    private String day;
    private LinearLayout dayContainer;

    private List<Event> schedule = new ArrayList<>();
    private boolean fromLocalDatastore;
    private Drawable image;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        day = getArguments().getString("day");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule_full, container, false);

        image = getActivity().getResources().getDrawable(R.mipmap.card_bacground);
        if (image != null)
            image.setAlpha(60);

        dayContainer = (LinearLayout) rootView.findViewById(R.id.day_container);

        SimpleDateFormat format = new SimpleDateFormat("EEEE MMMM dd", Locale.US);

        // loadSchedule();
        getURDSchedule();
        return rootView;
    }

    private void getURDSchedule() {
        schedule = ((ParseController) getActivity().getApplicationContext()).getEventList();
        onScheduleLoaded();
    }

//    private void loadSchedule() {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("schedule");
//        try {
//            List<ParseObject> objects = query.find();
//            ParseObject.pinAllInBackground(objects);
//            fromLocalDatastore = true;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
////        if (fromLocalDatastore)
////            query.fromLocalDatastore();
//
//        query.setLimit(200).findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if (e == null) {
//                    for (ParseObject object : objects) {
//                        schedule.add(new Event(object));
//                    }
//                    onScheduleLoaded();
//                }
//            }
//        });
//    }

    public void onScheduleLoaded() {
        View view;
        ScheduleItem scheduleItem;

        if (day != null) {
            for (final Event event : schedule) {
                if (event.getDate().equals(day)) {
                    view = getLayoutInflater(getArguments()).inflate(R.layout.schedule_item_full, null);
                    scheduleItem = new ScheduleItem(view);

                    scheduleItem.startTime.setText(event.getStartTime());
                    scheduleItem.endTime.setText(event.getEndTime());
                    scheduleItem.type.setText(event.getType());
                    scheduleItem.venue.setText(event.getVenue());
                    scheduleItem.details.setText(event.getDetails());

                    view.setOnClickListener(new View.OnClickListener() {
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
                            eventDialogFragment.show(getFragmentManager(), "eventDialogFragment");
                        }
                    });
                    LinearLayout layout = (LinearLayout) view.findViewById(R.id.container);
                    layout.setBackground(image);
                    dayContainer.addView(view);
                }
            }
        }
    }

    public class ScheduleItem {
        public TextView startTime;
        public TextView endTime;
        public TextView type;
        public TextView venue;
        public TextView details;

        public ScheduleItem(View view) {
            startTime = (TextView) view.findViewById(R.id.event_start_time);
            endTime = (TextView) view.findViewById(R.id.event_end_time);
            type = (TextView) view.findViewById(R.id.event_type);
            venue = (TextView) view.findViewById(R.id.event_venue);
            details = (TextView) view.findViewById(R.id.event_details);
        }
    }
}
