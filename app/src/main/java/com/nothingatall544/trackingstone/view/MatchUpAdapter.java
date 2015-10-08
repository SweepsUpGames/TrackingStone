package com.nothingatall544.trackingstone.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nothingatall544.trackingstone.R;
import com.nothingatall544.trackingstone.model.MatchUpRecord;

import java.util.List;

import co.dift.ui.SwipeToAction;

public class MatchUpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class ViewHolder extends SwipeToAction.ViewHolder {
        private TextView mDeckName;
        private ImageView mHeroImage;
        private TextView mPercent;
        public ViewHolder(View view) {
            super(view);
            mDeckName = (TextView) view.findViewById(R.id.title);
            mHeroImage = (ImageView) view.findViewById(R.id.hero_image);
            mPercent = (TextView) view.findViewById(R.id.win_rate);
        }

    }

    private List<MatchUpRecord> mRecords;

    public MatchUpAdapter(List<MatchUpRecord> records) {
        mRecords = records;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_up_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MatchUpRecord record = mRecords.get(position);
        ViewHolder vh = (ViewHolder) holder;

        vh.mDeckName.setText(record.getDeckName());
        final int percent = getWinRate(record);

        vh.mPercent.setText(String.format("%d%%", percent));

        //TODO get smaller images
        vh.mHeroImage.setImageResource(record.getHeroImageRef());
        vh.data = record;
    }

    @Override
    public int getItemCount() {
        return mRecords.size();
    }

    private int getWinRate(MatchUpRecord record) {
        final int gameWins = record.getWins();
        final int gamesPlayed = gameWins + record.getLosses();
        if (gamesPlayed < 1) {
            return 0;
        }

        final float winRate = (float) gameWins / (float) gamesPlayed;
        final float percent = 100 * winRate;

        return (int) percent;
    }
}