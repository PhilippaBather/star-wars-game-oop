package com.bather.philippa.starwars.player;

import com.bather.philippa.starwars.game.Counter;
import com.bather.philippa.starwars.game.Square;

public class Player {
    private final String name;
    private int lives;

    private Square position;

    public Player(String name) {
        this.name = name;
        this.lives = 3;
    }

    public String getName() {
        return name;
    }

    public Square getPosition() {
        return position;
    }

    public void updatePosition(Square position) {
//        this.position = new Square(position.getColIndex(), position.getRowIndex(), position.getCounter());
        this.position = position;
    }
    public Square moveRight(Counter counter, int totalColumns) {
        int newColIndex;
        int curColIndex = position.getColIndex();
        if(curColIndex < totalColumns) {
            newColIndex = curColIndex + 1;
        } else {
            newColIndex = 1;
        }
        return new Square(newColIndex, this.position.getRowIndex(), counter);
     }

    public Square moveLeft(Counter counter, int totalColumns) {
        int newColIndex;
        int curColIndex = position.getColIndex();
        if(curColIndex > 1) {
            newColIndex = curColIndex - 1;
        } else {
            newColIndex = totalColumns;
        }

        return new Square(newColIndex, this.position.getRowIndex(), counter);
    }

    public Square moveUp(Counter counter, int totalRows) {
        int newRowIndex;
        int curRowIndex = position.getRowIndex();
        if(curRowIndex > 1) {
            newRowIndex = curRowIndex - 1;
        } else {
            newRowIndex = totalRows;
        }

        return new Square(this.position.getColIndex(), newRowIndex, counter);
    }

    public Square moveDown(Counter counter, int totalRows) {
        int newRowIndex;
        int curRowIndex = position.getRowIndex();
        if (curRowIndex < totalRows) {
            newRowIndex = curRowIndex + 1;
        } else {
            newRowIndex = 1;
        }

        return new Square(this.position.getColIndex(), newRowIndex, counter);
    }
    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void decrementLives() {
        this.lives--;
        System.out.format("Allies of your nemesis be here...one life lost.  %d lives remaining\n", lives);
    }

    public void incrementLives() {
        this.lives++;
        System.out.format("A potion be here!...One extra life for you.  %d lives remaining\n", lives);
    }

}
