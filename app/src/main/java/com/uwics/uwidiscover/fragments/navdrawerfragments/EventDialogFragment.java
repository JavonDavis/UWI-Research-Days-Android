package com.uwics.uwidiscover.fragments.navdrawerfragments;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.activities.miscactivities.NotificationReceiver;

/**
 * @author Howard Edwards
 */
public class EventDialogFragment extends DialogFragment {

    private ImageView eventBookmarkImage;
    private boolean isBookmarked = false; // TODO: Add boolean flag to Event class???
    private String notifStatus;
    private String eventStartTime;
    private String eventEndTime;
    private String eventDate;
    private String eventDetails;
    private String eventFaculty;
    private String eventVenue;
    private String eventType;
    private String eventCategory;

    private AlertDialog dialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View rootView = getActivity().getLayoutInflater()
                .inflate(R.layout.fragment_dialog_event, null);

        eventStartTime = getArguments().getString("eventStartTime");
        eventEndTime = getArguments().getString("eventEndTime");
        eventDate = getArguments().getString("eventDate");
        eventDetails = getArguments().getString("eventDetails");
        eventFaculty = getArguments().getString("eventFaculty");
        eventVenue = getArguments().getString("eventVenue");
        eventType = getArguments().getString("eventType");

        TextView eventStartTimeText = (TextView) rootView.findViewById(R.id.event_start_time);
        TextView eventEndTimeText = (TextView) rootView.findViewById(R.id.event_end_time);
        TextView eventDetailsText = (TextView) rootView.findViewById(R.id.event_details);
        TextView eventFacultyText = (TextView) rootView.findViewById(R.id.event_faculty);
        TextView eventVenueText = (TextView) rootView.findViewById(R.id.event_venue);
        TextView eventTypeText = (TextView) rootView.findViewById(R.id.event_type);

        eventBookmarkImage = (ImageView) rootView.findViewById(R.id.bookmark_icon);

        eventStartTimeText.setText(eventStartTime);
        eventEndTimeText.setText(eventEndTime);
        eventDetailsText.setText(eventDetails);
        eventFacultyText.setText(eventFaculty);
        eventVenueText.setText(eventVenue);
        eventTypeText.setText(eventType);
//        eventCategoryText.setText(eventCategory);

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
        // TODO: Evaluate what isBookmarked should be ; when we start saving event bookmarked state
        dialogBuilder.setPositiveButton(notifStatus = isBookmarked ?
                        getResources().getString(R.string.string_disable_notif)
                        : getResources().getString(R.string.string_enable_notif),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        dialog = dialogBuilder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpNotification();
            }
        });
        return dialog;
    }

    private void setUpNotification() {
        if (isBookmarked) {
            eventBookmarkImage.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
            Toast.makeText(getActivity(), "Reminder removed", Toast.LENGTH_SHORT).show();
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setText(
                    getActivity().getResources().getString(R.string.string_enable_notif));
        } else {
            // showNotification();
            eventBookmarkImage.setImageResource(R.drawable.ic_bookmark_black_24dp);
            Toast.makeText(getActivity(), "Reminder set", Toast.LENGTH_SHORT).show();
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setText(
                    getActivity().getResources().getString(R.string.string_disable_notif));
        }
        isBookmarked = !isBookmarked;
    }

    private void showNotification() {
//        eventBookmarkImage.setImageResource(R.drawable.ic_bookmark_black_24dp);

        Intent intent = new Intent(getActivity(), NotificationReceiver.class);
        PendingIntent pIntent = PendingIntent.getActivity(getActivity(), (int) System.currentTimeMillis(), intent, 0);
        Uri soundNotifUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification notification = new NotificationCompat.Builder(getActivity())
                .setContentTitle(eventType)
                .setContentText(eventStartTime + " - " + eventEndTime).setSmallIcon(R.mipmap.ic_notif)
                .setContentIntent(pIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(eventType + " @ "
                        + eventVenue
//                        + " ("
//                        + eventFaculty + ")"
                        + " happening soon!\n"
                        + eventStartTime + " - " + eventEndTime))
                .addAction(0, "Show Details", pIntent)
                .setSound(soundNotifUri)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
