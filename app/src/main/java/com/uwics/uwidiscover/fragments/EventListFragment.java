package com.uwics.uwidiscover.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.SaveCallback;
import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.activities.HomeActivity;
import com.uwics.uwidiscover.classes.models.Event;
import com.uwics.uwidiscover.utils.ParseController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 02/02/16.
 */
public class EventListFragment extends Fragment {

    private String day;
    private LinearLayout dayContainer;

    private List<Event> schedule = new ArrayList<>();
    //could just use one filter but lazy and constrained for time
    //TODO - use one filter instead of a separate one for faculty
    private boolean hasFilter;
    private boolean hasFaculty;
    private Drawable image;

    //Keywords belong to each faculty/unit repetitions were handled individually in the db as there were few
    public ArrayList<String> FHE_Locations = new ArrayList<>(Arrays.asList("the writing centre", "animation lab", "faculty courtyard", "the assembly hall", "n4", "n1", "lt1", "the assembly hall"));
    public ArrayList<String> FMS_Locations = new ArrayList<>(Arrays.asList("fms", "uwi mona dental polyclinic"));
    public ArrayList<String> LAW_Locations = new ArrayList<>(Collections.singletonList("law"));
    public ArrayList<String> FSS_Locations = new ArrayList<>(Arrays.asList("ctpr office", "exhibition village", "graduation lawn", "spsw booth", "the assembly hall", "hugh shearer", "sr 12", "uwi regional headquarters"));
    public ArrayList<String> FST_Locations = new ArrayList<>(Arrays.asList("spine", "biotechnology", "geology", "earthquake", "herbarium", "physics", "chemistry", "science lecture theatre", "senate", "pesticide", "faculty of science and technology"));
    public ArrayList<String> IGDS_Locations = new ArrayList<>(Collections.singletonList("multi-functional room"));
    public ArrayList<String> MSS_Locations = new ArrayList<>(Collections.singletonList("mona social services"));
    public ArrayList<String> OGSR_Locations = new ArrayList<>(Arrays.asList("hr conference room", "office of graduate studies and research", "pglc"));
    public ArrayList<String> LIB_Locations = new ArrayList<>(Arrays.asList("uwi mona main library", "pglc", "uwi mona library", "library conference room"));


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        hasFilter = getArguments().containsKey("filter");
        hasFaculty = getArguments().containsKey("faculty");

        if (getArguments().containsKey("day"))
            day = getArguments().getString("day");

        ((HomeActivity) getActivity()).setActionBarTitle(getArguments().getString("wDay"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule_full, container, false);

        image = getActivity().getResources().getDrawable(R.mipmap.card_bacground);
        if (image != null)
            image.setAlpha(60);

        dayContainer = (LinearLayout) rootView.findViewById(R.id.day_container);
        schedule = ((ParseController) getActivity().getApplicationContext()).getEventList();
        if (hasFilter) {
            getFilteredSchedule();
        } else if (hasFaculty) {
            getFacultySchedule();
        } else {
            getDatedSchedule();
        }
        return rootView;
    }

    private void getFacultySchedule() {
        String faculty = getArguments().getString("faculty");
        View view;
        ScheduleItem scheduleItem;

        for (final Event event : schedule) {
            //couple null checks
            if (faculty != null && event.getFacultyTag() != null) {
                if (event.getFacultyTag().toLowerCase().contains(faculty)) {
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

    private void getDatedSchedule() {
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


    public void getFilteredSchedule() {
        String filter = getArguments().getString("filter");
        View view;
        ScheduleItem scheduleItem;

        for (final Event event : schedule) {
            //chose to update here because of convenience
            /*runUpdate(event, FHE_Locations, "FHE");
            runUpdate(event, FMS_Locations, "FMS");
            runUpdate(event, LAW_Locations, "LAW");
            runUpdate(event, FSS_Locations, "FSS");
            runUpdate(event, FST_Locations, "FST");
            runUpdate(event, IGDS_Locations, "IGDS");
            runUpdate(event, MSS_Locations, "MSS");
            runUpdate(event, OGSR_Locations, "OGSR");
            runUpdate(event, LIB_Locations, "LIB");*/

            //couple null checks
            if (filter != null && event.getDetails() != null && event.getType() != null) {
                if (event.getDetails().toLowerCase().contains(filter) || event.getType().toLowerCase().contains(filter)) {
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

    //Method used to update the faculty columns of the event objects since we weren't
    // given them inferences had to be made based on reasonable facts
    private void runUpdate(final Event event, ArrayList<String> locations, String tag) {
        Log.d("FAC", tag);
        for (String location : locations) {
            String venue = event.getVenue();
            venue = venue.replaceAll("\n", " ");
            event.setVenue(venue);

            if (venue.toLowerCase().contains(location)) {
                event.setFacultyTag(tag);
                event.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        Log.e("Saved:", event.getType());
                    }
                });
                break;
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
