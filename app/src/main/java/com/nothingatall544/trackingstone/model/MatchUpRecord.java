package com.nothingatall544.trackingstone.model;

/**
 * Created by derp on 8/29/2015.
 */
public class MatchUpRecord {
    private String mDeckName;
    private int mHeroImageRef;
    private int mWins;
    private int mLoses;

    public MatchUpRecord(String deckName, int heroImageRef, int wins, int loses) {
        mDeckName = deckName;
        mHeroImageRef = heroImageRef;
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

    public int getHeroImageRef() {
        return mHeroImageRef;
    }

    public int getWins() {
        return mWins;
    }

    public int getLosses() {
        return mLoses;
    }
}
