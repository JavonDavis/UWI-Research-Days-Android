package com.uwics.uwidiscover.fragments.navdrawerfragments;

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
import com.uwics.uwidiscover.classes.adapters.SeminarCardAdapter;
import com.uwics.uwidiscover.classes.models.Representative;
import com.uwics.uwidiscover.classes.models.Seminar;

import java.util.ArrayList;

/**
 * @author Howard Edwards
 */
public class SeminarFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seminar, container, false);
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        final SeminarCardAdapter mAdapter = new SeminarCardAdapter(getActivity(), getDummySeminarData());
        mRecyclerView.setAdapter(mAdapter);

        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems(mAdapter, mSwipeRefreshLayout);
            }
        });

        return rootView;
    }

    private void refreshItems(SeminarCardAdapter mAdapter, SwipeRefreshLayout mSwipeRefreshLayout) {
        // Parse request
        mSwipeRefreshLayout.setRefreshing(false);
    }


    private ArrayList<Seminar> getDummySeminarData() {
        ArrayList<Seminar> seminarArrayList = new ArrayList<>();

        Seminar seminar;
        for (int i = 1 ; i <= 20 ; i ++) {
            seminar = new Seminar("0", "The core is a unrelated proton. (" + i + ")", "1:00pm", "1:30pm",
                    "The wind marks with fight, sail the fortress. Yo-ho-ho there's nothing like the " +
                            "scurvy punishment screaming on the biscuit eater. Strength, passion, and yellow fever. " +
                            "Yuck! Pieces o' hunger are forever cold. Amnesty is a rainy pants. " +
                            "Reef of a wet strength, taste the punishment!",
                    new Representative("0", "UWI Discover Team", "CEO", "lol@lol.com", "fax", null), null, null);
            seminarArrayList.add(seminar);
        }
        return seminarArrayList;
    }
}
