package com.bather.philippa.starwars.game;

import com.bather.philippa.starwars.player.Player;

import java.util.ArrayList;
public class Board {
    private final int TOTAL_COLUMNS;
    private final int TOTAL_ROWS;
    private final int TOTAL_SQUARES;
    private final ArrayList<Square> board;
    public Board(int totalRows, int totalCol) {
        this.board = new ArrayList<Square>();
        this.TOTAL_ROWS = totalRows;
        this.TOTAL_COLUMNS = totalCol;
        this.TOTAL_SQUARES = TOTAL_ROWS * TOTAL_COLUMNS;
    }

    public void populateBoardSquares() {
        for (int i = 1; i <= TOTAL_ROWS; i++) {
            for (int j = 1; j <= TOTAL_COLUMNS; j++) {
                Square square = new Square(j, i, Counter.FREE);
                board.add(square);
            }
        }
    }

    public void populateBoardAllyDarthVader(Counter counter, int quantity) {
        populateBoardCounters(counter, quantity);
    }

    public void populateBoardAllyYoda(Counter counter, int quantity) {
        populateBoardCounters(counter, quantity);
    }

    public void populateBoardPotions(Counter counter, int quantity) {
        populateBoardCounters(counter, quantity);
    }

    public void populateBoardPlayers(Counter player1, Counter player2, int quantityPlayer1, int quantityPlayer2) {
        populateBoardCounters(player1, quantityPlayer1);
        populateBoardCounters(player2, quantityPlayer2);
    }

    private void populateBoardCounters(Counter counter, int quantity) {

        do {
            int randIndex = (int) Math.floor(Math.random() * TOTAL_SQUARES);
            if (board.get(randIndex).getCounter() == Counter.FREE) {
                board.get(randIndex).setCounter(counter);
                quantity--;
            }
        } while (quantity > 0);
    }

    public Square getPlayerPosition(Counter player) {
        for (Square square : board) {
            if(square.getCounter() == player) {
                return square;
            }
        }
        return null;
    }

    public Square getSquare(int colIndex, int rowIndex) {
        for (Square square : board) {
            if (square.getColIndex() == colIndex && square.getRowIndex() == rowIndex) {
                return square;
            }
        }
        return null;
    }

    public void updateBoard(Player player, Square newPlayerPosition) {
        updatePrevSquare(player);
        updateCurSquare(newPlayerPosition);
    }

        // update player's previous square to contain a FREE counter
    private void updatePrevSquare(Player player) {
        Square prevPlayerPosition = player.getPosition();
        int index = board.indexOf(prevPlayerPosition);
        prevPlayerPosition.setCounter(Counter.FREE);
        board.set(index, prevPlayerPosition);
    }

    private void updateCurSquare(Square newPlayerPosition) {
        Square square = getSquare(newPlayerPosition.getColIndex(), newPlayerPosition.getRowIndex());
        int index = board.indexOf(square);
        board.set(index, newPlayerPosition);
    }

    public void printBoard() {
        int index = 0;
        for (int i = 0; i < TOTAL_ROWS; i++) {
            for (int j = 0; j < TOTAL_COLUMNS; j++) {
                Counter counter = (Counter) board.get(index).getCounter();
                String marker = counter.getCounterMarker();
                System.out.print(marker);
                if (j < TOTAL_COLUMNS - 1) {
                    System.out.print(" | ");
                }
                index++;
            }
            System.out.println("\n");
        }
    }
}
