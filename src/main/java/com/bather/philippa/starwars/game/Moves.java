package com.bather.philippa.starwars.game;

public enum Moves {
    UP(8),
    DOWN(2),
    RIGHT(6),
    LEFT(4);

    private int numericalRep;

    Moves(int numericalRep) {
        this.numericalRep = numericalRep;
    }

    public int getMoveInt() {
        return numericalRep;
    }

    public static boolean movesContain(int key) {
        for (Moves move : values()) {
            if (move.numericalRep == key) return true;
        }
        return false;
    }
}
