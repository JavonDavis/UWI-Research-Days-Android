package com.uwics.uwidiscover.activities;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.activities.miscactivities.MyPreferencesActivity;
import com.uwics.uwidiscover.activities.miscactivities.SearchableActivity;
import com.uwics.uwidiscover.classes.models.Faculty;
import com.uwics.uwidiscover.fragments.navdrawerfragments.HumEdFacultyFragment;
import com.uwics.uwidiscover.fragments.navdrawerfragments.LawFacultyFragment;
import com.uwics.uwidiscover.fragments.navdrawerfragments.MedSciFacultyFragment;
import com.uwics.uwidiscover.fragments.navdrawerfragments.ScheduleFragment;
import com.uwics.uwidiscover.fragments.navdrawerfragments.SciTechFacultyFragment;
import com.uwics.uwidiscover.fragments.navdrawerfragments.SeminarFragment;
import com.uwics.uwidiscover.fragments.navdrawerfragments.SoSciFacultyFragment;

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

        String deviceId = (String) ParseInstallation.getCurrentInstallation().get("unique_id");
        Toast.makeText(this, "token: " + deviceId, Toast.LENGTH_LONG).show();

//        initialParseRequests();
    }

    private void initialParseRequests() {
        ParseQuery<Faculty> localQuery = ParseQuery.getQuery(Faculty.class);

        localQuery.fromLocalDatastore();
        localQuery.getFirstInBackground(new GetCallback<Faculty>() {
            @Override
            public void done(Faculty faculty, ParseException e) {
                if (e == null) {
                    // no error
                    if (faculty == null) {
                        ParseQuery<Faculty> query = ParseQuery.getQuery(Faculty.class);

                        query.findInBackground(new FindCallback<Faculty>() {
                            @Override
                            public void done(List<Faculty> faculties, ParseException e) {
                                if (e == null) {
                                    Toast.makeText(HomeActivity.this, faculties.get(0).getName(), Toast.LENGTH_SHORT).show();
                                    ParseObject.pinAllInBackground(faculties);
                                }
                            }
                        });
                    }
                } else {
                    Log.wtf(HomeActivity.class.getName(), "Error: " + e.getMessage());
                }
            }
        });
        // pin all the faculties in the background for the initial loading of the app
        // so we have a reference to them
    }

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
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_schedule:
                setTitle(getString(R.string.uwi_research_day));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new ScheduleFragment())
                        .commit();
                break;
            case R.id.nav_fac_humed:
                setTitle(getString(R.string.faculty_humanities));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new HumEdFacultyFragment(), Tags.HUMANITIES)
                        .commit();
                break;
            case R.id.nav_fac_law:
                setTitle(getString(R.string.faculty_law));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new LawFacultyFragment(), Tags.LAW)
                        .commit();
                break;
            case R.id.nav_fac_medsci:
                setTitle(getString(R.string.faculty_medical_sciences));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new MedSciFacultyFragment(), Tags.MEDSCI)
                        .commit();
                break;
            case R.id.nav_fac_scitech:
                setTitle(getString(R.string.faculty_science_technology));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new SciTechFacultyFragment(), Tags.SCITECH)
                        .commit();
                break;
            case R.id.nav_fac_sosci:
                setTitle(getString(R.string.faculty_social_sciences));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new SoSciFacultyFragment(), Tags.SOSCI)
                        .commit();
                break;
            case R.id.nav_seminars:
                setTitle(getString(R.string.uwi_research_day));
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new SeminarFragment())
                        .commit();
                break;
            case R.id.nav_tours:
                break;
            case R.id.nav_live:
                intent = new Intent(this, LiveActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                intent = new Intent(this, MyPreferencesActivity.class);
                startActivity(intent);
                break;
//            case R.id.nav_help:
//                intent = new Intent(this, HelpAndFeedbackActivity.class);
//                startActivity(intent);
//                break;
            case R.id.nav_sponsors:
                startActivity(new Intent(this, MeetOurSponsorsActivity.class));
                break;
            case R.id.nav_about_uwi:
                intent = new Intent(this, AboutUWIActivity.class);
                startActivity(intent);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
        String HUMANITIES = "humanities";
        String LAW = "law";
        String MEDSCI = "medsci";
        String SOSCI = "soSci";
        String SCITECH = "sciTech";
    }
}
