package com.nothingatall544.trackingstone.matchup;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.nothingatall544.trackingstone.R;
import com.nothingatall544.trackingstone.model.MatchUpRecord;

import java.util.List;

public class SwipeMatchupAdapter extends RecyclerSwipeAdapter<SwipeMatchupAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private SwipeLayout swipeLayout;
        private TextView mDeckName;
        private ImageView mHeroImage;
        private TextView mPercent;

        public ViewHolder(SwipeLayout view) {
            super(view);
            swipeLayout = view;
            mDeckName = (TextView) view.findViewById(R.id.title);
            mHeroImage = (ImageView) view.findViewById(R.id.hero_image);
            mPercent = (TextView) view.findViewById(R.id.win_rate);
        }
    }

    private List<MatchUpRecord> mRecords;

    public SwipeMatchupAdapter(List<MatchUpRecord> records) {
        this.mRecords = records;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SwipeLayout view = (SwipeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.swipe_view, parent, false);

        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, view.findViewById(R.id.bottom_lose));
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, view.findViewById(R.id.bottom_win));
        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {
            }

            @Override
            public void onOpen(SwipeLayout layout) {
                layout.close(true);
            }

            @Override
            public void onStartClose(SwipeLayout layout) {
                Log.d("TAG", "HERE onStartClose");
            }

            @Override
            public void onClose(SwipeLayout layout) {
                Log.d("TAG", "HERE onClose");
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                Log.d("TAG", "HERE onHandRelease");
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        MatchUpRecord record = mRecords.get(position);

        viewHolder.mDeckName.setText(record.getDeckName());
        final int percent = getWinRate(record);

        viewHolder.mPercent.setText(String.format("%d%%", percent));
        viewHolder.mHeroImage.setImageResource(record.getHeroImageRef());
    }

    @Override
    public int getItemCount() {
        return mRecords.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
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
