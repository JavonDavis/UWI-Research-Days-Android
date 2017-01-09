package com.uwics.uwidiscover.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.uwics.uwidiscover.R;

/**
 * Created by Howard on 1/24/2016.
 */
public class ProjectFragment extends Fragment implements View.OnClickListener {

    private ScrollView mScrollView;

    private RecyclerView mMediaRecyclerView;
    private RecyclerView mFeedbackRecyclerView;

    private TextView mSeeLocationTextView;
    private TextView mProjectDescriptionTextView;
    private TextView mBackToTopTextView;

    private Button mLeaveFeedbackButton;

    private String projectDescription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        projectDescription = getArguments().getString("projectDescription");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_project, container, false);

        mScrollView = (ScrollView) rootView.findViewById(R.id.scrollView);

        mMediaRecyclerView = (RecyclerView) rootView.findViewById(R.id.project_media_slider);
        mFeedbackRecyclerView = (RecyclerView) rootView.findViewById(R.id.project_feedback);
        setUpRecyclerViews();

        mSeeLocationTextView = (TextView) rootView.findViewById(R.id.see_location);
        mProjectDescriptionTextView = (TextView) rootView.findViewById(R.id.project_description);
        mBackToTopTextView = (TextView) rootView.findViewById(R.id.back_to_top);
        setUpTextViews();

        mLeaveFeedbackButton = (Button) rootView.findViewById(R.id.leave_feedback_button);
        setUpFeedbackButton();

        return rootView;
    }

    private void setUpRecyclerViews() {
        mMediaRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mMediaLayoutManager = new LinearLayoutManager(getActivity());
        mMediaLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mMediaRecyclerView.setLayoutManager(mMediaLayoutManager);
        // mMediaRecyclerView.setAdapter();

        LinearLayoutManager mFeedbackLayoutManager = new LinearLayoutManager(getActivity());
        mFeedbackLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFeedbackRecyclerView.setLayoutManager(mFeedbackLayoutManager);
        // mFeedbackRecyclerView.setAdapter();
    }

    private void setUpTextViews() {
        mSeeLocationTextView.setOnClickListener(this);
        mBackToTopTextView.setOnClickListener(this);
        mProjectDescriptionTextView.setText(projectDescription);
    }

    private void setUpFeedbackButton() {
        mLeaveFeedbackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.see_location:
                mScrollView.fullScroll(View.FOCUS_DOWN);
                break;
            case R.id.back_to_top:
                mScrollView.fullScroll(View.FOCUS_UP);
                break;
            case R.id.leave_feedback_button:
                new FeedbackDialogFragment().show(getFragmentManager(), "feedbackDialogProj");
                break;
        }
    }
}
