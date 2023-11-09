package com.duckrace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DuckRacer {
    private final int id;
    private String name;
    private final Collection<Reward> rewards = new ArrayList<>();

    public DuckRacer(int id, String name) {
        this.id = id;
        setName(name);
    }

    // business ("action") methods
    public void win(Reward reward) {
        rewards.add(reward);
    }

    // accessor methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // derived property
    public int getWins() {
        return rewards.size();
    }

    /*
     * NOTE: instead of returning a direct reference to the collection,
     * we return a "read-only" view.
     */
    public Collection<Reward> getRewards() {
        return Collections.unmodifiableCollection(rewards);  // returns a read-thru wrapper
        // return List.copyOf(rewards);  // returns a snapshot
    }

    @Override
    public String toString() {
        return String.format("%s: id=%s, name=%s, wins=%s, rewards=%s",
                getClass().getSimpleName(), getId(), getName(), getWins(), getRewards());
    }
}