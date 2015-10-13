package com.nothingatall544.trackingstone.matchup;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.nothingatall544.trackingstone.R;

import java.util.List;

/**
 * Maps matchups to their view
 * <p/>
 * puts the V in MVP?
 * <p/>
 * TODO: Pull out controlling logic into a presenter
 * TODO: define an interface for this
 */
public class SwipeMatchupAdapter extends RecyclerSwipeAdapter<SwipeMatchupAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private SwipeLayout mLayout;
        private TextView mDeckName;
        private ImageView mHeroImage;
        private TextView mPercent;
        private int mHeroImageRef = 0;
        private SwipeLayout.SwipeListener mListener;

        public ViewHolder(SwipeLayout view) {
            super(view);
            mLayout = view;
            mDeckName = (TextView) view.findViewById(R.id.title);
            mPercent = (TextView) view.findViewById(R.id.win_rate);
            mHeroImage = (ImageView) view.findViewById(R.id.hero_image);
        }

        public void setListener(SwipeLayout.SwipeListener listener) {
            if (mListener != null) {
                mLayout.removeSwipeListener(mListener);
            }
            mListener = listener;
            mLayout.addSwipeListener(mListener);
        }

        public void setTo(MatchUpRecord record) {
            final int percent = getWinRate(record);
            if (!mDeckName.getText().equals(record.getDeckName())) {
                mDeckName.setText(record.getDeckName());
            }
            if (!mPercent.getText().equals(String.format("%d%%", percent))) {
                mPercent.setText(String.format("%d%%", percent));
            }
            if (mHeroImageRef != record.getHeroImageRef()) {
                mHeroImage.setImageResource(record.getHeroImageRef());
                mHeroImageRef = record.getHeroImageRef();
            }
        }
    }

    private List<MatchUpRecord> mRecords;

    public SwipeMatchupAdapter() {
    }

    public void updateRecords(List<MatchUpRecord> records) {
        this.mRecords = records;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("TAG", "Create");
        SwipeLayout view = (SwipeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.swipe_view, parent, false);

        view.setShowMode(SwipeLayout.ShowMode.LayDown);
        view.addDrag(SwipeLayout.DragEdge.Left, view.findViewById(R.id.bottom_lose));
        view.addDrag(SwipeLayout.DragEdge.Right, view.findViewById(R.id.bottom_win));

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Log.d("TAG", "Bind " + position);
        MatchUpRecord record = mRecords.get(position);

        viewHolder.setListener(new MatchupSwipeListener(record, position));
        viewHolder.setTo(record);
    }

    @Override
    public int getItemCount() {
        return mRecords.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    private static int getWinRate(MatchUpRecord record) {
        final int gameWins = record.getWins();
        final int gamesPlayed = gameWins + record.getLosses();
        if (gamesPlayed < 1) {
            return 0;
        }

        final float winRate = (float) gameWins / (float) gamesPlayed;
        final float percent = 100 * winRate;

        return (int) percent;
    }

    private class MatchupSwipeListener implements SwipeLayout.SwipeListener {
        private final Handler mHandler;
        private final MatchUpRecord mRecord;
        private final int mPosition;

        public MatchupSwipeListener(MatchUpRecord record, int position) {
            this.mHandler = new Handler();
            this.mRecord = record;
            this.mPosition = position;
        }

        @Override
        public void onStartOpen(SwipeLayout layout) {

        }

        @Override
        public void onOpen(final SwipeLayout layout) {

        }

        @Override
        public void onStartClose(SwipeLayout layout) {

        }

        @Override
        public void onClose(SwipeLayout layout) {

        }

        @Override
        public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

        }

        @Override
        public void onHandRelease(final SwipeLayout layout, float xvel, float yvel) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (layout.getOpenStatus() != SwipeLayout.Status.Open) {
                        return;
                    }
                    if (layout.getCurrentBottomView().getId() == R.id.bottom_win) {
                        onWin();
                    } else if (layout.getCurrentBottomView().getId() == R.id.bottom_lose) {
                        onLoss();
                    }
                    layout.close(true);
                }
            }, 500);
        }

        private void onWin() {
            mRecord.win();
            notifyItemChanged(mPosition);
        }

        private void onLoss() {
            mRecord.loss();
            notifyItemChanged(mPosition);
        }
    }
}
