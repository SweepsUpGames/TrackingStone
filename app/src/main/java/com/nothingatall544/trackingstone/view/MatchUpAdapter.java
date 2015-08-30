package com.nothingatall544.trackingstone.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nothingatall544.trackingstone.R;
import com.nothingatall544.trackingstone.model.MatchUpRecord;

import java.util.List;

import co.dift.ui.SwipeToAction;

/**
 * Created by derp on 8/29/2015.
 */
public class MatchUpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MatchUpRecord> mRecords;

    public static class ViewHolder extends SwipeToAction.ViewHolder {
        private TextView mDeckName;
        private TextView mPercent;

        public ViewHolder(View view) {
            super(view);
            mDeckName = (TextView) view.findViewById(R.id.title);
            mPercent = (TextView) view.findViewById(R.id.win_rate);
        }
    }

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
        final int percent = (record.getWins() + record.getLoses()) > 0
                ? (int) (100 * ((double) record.getWins() / (record.getWins() + record.getLoses())))
                : 0;
        vh.mPercent.setText(String.format("%d%%", percent));
        vh.data = record;
    }

    @Override
    public int getItemCount() {
        return mRecords.size();
    }
}