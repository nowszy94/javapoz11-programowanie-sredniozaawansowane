package com.sda.hangman.application;

import com.sda.hangman.domain.GameFactory;
import com.sda.hangman.domain.model.Game;
import com.sda.hangman.domain.model.GameStatus;

import java.util.Scanner;

public class ConsoleApplication {

    private ConsoleViews consoleViews;
    private GameFactory gameFactory;

    public ConsoleApplication(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
        this.consoleViews = new ConsoleViews(new Scanner(System.in));
    }

    public void start() {
        Integer menuOption = consoleViews.mainMenu();
        switch (menuOption) {
            case 1:
                startGame();
                break;
            case 2:
                System.out.println("Tutaj beda wyniki");
                break;
            case 0:
                System.out.println("tutaj koniec");
                break;
            default:
                System.out.println("wybrano zla opcje");
        }
    }

    private void startGame() {
        Game game = gameFactory.createGame();
        game.setStatus(GameStatus.ACTIVE);
        do {
            char nextLetter = consoleViews.displayGame(game);
        } while (game.getStatus() == GameStatus.ACTIVE);
    }
}
