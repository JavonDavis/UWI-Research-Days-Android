package com.uwics.uwidiscover.classes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.uwics.uwidiscover.R;

/**
 * @author Howard Edwards
 */
public class SponsorsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private int[] sponsorImages;

    public SponsorsAdapter(Context context, int[] sponsorImages) {
        this.context = context;
        this.sponsorImages = sponsorImages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.sponsor_image_item, parent, false);
        return new SponsorHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SponsorHolder mHolder = (SponsorHolder) holder;
        final int sponsorImage = sponsorImages[position];

        mHolder.sponsorImageView.setImageResource(sponsorImage);
    }

    @Override
    public int getItemCount() {
        return sponsorImages.length;
    }

    private class SponsorHolder extends RecyclerView.ViewHolder {
        private ImageView sponsorImageView;

        public SponsorHolder(View itemView) {
            super(itemView);
            sponsorImageView = (ImageView) itemView.findViewById(R.id.sponsor_image);
        }
    }
}
