package com.uwics.uwidiscover.fragments.navdrawerfragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.uwics.uwidiscover.R;

import java.util.Calendar;

/**
 * @author Howard Edwards
 * @author Javon Davis
 */
public class EventDialogFragment extends DialogFragment {

    private String eventStartTime;
    private String eventEndTime;
    private String eventDate;
    private String eventDetails;
    private String eventVenue;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View rootView = getActivity().getLayoutInflater()
                .inflate(R.layout.fragment_dialog_event, null);

        eventStartTime = getArguments().getString("eventStartTime");
        eventEndTime = getArguments().getString("eventEndTime");
        eventDate = getArguments().getString("eventDate");
        eventDetails = getArguments().getString("eventDetails");
        String eventFaculty = getArguments().getString("eventFaculty");
        eventVenue = getArguments().getString("eventVenue");
        String eventType = getArguments().getString("eventType");

        TextView eventStartTimeText = (TextView) rootView.findViewById(R.id.event_start_time);
        TextView eventEndTimeText = (TextView) rootView.findViewById(R.id.event_end_time);
        TextView eventDetailsText = (TextView) rootView.findViewById(R.id.event_details);
        TextView eventFacultyText = (TextView) rootView.findViewById(R.id.event_faculty);
        TextView eventVenueText = (TextView) rootView.findViewById(R.id.event_venue);
        TextView eventTypeText = (TextView) rootView.findViewById(R.id.event_type);

        eventStartTimeText.setText(eventStartTime);
        eventEndTimeText.setText(eventEndTime);
        eventDetailsText.setText(eventDetails);
        eventFacultyText.setText(eventFaculty);
        eventVenueText.setText(eventVenue);
        eventTypeText.setText(eventType);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(rootView);
        if (eventDate != null) {
            switch (eventDate) {
                case "17/02/2016":
                    dialogBuilder.setTitle("DAY ONE");
                    break;
                case "18/02/2016":
                    dialogBuilder.setTitle("DAY TWO");
                    break;
                case "19/02/2016":
                    dialogBuilder.setTitle("DAY THREE");
                    break;
            }
        }

        dialogBuilder.setPositiveButton( getResources().getString(R.string.string_enable_notif),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setReminder();
                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        return dialog;
    }

    private void setReminder() {

        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.MONTH, getMonth());
        startTime.set(Calendar.YEAR, getYear());
        startTime.set(Calendar.DAY_OF_MONTH, getDay());
        startTime.set(Calendar.HOUR_OF_DAY, getStartHour());
        startTime.set(Calendar.MINUTE, getStartMinute());

        Calendar endTime = Calendar.getInstance();
        endTime.set(Calendar.MONTH, getMonth());
        endTime.set(Calendar.YEAR, getYear());
        endTime.set(Calendar.DAY_OF_MONTH, getDay());
        endTime.set(Calendar.HOUR_OF_DAY, getEndHour());
        endTime.set(Calendar.MINUTE, getEndMinute());

        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", startTime.getTimeInMillis());
        intent.putExtra("allDay", false);
        intent.putExtra("endTime", endTime.getTimeInMillis());
        intent.putExtra("eventLocation",eventVenue);
        intent.putExtra("title", eventDetails);
        startActivity(intent);
    }

    public int getMonth() {
        int month = Integer.parseInt(eventDate.substring(3,5));
        return month-1; //Calender starts counting at 0

    }

    public int getYear() {
        return Integer.parseInt(eventDate.substring(6,10));
    }

    public int getDay() {
        return Integer.parseInt(eventDate.substring(0,2));
    }

    public int getStartHour() {
        int endIndex = eventStartTime.indexOf(":");
        int hour = Integer.parseInt(eventStartTime.substring(0,endIndex));

        boolean isInEvening = eventStartTime.contains("PM");

        if(hour != 12 && isInEvening)
        {
            hour+=12;
        }

        return hour;
    }

    public int getEndHour() {
        int endIndex = eventEndTime.indexOf(":");
        int hour = Integer.parseInt(eventEndTime.substring(0,endIndex));

        boolean isInEvening = eventEndTime.contains("PM");

        if(hour != 12 && isInEvening)
        {
            hour+=12;
        }

        return hour;
    }

    public int getStartMinute() {
        int startIndex = eventStartTime.indexOf(":")+1;
        int endIndex = startIndex+2;

        return Integer.parseInt(eventStartTime.substring(startIndex,endIndex));
    }

    public int getEndMinute() {
        int startIndex = eventEndTime.indexOf(":")+1;
        int endIndex = startIndex+2;

        return Integer.parseInt(eventEndTime.substring(startIndex,endIndex));
    }
}
