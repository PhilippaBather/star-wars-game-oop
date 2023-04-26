package com.bather.philippa.starwars.utils;

import com.bather.philippa.starwars.game.Moves;

import java.util.Scanner;

public class Validation {
    private static final String INPUT_MSG = "Please enter a valid number: ";

    public static int validateIntInput(String message) {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        int key = -1;

        do {
            if(scanner.hasNextInt()) {
                key = scanner.nextInt();
                if (Moves.movesContain(key)) isValid = true;
                else System.out.println(INPUT_MSG.concat(message));
            } else {
                System.out.println(INPUT_MSG.concat(message));
                scanner.next(); // clear buffer
            }
        } while(!isValid);

        return key;
    }
}
