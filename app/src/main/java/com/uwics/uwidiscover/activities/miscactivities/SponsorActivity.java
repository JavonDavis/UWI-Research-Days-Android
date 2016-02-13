package com.uwics.uwidiscover.activities.miscactivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.activities.HomeActivity;
import com.uwics.uwidiscover.classes.models.Event;
import com.uwics.uwidiscover.utils.ParseController;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Howard Edwards
 */

// TODO: Perform loading of schedule during showing of this page:
public class SponsorActivity extends AppCompatActivity {

    private SponsorActivity activity = this;
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
        ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
        try {
            List<Event> objects = query.find();
            Event.pinAllInBackground(objects);
            query.setLimit(200).findInBackground(new FindCallback<Event>() {
                @Override
                public void done(List<Event> events, ParseException e) {
                    if (e == null) {
                        for (Event event : events) {
                            schedule.add(event);
                        }
                        ((ParseController) getApplicationContext()).setEventList(schedule);
                        delay();
                    } else {
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
                        dialogBuilder.setTitle(getString(R.string.string_connection_error_title));
                        dialogBuilder.setMessage(R.string.string_connection_error_message);
                        dialogBuilder.setNeutralButton(R.string.string_connection_try_again,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        activity.recreate();
                                    }
                                });
                        dialogBuilder.show();
                    }
                }
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void delay() {
        int secondsDelayed = 2;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SponsorActivity.this, HomeActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);
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
