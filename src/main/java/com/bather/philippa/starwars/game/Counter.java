package com.bather.philippa.starwars.game;

/**
 * Counters for board game.  Those that are not player counters,
 * DARTH_VADER and YODA, have markers represented as an 'X', as
 * their position should be concealed on the board. *
 */
public enum Counter {
    DARTH_VADER("D"),
    YODA("Y"),
    ALLY_DARTH_VADER("X"),
    ALLY_YODA("X"),
    POTION("X"),
    FREE("X");


    private String counterMarker;

    Counter(String counterMarker) {
        this.counterMarker = counterMarker;
    }

    public String getCounterMarker() {
        return counterMarker;
    }
}
