package com.uwics.uwidiscover.fragments.navdrawerfragments;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.RelativeLayout;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.activities.DepartmentActivity;

/**
 * Created by Howard on 1/23/2016.
 */
public class MedSciFacultyFragment extends Fragment {

    private RelativeLayout mapDisplayPanel;
    private boolean mapVisible;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_faculty_no_dept, container, false);

        mapDisplayPanel = (RelativeLayout) rootView.findViewById(R.id.map_display_container);
        mapVisible = false;

        Button viewProjects = (Button) rootView.findViewById(R.id.view_projects_button);
        viewProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DepartmentActivity.class);
                intent.setAction("med");
                intent.putExtra("faculty_op", "med");
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_about_faculty, menu);
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
