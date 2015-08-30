package com.nothingatall544.trackingstone;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nothingatall544.trackingstone.model.MatchUpRecord;
import com.nothingatall544.trackingstone.view.MatchUpAdapter;

import java.util.ArrayList;
import java.util.List;

import co.dift.ui.SwipeToAction;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MatchUpAdapter adapter;
    SwipeToAction swipeToAction;

    List<MatchUpRecord> records = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new MatchUpAdapter(this.records);
        recyclerView.setAdapter(adapter);

        swipeToAction = new SwipeToAction(recyclerView, new SwipeToAction.SwipeListener<MatchUpRecord>() {
            @Override
            public boolean swipeLeft(final MatchUpRecord itemData) {
                displaySnackbar(itemData.getDeckName() + " defeated", null, null);
                itemData.win();
                adapter.notifyItemChanged(records.indexOf(itemData));
                return true;
            }

            @Override
            public boolean swipeRight(MatchUpRecord itemData) {
                displaySnackbar(itemData.getDeckName() + " won :(", null, null);
                itemData.loss();
                adapter.notifyItemChanged(records.indexOf(itemData));
                return true;
            }

            @Override
            public void onClick(MatchUpRecord itemData) {
                displaySnackbar(itemData.getDeckName() + " clicked", null, null);
            }

            @Override
            public void onLongClick(MatchUpRecord itemData) {
                displaySnackbar(itemData.getDeckName() + " long clicked", null, null);
            }
        });


        populate();

        // use swipeLeft or swipeRight and the elem position to swipe by code
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToAction.swipeRight(2);
            }
        }, 3000);
    }

    private void populate() {
        this.records.add(new MatchUpRecord("Warrior Control", 4, 6));
        this.records.add(new MatchUpRecord("Warrior Control", 4, 6));
        this.records.add(new MatchUpRecord("Ramp Druid", 8, 2));
        this.records.add(new MatchUpRecord("Ramp Druid", 8, 2));
    }

    private void displaySnackbar(String text, String actionName, View.OnClickListener action) {
        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                .setAction(actionName, action);

        View v = snack.getView();
        v.setBackgroundColor(getResources().getColor(R.color.snack));
        ((TextView) v.findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
        ((TextView) v.findViewById(android.support.design.R.id.snackbar_action)).setTextColor(Color.BLACK);

        snack.show();
    }
}
