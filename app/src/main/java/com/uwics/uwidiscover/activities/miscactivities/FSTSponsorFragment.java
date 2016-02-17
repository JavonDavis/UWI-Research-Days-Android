package com.uwics.uwidiscover.activities.miscactivities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.activities.HomeActivity;
import com.uwics.uwidiscover.fragments.EventListFragment;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 17/02/16.
 */
public class FSTSponsorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sponsor_activity, container, false);
        DilatingDotsProgressBar mProgressBar = (DilatingDotsProgressBar) rootView.findViewById(R.id.progress_bar);
        mProgressBar.showNow();
        return rootView;
    }
}
