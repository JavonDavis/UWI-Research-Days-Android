package com.uwics.uwidiscover.fragments.navdrawerfragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.activities.miscactivities.SponsorActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScienceParkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScienceParkFragment extends Fragment {

    public ScienceParkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ScienceParkFragment.
     */
    public static ScienceParkFragment newInstance() {
        return new ScienceParkFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_science_park, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.options_list);

        List<String> items = new ArrayList<>();

        items.add("Ads");
        items.add("Banners");
        items.add("Meet Our Sponsors");

        // recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ItemAdapter(getActivity(), items));
        recyclerView.setHasFixedSize(true);

        return rootView;
    }

    public interface OnParkItemClickListener {
        void onItemClicked(int position);
    }

    public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder>
            implements OnParkItemClickListener {

        private List<String> mItems;
        private Context mContext;

        public ItemAdapter(Context context, List<String> items) {
            mContext = context;
            mItems = items;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_park, parent, false);
            return new ItemHolder(rootView, this);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            holder.name.setText(mItems.get(position));

        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        @Override
        public void onItemClicked(int position) {
            startActivity(new Intent(mContext, SponsorActivity.class));
        }

        public class ItemHolder extends RecyclerView.ViewHolder {

            CardView container;
            TextView name;

            private OnParkItemClickListener mListener;

            public ItemHolder(View itemView, OnParkItemClickListener listener) {
                super(itemView);
                mListener = listener;
                name = (TextView) itemView.findViewById(R.id.name);
                container = (CardView) itemView.findViewById(R.id.container);
                container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onItemClicked(getLayoutPosition());
                    }
                });
            }
        }
    }
}