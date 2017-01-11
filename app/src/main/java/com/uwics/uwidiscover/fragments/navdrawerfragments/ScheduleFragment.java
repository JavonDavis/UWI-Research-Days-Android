package com.uwics.uwidiscover.fragments.navdrawerfragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.activities.HomeActivity;
import com.uwics.uwidiscover.classes.models.Event;
import com.uwics.uwidiscover.fragments.EventDialogFragment;
import com.uwics.uwidiscover.fragments.EventListFragment;
import com.uwics.uwidiscover.utils.FireBaseController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * @author Javon Davis
 */
public class ScheduleFragment extends Fragment implements View.OnClickListener {

    // Calendar API has Jan starting @ 0
    private final static Calendar DAY_ONE = new GregorianCalendar(2017, 1, 1);
    private final static Calendar DAY_TWO = new GregorianCalendar(2017, 1, 2);
    private final static Calendar DAY_THREE = new GregorianCalendar(2017, 1, 3);

    private LinearLayout dayOneContainer;
    private LinearLayout dayTwoContainer;
    private LinearLayout dayThreeContainer;

    private List<Event> schedule = new ArrayList<>();
    private boolean fromLocalDatastore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((HomeActivity) getActivity()).setActionBarTitle(getResources().getString(R.string.uwi_research_day));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        dayOneContainer = (LinearLayout) rootView.findViewById(R.id.day_one_container);
        dayTwoContainer = (LinearLayout) rootView.findViewById(R.id.day_two_container);
        dayThreeContainer = (LinearLayout) rootView.findViewById(R.id.day_three_container);

        Drawable image = getActivity().getResources().getDrawable(R.mipmap.card_bacground);
        if (image != null)
            image.setAlpha(60);

        dayOneContainer.setBackground(image);
        dayTwoContainer.setBackground(image);
        dayThreeContainer.setBackground(image);

        TextView dayOneHeader = (TextView) rootView.findViewById(R.id.day_one_title);
        TextView dayTwoHeader = (TextView) rootView.findViewById(R.id.day_two_title);
        TextView dayThreeHeader = (TextView) rootView.findViewById(R.id.day_three_title);

        Button dayOneViewListButton = (Button) rootView.findViewById(R.id.day_one_view_list_button);
        Button dayTwoViewListButton = (Button) rootView.findViewById(R.id.day_two_view_list_button);
        Button dayThreeViewListButton = (Button) rootView.findViewById(R.id.day_three_view_list_button);

        dayOneViewListButton.setOnClickListener(this);
        dayTwoViewListButton.setOnClickListener(this);
        dayThreeViewListButton.setOnClickListener(this);

        SimpleDateFormat format = new SimpleDateFormat("EEEE MMMM dd", Locale.US);

        dayOneHeader.setText(format.format(DAY_ONE.getTime()));
        dayTwoHeader.setText(format.format(DAY_TWO.getTime()));
        dayThreeHeader.setText(format.format(DAY_THREE.getTime()));

        getURDSchedule();
        return rootView;
    }

    private void getURDSchedule() {
        schedule = ((FireBaseController) getActivity().getApplicationContext()).getEventList();
        onScheduleLoaded();
    }

    public void onScheduleLoaded() {
        View view;
        ScheduleItem scheduleItem;
        final int limit = 7;

        for (final Event event : schedule) {
            switch (event.getDate()) {
                case "01/02/2017":
                    if (dayOneContainer.getChildCount() < limit) {
                        view = getLayoutInflater(getArguments()).inflate(R.layout.schedule_item, null);
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
                        dayOneContainer.addView(view);
                    }
                    break;
                case "02/02/2017":
                    if (dayTwoContainer.getChildCount() < limit) {
                        view = getLayoutInflater(getArguments()).inflate(R.layout.schedule_item, null);
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
                                String[] location = event.getDetails().split(" ");
                                bundle.putString("eventDetails", event.getDetails());
                                bundle.putString("eventVenue", event.getVenue());
                                bundle.putString("eventType", event.getType());

                                eventDialogFragment.setArguments(bundle);
                                eventDialogFragment.show(getFragmentManager(), "eventDialogFragment");
                            }
                        });
                        dayTwoContainer.addView(view);
                    }
                    break;
                case "03/02/2017":
                    if (dayThreeContainer.getChildCount() < limit) {
                        view = getLayoutInflater(getArguments()).inflate(R.layout.schedule_item, null);
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
                        dayThreeContainer.addView(view);
                    }
                    break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).setActionBarTitle(getResources().getString(R.string.uwi_research_day));
    }

    @Override
    public void onClick(View v) {
        Bundle bundle;
        EventListFragment eventListFragment;

        switch (v.getId()) {
            case R.id.day_one_view_list_button:
                bundle = new Bundle();
                bundle.putString("day", "01/02/2017");
                bundle.putString("wDay", "Feb 1, 2017");
                eventListFragment = new EventListFragment();
                eventListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, eventListFragment)
                        .commit();
                break;
            case R.id.day_two_view_list_button:
                bundle = new Bundle();
                bundle.putString("day", "18/02/2017");
                bundle.putString("wDay", "Feb 2, 2017");
                eventListFragment = new EventListFragment();
                eventListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, eventListFragment)
                        .commit();
                break;
            case R.id.day_three_view_list_button:
                bundle = new Bundle();
                bundle.putString("day", "03/02/2017");
                bundle.putString("wDay", "Feb 3, 2017");
                eventListFragment = new EventListFragment();
                eventListFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, eventListFragment)
                        .commit();
                break;
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