package com.uwics.uwidiscover.classes.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uwics.uwidiscover.R;
import com.uwics.uwidiscover.activities.SeminarActivity;
import com.uwics.uwidiscover.classes.models.Seminar;

import java.util.ArrayList;

/**
 * @author Howard Edwards
 */
public class SeminarCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<Seminar> seminarArrayList;

    public SeminarCardAdapter(Context context, ArrayList<Seminar> seminarArrayList) {
        this.context = context;
        this.seminarArrayList = seminarArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.fragment_seminar_item, parent, false);
        return new SeminarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SeminarViewHolder mHolder = (SeminarViewHolder) holder;
        final Seminar seminar = seminarArrayList.get(position);
        final String seminarTimeDur = seminar.getStartTime() + " - " + seminar.getEndTime();

        mHolder.seminarTopic.setText(seminar.getTopic());
        mHolder.seminarSpeaker.setText(seminar.getSpeaker().getName());
        mHolder.seminarTime.setText(seminarTimeDur);
        mHolder.seminarDescription.setText(seminar.getDescription());
        mHolder.seminarCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle seminarInfo = new Bundle();
                seminarInfo.putString("seminarTopic", seminar.getTopic());
                seminarInfo.putString("seminarSpeaker", seminar.getSpeaker().getName());
                seminarInfo.putString("seminarTime", seminarTimeDur);
                seminarInfo.putString("seminarDescription", seminar.getDescription());
                context.startActivity(new Intent(context, SeminarActivity.class).putExtras(seminarInfo));
            }
        });
    }

    @Override
    public int getItemCount() {
        return seminarArrayList.size();
    }

    private class SeminarViewHolder extends RecyclerView.ViewHolder {

        private CardView seminarCardView;
        private TextView seminarTopic;
        private TextView seminarSpeaker;
        private TextView seminarTime;
        private TextView seminarDescription;

        public SeminarViewHolder(View itemView) {
            super(itemView);
            seminarCardView = (CardView) itemView.findViewById(R.id.cardView);
            seminarTopic = (TextView) itemView.findViewById(R.id.seminar_topic);
            seminarSpeaker = (TextView) itemView.findViewById(R.id.seminar_speaker);
            seminarTime = (TextView) itemView.findViewById(R.id.seminar_time);
            seminarDescription = (TextView) itemView.findViewById(R.id.seminar_description);
        }
    }
}
