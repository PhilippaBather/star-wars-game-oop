package com.bather.philippa.starwars.game;

import com.bather.philippa.starwars.player.DarthVader;
import com.bather.philippa.starwars.player.Player;
import com.bather.philippa.starwars.player.Yoda;

import static com.bather.philippa.starwars.utiles.Validation.validateIntInput;

public class Game {
    private final int TOTAL_COLUMNS = 5;
    private final int TOTAL_ROWS = 5;
    private final DarthVader darthVader;
    private final Yoda yoda;
    private final Board board;
    private final String GAME_TITLE = """
            \n **** STAR WARS BOARD GAME ****\n
    """;
    private final String GAME_RULES = """
            Yoda (Y) and Darth Vader (D) are pitted against each other to the death.
            Attempt to catch your foe before he catches you to win.
            Find one of your foe's hidden allies and lose a life.
            Find a hidden potion and gain a life.
            You have 3 lives.\n
            """;
    private final String MENU_OPTIONS = "Up = 8, Down = 2, Right = 6, Left = 4";
    private int curPlayer;
    private boolean isGameOver;

    public Game() {
        this.darthVader = new DarthVader();
        this.yoda = new Yoda();
        this.board = new Board(TOTAL_ROWS, TOTAL_COLUMNS);
        this.isGameOver = false;
        this.curPlayer = 1;
    }

    public void initiateGame() {
        setupBoard();
        updatePlayerPositions();
        play();
    }

    private void setupBoard() {
        board.populateBoardSquares();
        int TOTAL_ALLIES = 5; // per player
        board.populateBoardAllyDarthVader(Counter.ALLY_DARTH_VADER, TOTAL_ALLIES);
        board.populateBoardAllyYoda(Counter.ALLY_YODA, TOTAL_ALLIES);
        int TOTAL_POTIONS = 6;
        board.populateBoardPotions(Counter.POTION, TOTAL_POTIONS);
        int TOTAL_DARTH_VADER = 1;
        int TOTAL_YODA = 1;
        board.populateBoardPlayers(Counter.YODA, Counter.DARTH_VADER, TOTAL_YODA, TOTAL_DARTH_VADER);
    }

    private void play() {
        printGameDetails();
        do {
            printBoard();
            promptPlayerMove();
            int key = processInput();
            processPlayerMove(key);
            checkGameStatus(curPlayer);
            if (!isGameOver) switchPlayer();
        } while(!isGameOver);

        Player player = curPlayer == 1 ? yoda : darthVader;
        System.out.format("GAME OVER!!! %s wins!", player.getName()); // notify user
    }

    public void printGameDetails() {
        System.out.println(GAME_TITLE);
        System.out.println(GAME_RULES);
    }


    private void printBoard() {
        board.printBoard();
    }
    private void promptPlayerMove() {
        String player = curPlayer == 1 ? yoda.getName() : darthVader.getName();
        System.out.format("%s make your move \n", player);
        System.out.println(MENU_OPTIONS);
    }
    private int processInput() {
        return validateIntInput(MENU_OPTIONS);
    }

    private void processPlayerMove(int key) {
        Player player = curPlayer == 1 ? yoda : darthVader;
        Counter counter = curPlayer == 1 ? Counter.YODA : Counter.DARTH_VADER;
        Square newPlayerPosition = null;
        switch (key) {
            case 8 -> newPlayerPosition = player.moveUp(counter, TOTAL_ROWS);
            case 2 -> newPlayerPosition = player.moveDown(counter, TOTAL_ROWS);
            case 6 -> newPlayerPosition = player.moveRight(counter, TOTAL_COLUMNS);
            case 4 -> newPlayerPosition = player.moveLeft(counter, TOTAL_COLUMNS);
            default -> System.out.println("Please enter...");
        }
        // check contents of squares and update lives in invoked methods
        assert newPlayerPosition != null;

        checkNemesis(player, newPlayerPosition);
        if (isGameOver) return;  // terminate game

        checkEnemies(newPlayerPosition, counter);
        checkPotions(newPlayerPosition);
        // update board
        board.updateBoard(player, newPlayerPosition);
        // update player position with new square
        player.updatePosition(newPlayerPosition);
    }

    private void checkNemesis(Player player, Square newPlayerPosition) {
        Counter oppCounter = (player instanceof Yoda) ? Counter.DARTH_VADER : Counter.YODA;
        Square square = board.getSquare(newPlayerPosition.getColIndex(), newPlayerPosition.getRowIndex());
        if (square == null || square.getCounter() == oppCounter) {
            Player oppPlayer = (player instanceof Yoda) ? darthVader : yoda;
            oppPlayer.setLives(0);
            isGameOver = true;
        }
    }

    private void checkEnemies(Square newPlayerPosition, Counter counter) {
        Square square = board.getSquare(newPlayerPosition.getColIndex(), newPlayerPosition.getRowIndex());
        if (square == null || square.getCounter() != counter) return;
        if (curPlayer == 1) {
            yoda.decrementLives();
        } else {
            darthVader.decrementLives();
        }
    }

    private void checkPotions(Square newPlayerPosition) {
        Square square = board.getSquare(newPlayerPosition.getColIndex(), newPlayerPosition.getRowIndex());
        if (square == null || square.getCounter() != Counter.POTION) return;

        if (curPlayer == 1) {
            yoda.incrementLives();
        } else {
            darthVader.incrementLives();
        }
    }

    private void updatePlayerPositions() {
        Square posPlayer1 = board.getPlayerPosition(Counter.YODA);
        yoda.updatePosition(posPlayer1);
        Square posPlayer2 = board.getPlayerPosition(Counter.DARTH_VADER);
        darthVader.updatePosition(posPlayer2);
    }

    private void switchPlayer() {
        curPlayer = curPlayer == 1 ? 2: 1;
    }

    private void checkGameStatus(int curPlayer) {
        int yodaLives = yoda.getLives();
        int darthLives = darthVader.getLives();
        isGameOver = (yodaLives < 1 || darthLives < 1); // if zero lives, isGameOver == true
    }
}
