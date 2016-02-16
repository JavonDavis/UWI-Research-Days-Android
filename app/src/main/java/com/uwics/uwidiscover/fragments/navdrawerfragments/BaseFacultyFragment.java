package com.uwics.uwidiscover.fragments.navdrawerfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.activities.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shane on 2/16/16.
 */
public class BaseFacultyFragment extends Fragment {

    private static final String ARG_FACULTY = "arg_faculty";

    @Bind(R.id.map_display_container) RelativeLayout mapDisplayPanel;

    private String faculty;
    private boolean mapVisible;

    public BaseFacultyFragment() {
        // required
    }

    public static BaseFacultyFragment newInstance(String faculty) {
        BaseFacultyFragment fragment = new BaseFacultyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_FACULTY, faculty);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        faculty = getArguments().getString(ARG_FACULTY, HomeActivity.Tags.MEDSCI);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base_faculty, container, false);
        ButterKnife.bind(this, rootView); // automatic view bindings
        mapVisible = false;
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_base_faculty, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_map:
                if (!mapVisible) {
                    Animation slideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
                    mapDisplayPanel.startAnimation(slideUp);
                    mapDisplayPanel.setVisibility(View.VISIBLE);
                    mapVisible = true;
                } else {
                    Animation slideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
                    mapDisplayPanel.startAnimation(slideDown);
                    mapDisplayPanel.setVisibility(View.GONE);
                    mapVisible = false;
                }
                return true;
            case R.id.action_about:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
