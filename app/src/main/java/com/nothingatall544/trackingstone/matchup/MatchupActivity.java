package com.nothingatall544.trackingstone.matchup;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nothingatall544.trackingstone.R;
import com.nothingatall544.trackingstone.matchup.SwipeMatchupAdapter;
import com.nothingatall544.trackingstone.matchup.MatchUpRecord;

import java.util.ArrayList;
import java.util.List;

public class MatchupActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeMatchupAdapter adapter;

    MatchupModel mModel = new MatchupModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new SwipeMatchupAdapter(this.mModel.getRecords());
        recyclerView.getItemAnimator().setSupportsChangeAnimations(false);
        recyclerView.setAdapter(adapter);
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
