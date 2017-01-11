package com.uwics.uwidiscover.activities.miscactivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.activities.HomeActivity;
import com.uwics.uwidiscover.classes.models.Event;
import com.uwics.uwidiscover.utils.FireBaseController;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Howard Edwards
 */

// TODO: Perform loading of schedule during showing of this page:
public class SponsorActivity extends AppCompatActivity {

    final String FROMLOCALDATASTORE = "FromLocalDatastoreFile";
    private SharedPreferences sharedPreferences;
    private List<Event> schedule = new ArrayList<>();

//    private int[] sponsorsImages = new int[]{R.drawable.sponsor_hawkeye_banner,
//            R.drawable.sponsor_arrc_media_banner,
//            R.drawable.sponsor_cardi_banner,
//            R.drawable.sponsor_epic_technologies_banner,
//            R.drawable.sponsor_jipo_banner,
//            R.drawable.sponsor_scientific_research_council_banner,
//            R.drawable.sponsor_uwicfptv_banner,
//            R.drawable.sponsor_vision_2030_banner};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences(FROMLOCALDATASTORE, 0);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsor_activity);

//        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        RecyclerViewDisabler mRecyclerDisabler = new RecyclerViewDisabler();
//        mRecyclerView.addOnItemTouchListener(mRecyclerDisabler);
//        mRecyclerView.removeOnItemTouchListener(mRecyclerDisabler);
//
//        StaggeredGridLayoutManager mStaggeredGridLayoutManager =
//                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
//
//        SponsorsAdapter mAdapter = new SponsorsAdapter(this, sponsorsImages);
//        mRecyclerView.setAdapter(mAdapter);

        DilatingDotsProgressBar mProgressBar = (DilatingDotsProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.showNow();

        loadSchedule();
    }

    private void loadSchedule() {
        //ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
        DatabaseReference objects = FirebaseDatabase.getInstance().getReference().child("results");
        objects.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Event>> events = new GenericTypeIndicator<List<Event>>() {};
                schedule = dataSnapshot.getValue(events);
                ((FireBaseController) getApplicationContext()).setEventList(schedule);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SponsorActivity.this);
                dialogBuilder.setTitle(getString(R.string.string_connection_error_title))
                        .setMessage(R.string.string_connection_error_message)
                        .setPositiveButton(R.string.string_connection_try_again, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SponsorActivity.this.recreate();
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
    //old loadschedule method
//    private void loadSchedule() {
//        ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
//        try {
//            if (!sharedPreferences.getBoolean("from_datastore", false)) {
//                List<Event> objects = query.setLimit(200).find();
//                //Event.pinAllInBackground(objects);
//                sharedPreferences.edit().putBoolean("from_datastore", true).apply();
//            }
//            query.setLimit(200)
//                    .fromLocalDatastore()
//                    .findInBackground(new FindCallback<Event>() {
//                        @Override
//                        public void done(List<Event> events, ParseException e) {
//                            if (e == null) {
//                                for (Event event : events) {
//                                    schedule.add(event);
//                                }
//                                ((ParseController) getApplicationContext()).setEventList(schedule);
//                                delay();
//                            } else {
//                                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SponsorActivity.this);
//                                dialogBuilder.setTitle(getString(R.string.string_connection_error_title))
//                                        .setMessage(R.string.string_connection_error_message)
//                                        .setPositiveButton(R.string.string_connection_try_again, new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                SponsorActivity.this.recreate();
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
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }

    private void delay() {
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SponsorActivity.this, HomeActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }

    public class RecyclerViewDisabler implements RecyclerView.OnItemTouchListener {

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            return true;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
