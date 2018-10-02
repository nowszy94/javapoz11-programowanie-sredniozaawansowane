package com.sda.hangman.application;

import com.sda.hangman.domain.model.Game;

import java.util.Scanner;

public class ConsoleViews {
    private Scanner scanner;

    public ConsoleViews(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer mainMenu() {
        System.out.println("1. Start");
        System.out.println("2. Wyniki");
        System.out.println("0. Koniec");
        return getIntValue();
    }

    private Integer getIntValue() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public char displayGame(Game game) {
        char[] phraseStatus = game.getPhraseStatus();
        int leftAttempts = game.getLeftAttempts();
        String phraseStatusAsString = new String(phraseStatus);
        System.out.println(phraseStatusAsString + " (" + leftAttempts + ")");
        return scanner.nextLine().charAt(0);
    }

    public void displayWrongLetterAdded() {
        System.out.println("Podano niepoprawna litere");
    }

    public void displayGameWon() {
        System.out.println("Wygrales/Wygralas!");
        waitForAction();
    }

    public void displayGameLose() {
        System.out.println("Przegrales/Przegralas!");
        waitForAction();
    }

    private void waitForAction() {
        System.out.println("Wcisnij enter zeby kontynuowac");
        scanner.nextLine();
    }
}
