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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.activities.DepartmentActivity;
import com.uwics.uwidiscover.classes.adapters.FacultyGridAdapter;

/**
 * Created by Howard on 1/23/2016.
 */
public class SciTechFacultyFragment extends Fragment {

    private final static String[] SCITECH_DEPT_OPTIONS = new String[]{"Dept. Of Chemistry",
            "Dept. Of Computing", "Dept. Of Geography and Geology", "Dept. Of Life Sciences",
            "Dept. Of Mathematics", "Dept. Of Physics", "Dept. Of Engineering"};

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
        View rootView = inflater.inflate(R.layout.fragment_faculty, container, false);

        mapDisplayPanel = (RelativeLayout) rootView.findViewById(R.id.map_display_container);
        mapVisible = false;

        GridView gridView = (GridView) rootView.findViewById(R.id.gridView);
        gridView.setSelector(R.color.white);
        gridView.setAdapter(new FacultyGridAdapter(getActivity(), SCITECH_DEPT_OPTIONS));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String action = null;
                switch (position) {
                    case 0:
                        action = "chem";
                        break;
                    case 1:
                        action = "comp";
                        break;
                    case 2:
                        action = "geo";
                        break;
                    case 3:
                        action = "sci";
                        break;
                    case 4:
                        action = "math";
                        break;
                    case 5:
                        action = "phys";
                        break;
                    case 6:
                        action = "engin";
                        break;
                }
                Intent intent = new Intent(getActivity(), DepartmentActivity.class);
                if (action != null) {
                    intent.setAction(action);
                    intent.putExtra("faculty_op", "sci");
                    startActivity(intent);
                }
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
