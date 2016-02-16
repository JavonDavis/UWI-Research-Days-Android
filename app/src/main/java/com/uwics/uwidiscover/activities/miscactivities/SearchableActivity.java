package com.uwics.uwidiscover.activities.miscactivities;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.classes.adapters.EventAdapter;
import com.uwics.uwidiscover.classes.models.Event;
import com.uwics.uwidiscover.fragments.FilterDialogFragment;
import com.uwics.uwidiscover.utils.ParseController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchableActivity extends AppCompatActivity
        implements FilterDialogFragment.FilterDialogListener {

    public static final String TAG = "SearchableActivity";

    @Bind(R.id.container) CoordinatorLayout mCoordinatorLayout;
    @Bind(R.id.search_results) RecyclerView mRecyclerView;
    @Bind(R.id.tv_error) TextView mErrorView;

    private EventAdapter mEventAdapter;
    private List<Event> eventList;

    /* On API 17, the handleIntent() method was being fired twice, causing duplicate search results,
     so just a boolean to prevent that */
    private boolean queryComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        eventList = ((ParseController) getApplicationContext()).getEventList();
        ButterKnife.bind(this);
        setupViews();
        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        setupSearView(menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void setupSearView(Menu menu) {
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
//        searchView.setOnQueryTextListener(new SearchQueryListener());

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        ComponentName componentName = new ComponentName(this, SearchableActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                showFilterDialog();
                return true;
            case R.id.action_search:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void setupViews() {
        mEventAdapter = new EventAdapter(this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mEventAdapter);
    }

    private void handleIntent(Intent intent) {
        if (!queryComplete) {
            if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                String searchString = intent.getStringExtra(SearchManager.QUERY);
                setTitle(searchString);
                createQueryRequest(searchString);
            }
        }
    }

    private void createQueryRequest(String searchString) {
        searchString = searchString.toLowerCase();
        List<Event> resultEventList = new ArrayList<>();
        for (Event e : eventList) {
            if (e.getDetails().toLowerCase().contains(searchString)
                    || e.getVenue().toLowerCase().contains(searchString)
                    || e.getType().toLowerCase().contains(searchString)) {
                resultEventList.add(e);
                mEventAdapter.addEvent(e);
            }
        }
        if (resultEventList.size() == 0)
            mErrorView.setVisibility(View.VISIBLE);
        queryComplete = true;
    }

    public void showFilterDialog() {
        FilterDialogFragment.newInstance()
                .show(getSupportFragmentManager(), FilterDialogFragment.TAG);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, Map<String, String> filterValues) {
        dialog.dismiss();

        List<Event> filteredEvents = new ArrayList<>();

        for (Event event: eventList) {
            if (!filterValues.containsKey("wednesday") && event.getDate().equals("18/2/2016")) filteredEvents.add(event);
            else if (!filterValues.containsKey("thursday") && event.getDate().equals("19/2/2016")) filteredEvents.add(event);
            else if (!filterValues.containsKey("friday") && event.getDate().equals("20/2/2016")) filteredEvents.add(event);
        }

        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.US);

        if (filterValues.containsKey("start_time")) {
            try {
                Date filterStartTime = format.parse(filterValues.get("start_time"));
                Date eventStartTime;

                for (Event event: filteredEvents) {
                    eventStartTime = format.parse(event.getStartTime());

                    if (null == eventStartTime)
                        continue;

                    if (eventStartTime.before(filterStartTime))
                        filteredEvents.remove(event);
                }

            } catch (ParseException e) {
                Log.d(TAG, e.getMessage());
                Snackbar.make(mCoordinatorLayout, "Start or end time invalid", Snackbar.LENGTH_LONG)
                        .show();
            }
        }

        if (filterValues.containsKey("end_time")) {
            try {
                Date filterEndTime = format.parse(filterValues.get("end_time"));
                Date eventEndTime;

                for (Event event: filteredEvents) {
                    eventEndTime = format.parse(event.getEndTime());

                    if (null == eventEndTime)
                        continue;

                    if (eventEndTime.before(filterEndTime))
                        filteredEvents.remove(event);
                }

            } catch (ParseException e) {
                Log.d(TAG, e.getMessage());
                Snackbar.make(mCoordinatorLayout, "Start or end time invalid", Snackbar.LENGTH_LONG)
                        .show();
            }
        }

        mEventAdapter.setEvents(filteredEvents);

        if (filteredEvents.size() <= 0) {
            mErrorView.setText(R.string.no_results_found);
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
    }
}