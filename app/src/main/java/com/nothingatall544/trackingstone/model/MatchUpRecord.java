package com.nothingatall544.trackingstone.model;

/**
 * Created by derp on 8/29/2015.
 */
public class MatchUpRecord {
    private String mDeckName;
    private int mWins;
    private int mLoses;

    public MatchUpRecord(String deckName, int wins, int loses) {
        mDeckName = deckName;
        mWins = wins;
        mLoses = loses;
    }

    public void win() {
        mWins++;
    }

    public void loss() {
        mLoses++;
    }

    public String getDeckName() {
        return mDeckName;
    }

    public int getWins() {
        return mWins;
    }

    public int getLoses() {
        return mLoses;
    }
}
