package com.uwics.uwidiscover.activities.miscactivities;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.activities.HomeActivity;
import com.uwics.uwidiscover.classes.models.Event;
import com.uwics.uwidiscover.utils.ConnectionHelper;
import com.uwics.uwidiscover.utils.FireBaseController;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Howard Edwards
 */
public class SplashActivity extends AppCompatActivity {

    private final String FROMLOCALDATASTORE = "FromLocalDatastoreFile";
    private List<Event> schedule = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences(FROMLOCALDATASTORE, 0);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        DilatingDotsProgressBar mProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.showNow();

        if (ConnectionHelper.isNetworkAvailable(this) || sharedPreferences.getBoolean("from_datastore", false)) {
            loadSchedule();
        } else {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setTitle(getString(R.string.string_connection_error_title))
                    .setMessage(R.string.string_connection_error_message)
                    .setPositiveButton(R.string.string_connection_try_again, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SplashActivity.this.recreate();
                        }
                    }).setNegativeButton(R.string.string_exit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    System.exit(0);
                }
            })/*.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    SplashActivity.this.recreate();
                }
            })*/.show().setCanceledOnTouchOutside(false);
        }
    }

    private void loadSchedule() {
        //ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
        DatabaseReference objects = FirebaseDatabase.getInstance().getReference().child("urdschedule");
        objects.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Event>> events = new GenericTypeIndicator<List<Event>>() {};
                schedule = dataSnapshot.getValue(events);
                ((FireBaseController) getApplicationContext()).setEventList(schedule);
                delay();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SplashActivity.this);
                dialogBuilder.setTitle(getString(R.string.string_connection_error_title))
                        .setMessage(R.string.string_connection_error_message)
                        .setPositiveButton(R.string.string_connection_try_again, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SplashActivity.this.recreate();
                            }
                        }).setNegativeButton(R.string.string_exit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                })/*.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialog) {
                                        SponsorActivity.this.recreate();
                                    }
                                })*/.show().setCanceledOnTouchOutside(false);
            }
        });

            if (!sharedPreferences.getBoolean("from_datastore", false)) {
//                List<Event> objects = query.setLimit(200).find();
//                Event.pinAllInBackground(objects);
                sharedPreferences.edit().putBoolean("from_datastore", true).apply();
            }
//            query.setLimit(200)
//                    .findInBackground(new FindCallback<Event>() {
//                        @Override
//                        public void done(List<Event> events, ParseException e) {
//                            if (e == null) {
////                                for (Event event : events) {
////                                    schedule.add(event);
////                                }
//                                ((ParseController) getApplicationContext()).setEventList(schedule);
//                                delay();
//                            } else {
//                                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SplashActivity.this);
//                                dialogBuilder.setTitle(getString(R.string.string_connection_error_title))
//                                        .setMessage(R.string.string_connection_error_message)
//                                        .setPositiveButton(R.string.string_connection_try_again, new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                SplashActivity.this.recreate();
//                                            }
//                                        }).setNegativeButton(R.string.string_exit, new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        finish();
//                                        System.exit(0);
//                                    }
//                                })/*.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                                    @Override
//                                    public void onDismiss(DialogInterface dialog) {
//                                        SponsorActivity.this.recreate();
//                                    }
//                                })*/.show().setCanceledOnTouchOutside(false);
//                            }
//                        }
//                    });

    }

    private void delay() {
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
}
