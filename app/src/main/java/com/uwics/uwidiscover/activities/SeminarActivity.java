package com.uwics.uwidiscover.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.fragments.AboutSpeakerDialogFragment;
import com.uwics.uwidiscover.fragments.FeedbackDialogFragment;

/**
 * Created by Howard on 1/22/2016.
 */
public class SeminarActivity extends AppCompatActivity {

    private TextView mSeminarTopicTextView;
    private TextView mSeminarSpeakerTextView;
    private TextView mSeminarTimeTextView;
    private TextView mSeminarDescriptionTextView;
    private RecyclerView mFeedbackRecyclerView;
    private Button mLeaveFeedbackButton;
    private String seminarTopic;
    private String seminarSpeaker;
    private String seminarTime;
    private String seminarDescription;
    private String seminarSpeakerPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar);
        seminarTopic = getIntent().getExtras().getString("seminarTopic");
        seminarSpeaker = getIntent().getExtras().getString("seminarSpeaker");
        seminarTime = getIntent().getExtras().getString("seminarTime");
        seminarDescription = getIntent().getExtras().getString("seminarDescription");

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFeedbackRecyclerView = (RecyclerView) findViewById(R.id.seminar_feedback);
        setUpRecyclerView();

        mSeminarTopicTextView = (TextView) findViewById(R.id.seminar_topic);
        mSeminarSpeakerTextView = (TextView) findViewById(R.id.seminar_speaker);
        mSeminarTimeTextView = (TextView) findViewById(R.id.seminar_time);
        mSeminarDescriptionTextView = (TextView) findViewById(R.id.seminar_description);
        setUpSeminarTextViews(seminarTopic, seminarSpeaker, seminarTime, seminarDescription);

        mLeaveFeedbackButton = (Button) findViewById(R.id.leave_feedback_button);
        setUpFeedbackButton();
    }

    private void setUpSeminarTextViews(String seminarTopic, String seminarSpeaker,
                                       String seminarTime, String seminarDescription) {
        mSeminarTopicTextView.setText(seminarTopic);
        mSeminarSpeakerTextView.setText(seminarSpeaker);
        mSeminarTimeTextView.setText(seminarTime);
        mSeminarDescriptionTextView.setText(seminarDescription);
    }

    private void setUpRecyclerView() {
        LinearLayoutManager mFeedbackLayoutManager = new LinearLayoutManager(this);
        mFeedbackLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFeedbackRecyclerView.setLayoutManager(mFeedbackLayoutManager);
        // mFeedbackRecyclerView.setAdapter();
    }

    private void setUpFeedbackButton() {
        mLeaveFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FeedbackDialogFragment().show(getSupportFragmentManager(), "feedbackDialogSem");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_seminar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_speaker:
                AboutSpeakerDialogFragment aboutSpeakerDialogFragment = new AboutSpeakerDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("seminarSpeaker", seminarSpeaker);
                bundle.putString("seminarSpeakerPosition", seminarSpeakerPosition);

                aboutSpeakerDialogFragment.setArguments(bundle);
                aboutSpeakerDialogFragment.show(getSupportFragmentManager(), "aboutSpeakerDialogFragment");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
