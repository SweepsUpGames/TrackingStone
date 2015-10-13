package com.nothingatall544.trackingstone.matchup;

import com.google.common.collect.ImmutableList;
import com.nothingatall544.trackingstone.R;

import java.util.List;

public class MatchupModel {
    public List<MatchUpRecord> getRecords() {
        return ImmutableList.<MatchUpRecord>builder()
                .add(new MatchUpRecord("Warrior Control", R.drawable.garrosh_hellscream, 4, 6))
                .add(new MatchUpRecord("Patron", R.drawable.garrosh_hellscream, 8, 2))
                .add(new MatchUpRecord("Ramp Druid", R.drawable.malfurion_stormrage, 6, 6))
                .add(new MatchUpRecord("Dagron Priest", R.drawable.anduin_wrynn, 2, 2))
                .add(new MatchUpRecord("Miracle Rogue", R.drawable.valeera_sanguinar, 0, 4))
                .build();
    }
}
