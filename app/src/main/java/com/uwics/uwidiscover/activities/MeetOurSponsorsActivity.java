package com.uwics.uwidiscover.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.uwics.uwidiscover.R;

import butterknife.Bind;

/**
 * @author Howard Edwards
 */
public class MeetOurSponsorsActivity extends AppCompatActivity {

    @Bind(R.id.vision)
    ImageView visionImageView;
    @Bind(R.id.cfp)
    ImageView cfpImageView;
    @Bind(R.id.jipo)
    ImageView jipoImageView;
    @Bind(R.id.scientific)
    ImageView scientificImageView;
    @Bind(R.id.cardi)
    ImageView cardiImageView;
    @Bind(R.id.hawkeye)
    ImageView hawkeyeImageView;
    @Bind(R.id.arrc)
    ImageView arrcImageView;
    @Bind(R.id.epic)
    ImageView epicImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_meet_our_sponsors);

//        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getActionBar() != null)
            getActionBar().setDisplayHomeAsUpEnabled(true);

        new AlertDialog.Builder(this).setMessage("Click a banner visit the page of the respective Organization/Company/Group")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openSponsorLinks(View view) {
        if (view.getTag() != null) {
            String url = (String) view.getTag();
            Intent intent = new Intent();

            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }
}
