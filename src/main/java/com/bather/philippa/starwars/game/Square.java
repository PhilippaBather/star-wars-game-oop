package com.bather.philippa.starwars.game;

public class Square {

    private int colIndex;
    private int rowIndex;
    private Enum<Counter> counter;

    public Square() {
    }

    public Square(int colIndex, int rowIndex, Enum<Counter> counter) {
        this.colIndex = colIndex;
        this.rowIndex = rowIndex;
        this.counter = counter;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Enum<Counter> getCounter() {
        return counter;
    }

    public void setCounter(Enum<Counter> counter) {
        this.counter = counter;
    }
}
