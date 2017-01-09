package com.uwics.uwidiscover.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.classes.adapters.ProjectCardAdapter;
import com.uwics.uwidiscover.classes.models.Location;
import com.uwics.uwidiscover.classes.models.Project;

import java.util.ArrayList;

/**
 * @author Howard Edwards
 */
public class DepartmentFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ProjectCardAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_department, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // TODO: Implement a Load More for every 15 items
        mAdapter = new ProjectCardAdapter(getActivity(), getDummyProjectData(),
                getActivity().getIntent().getStringExtra("faculty_op"));
        mRecyclerView.setAdapter(mAdapter);

        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadItems(mAdapter, mSwipeRefreshLayout);
            }
        });

        return rootView;
    }

    private void loadItems(ProjectCardAdapter mAdapter, SwipeRefreshLayout mSwipeRefreshLayout) {
        // load more
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public ArrayList<Project> getDummyProjectData() {
        ArrayList<Project> projectArrayList = new ArrayList<>();

        Project project;
        for (int i = 1; i <= 20; i++) {
            project = new Project("0" + i, "T" + i, getResources().getString(R.string.text_placeholder),
                    null, null, null, new Location("0" + i, "L" + i, null));
            projectArrayList.add(project);
        }
        return projectArrayList;
    }
}
