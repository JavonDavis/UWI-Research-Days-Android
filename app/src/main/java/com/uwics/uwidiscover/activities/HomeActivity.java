package com.uwics.uwidiscover.activities;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.activities.miscactivities.FSTSponsorFragment;
import com.uwics.uwidiscover.activities.miscactivities.MyPreferencesActivity;
import com.uwics.uwidiscover.activities.miscactivities.SearchableActivity;
import com.uwics.uwidiscover.classes.models.Faculty;
import com.uwics.uwidiscover.fragments.EventListFragment;
import com.uwics.uwidiscover.fragments.navdrawerfragments.ScheduleFragment;

import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        setupNavDrawer();
        setTitle(getResources().getString(R.string.app_name));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ScheduleFragment())
                    .commit();
        } else {
            setTitle(savedInstanceState.getCharSequence("title"));
        }
//        initialParseRequests();
    }

//    private void initialParseRequests() {
//        ParseQuery<Faculty> localQuery = ParseQuery.getQuery(Faculty.class);
//
//        localQuery.fromLocalDatastore();
//        localQuery.getFirstInBackground(new GetCallback<Faculty>() {
//            @Override
//            public void done(Faculty faculty, ParseException e) {
//                if (e == null) {
//                    // no error
//                    if (faculty == null) {
//                        ParseQuery<Faculty> query = ParseQuery.getQuery(Faculty.class);
//
//                        query.findInBackground(new FindCallback<Faculty>() {
//                            @Override
//                            public void done(List<Faculty> faculties, ParseException e) {
//                                if (e == null) {
//                                    Toast.makeText(HomeActivity.this, faculties.get(0).getName(), Toast.LENGTH_SHORT).show();
//                                    ParseObject.pinAllInBackground(faculties);
//                                }
//                            }
//                        });
//                    }
//                } else {
//                    Log.wtf(HomeActivity.class.getName(), "Error: " + e.getMessage());
//                }
//            }
//        });
//        // pin all the faculties in the background for the initial loading of the app
//        // so we have a reference to them
//    }

    private void setupNavDrawer() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        disableNavigationViewScrollbars(navigationView);

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (isScheduleShowing()) {
            super.onBackPressed();
        } else {
            setTitle(getString(R.string.uwi_research_day));
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new ScheduleFragment())
                    .commit();
        }
    }

    private boolean isScheduleShowing() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        return fragment instanceof ScheduleFragment && fragment.isVisible();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
//        searchView.setOnQueryTextListener(new SearchQueryListener());

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        ComponentName componentName = new ComponentName(this, SearchableActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        return super.onCreateOptionsMenu(menu);
    }

//    private class SearchQueryListener implements SearchView.OnQueryTextListener {
//
//        @Override
//        public boolean onQueryTextSubmit(String query) {
//            return false;
//        }
//
//        @Override
//        public boolean onQueryTextChange(String newText) {
//            return true;
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Bundle bundle;
        EventListFragment eventListFragment;
        switch (item.getItemId()) {
            /*case R.id.nav_park:
                launchFragment(getString(R.string.science_experience_park), ScienceParkFragment.newInstance(), Tags.PARK);
                break;*/
            case R.id.nav_schedule:
                launchFragment(getString(R.string.uwi_research_day), new ScheduleFragment());
                break;
            case R.id.nav_fac_humed:
                bundle = new Bundle();
                bundle.putString("faculty", Tags.HUMANITIES);
                bundle.putString("wDay",getString(R.string.faculty_humanities));
                eventListFragment = new EventListFragment();
                eventListFragment.setArguments(bundle);
                launchFragment(getString(R.string.faculty_humanities), eventListFragment);
                break;
            case R.id.nav_fac_law:
                bundle = new Bundle();
                bundle.putString("faculty", Tags.LAW);
                bundle.putString("wDay",getString(R.string.faculty_law));
                eventListFragment = new EventListFragment();
                eventListFragment.setArguments(bundle);
                launchFragment(getString(R.string.faculty_law), eventListFragment);
                break;
            case R.id.nav_fac_medsci:
                bundle = new Bundle();
                bundle.putString("faculty", Tags.MEDICAL_SCIENCES);
                bundle.putString("wDay",getString(R.string.faculty_medical_sciences));
                eventListFragment = new EventListFragment();
                eventListFragment.setArguments(bundle);
                launchFragment(getString(R.string.faculty_medical_sciences), eventListFragment);
                break;
            case R.id.nav_fac_scitech:
                FSTSponsorFragment fstSponsorFragment = new FSTSponsorFragment();
                launchFragment(getString(R.string.faculty_science_technology), fstSponsorFragment);
                eventListFragment = new EventListFragment();
                delay(eventListFragment);
                break;
            case R.id.nav_fac_sosci:
                bundle = new Bundle();
                bundle.putString("faculty", Tags.SOCIAL_SCIENCES);
                bundle.putString("wDay",getString(R.string.faculty_social_sciences));
                eventListFragment = new EventListFragment();
                eventListFragment.setArguments(bundle);
                launchFragment(getString(R.string.faculty_social_sciences), eventListFragment);
                break;
            case R.id.nav_fac_igds:
                bundle = new Bundle();
                bundle.putString("faculty", Tags.INSTITUTE_FOR_GENDER_STUDIES);
                bundle.putString("wDay",getString(R.string.faculty_institute_for_gender_and_development_studies));
                eventListFragment = new EventListFragment();
                eventListFragment.setArguments(bundle);
                launchFragment(getString(R.string.faculty_institute_for_gender_and_development_studies), eventListFragment);
                break;
            case R.id.nav_fac_mss:
                bundle = new Bundle();
                bundle.putString("faculty", Tags.MONA_SOCIAL_SERVICES);
                bundle.putString("wDay",getString(R.string.faculty_mona_social_services));
                eventListFragment = new EventListFragment();
                eventListFragment.setArguments(bundle);
                launchFragment(getString(R.string.faculty_mona_social_services), eventListFragment);
                break;
            case R.id.nav_fac_ogsr:
                bundle = new Bundle();
                bundle.putString("faculty", Tags.OFFICE_GRADUATE_STUDIES);
                bundle.putString("wDay",getString(R.string.faculty_office_of_graduate_studies_and_research));
                eventListFragment = new EventListFragment();
                eventListFragment.setArguments(bundle);
                launchFragment(getString(R.string.faculty_office_of_graduate_studies_and_research), eventListFragment);
                break;
            case R.id.nav_seminars:
                bundle = new Bundle();
                bundle.putString("filter", Filters.SEMINAR);
                bundle.putString("wDay",getString(R.string.string_seminars));
                eventListFragment = new EventListFragment();
                eventListFragment.setArguments(bundle);
                launchFragment(getString(R.string.string_seminars), eventListFragment);
                break;
            case R.id.nav_tours:
                bundle = new Bundle();
                bundle.putString("filter", Filters.TOUR);
                bundle.putString("wDay",getString(R.string.string_tours));
                eventListFragment = new EventListFragment();
                eventListFragment.setArguments(bundle);
                launchFragment(getString(R.string.string_tours), eventListFragment);
                break;
            case R.id.nav_live:
                startActivity(new Intent(this, LiveActivity.class));
                break;
            case R.id.nav_settings:
                startActivity(new Intent(this, MyPreferencesActivity.class));
                break;
//            case R.id.nav_help:
//                intent = new Intent(this, HelpAndFeedbackActivity.class);
//                startActivity(intent);
//                break;
            case R.id.nav_sponsors:
                startActivity(new Intent(this, MeetOurSponsorsActivity.class));
                break;
            case R.id.nav_about_uwi:
                startActivity(new Intent(this, AboutUWIActivity.class));
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void launchFragment(String title, Fragment fragment) {
        this.launchFragment(title, fragment, null);
    }

    private void launchFragment(String title, Fragment fragment, String tag) {
        setTitle(title);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("title", mTitle);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        mTitle = title;
    }

    public void setActionBarTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    public interface Tags {
        String PARK = "science_experience_park";
        String HUMANITIES = "fhe";
        String LAW = "fol";
        String MEDICAL_SCIENCES = "fms";
        String SOCIAL_SCIENCES = "fss";
        String SCIENCE_TECHNOLOGY = "fst";
        String MONA_SOCIAL_SERVICES = "mss";
        String INSTITUTE_FOR_GENDER_STUDIES = "igds";
        String OFFICE_GRADUATE_STUDIES = "ogsr";
    }

    public interface Filters
    {
        String SEMINAR = "seminar";
        String TOUR = "tour";
    }

    private void delay(final EventListFragment eventListFragment) {
        int secondsDelayed = 2;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putString("faculty", Tags.SCIENCE_TECHNOLOGY);
                bundle.putString("wDay",getString(R.string.faculty_science_technology));
                eventListFragment.setArguments(bundle);

                launchFragment(getString(R.string.faculty_science_technology), eventListFragment);
            }
        }, secondsDelayed * 1000);
    }
}
