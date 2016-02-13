package com.uwics.uwidiscover.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.fragments.ProjectFragment;

/**
 * Created by Howard on 1/24/2016.
 */
public class ProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        String projectTheme = getIntent().getExtras().getString("projectTheme");
        if (projectTheme != null) {
            switch (projectTheme) {
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
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        Bundle args = new Bundle();
        String projectTitle = getIntent().getExtras().getString("projectTitle");
        String projectDescription = getIntent().getExtras().getString("projectDescription");

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        setTitle(projectTitle);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        args.putString("projectDescription", projectDescription);
        ProjectFragment projectFragment = new ProjectFragment();
        projectFragment.setArguments(args);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, projectFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_project, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_moderators:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
