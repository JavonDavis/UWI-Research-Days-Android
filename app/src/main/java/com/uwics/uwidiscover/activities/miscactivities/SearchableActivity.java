package com.uwics.uwidiscover.activities.miscactivities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.classes.adapters.EventAdapter;
import com.uwics.uwidiscover.classes.models.Event;
import com.uwics.uwidiscover.utils.ParseController;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchableActivity extends AppCompatActivity {

    @Bind(R.id.container)
    CoordinatorLayout mCoordinatorLayout;
    @Bind(R.id.search_results)
    RecyclerView mRecyclerView;
    @Bind(R.id.tv_error)
    TextView mErrorView;

    private EventAdapter mEventAdapter;
    private List<Event> eventList;

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
//        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
//
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//
//        ComponentName componentName = new ComponentName(this, SearchableActivity.class);
//
//        searchView.setSearchableInfo(searchManager
//                .getSearchableInfo(componentName));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_filter:
//                Snackbar.make(mCoordinatorLayout, "Filter", Snackbar.LENGTH_SHORT).show();
//                return true;
//            case R.id.action_search:
//                return true;
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
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String searchString = intent.getStringExtra(SearchManager.QUERY);
            setTitle(searchString);
            createQueuryRequest(searchString);
        }
    }

    private void createQueuryRequest(String searchString) {
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
    }
}