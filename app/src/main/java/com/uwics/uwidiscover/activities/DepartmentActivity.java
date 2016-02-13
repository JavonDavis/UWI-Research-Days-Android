package com.uwics.uwidiscover.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.fragments.DepartmentFragment;

/**
 * Created by Howard on 1/22/2016.
 */
public class DepartmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("FACULTYOP", getIntent().getStringExtra("faculty_op"));
        switch (getIntent().getStringExtra("faculty_op")) {
            case "hum":
                setTheme(R.style.AppTheme_HumEd);
                break;
            case "law":
                setTheme(R.style.AppTheme_Law);
                break;
            case "med":
                setTheme(R.style.AppTheme_MedSci);
                break;
            case "sci":
                setTheme(R.style.AppTheme_SciTech);
                break;
            case "soSci":
                setTheme(R.style.AppTheme_SoSci);
                break;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        switch (getIntent().getAction()) {
            case "media":
                break;
            case "hist":
                break;
            case "caribstudies":
                break;
            case "lang":
                break;
            case "lib":
                break;
            case "lit":
                break;
            case "modern":
                break;
            case "edu":
                break;
            case "chem":
                break;
            case "comp":
                break;
            case "geo":
                break;
            case "sci":
                break;
            case "math":
                break;
            case "phys":
                break;
            case "engin":
                break;
            case "econ":
                break;
            case "gov":
                break;
            case "sopsywork":
                break;
            case "law":
                break;
            case "med":
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new DepartmentFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_department, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_about:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
