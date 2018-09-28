package com.sda.menu;

import com.sda.todo.model.Command;

import java.util.Scanner;

public class MenuApplication {

    private MenuViews menuViews;

    public MenuApplication(MenuViews menuViews) {
        this.menuViews = menuViews;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuViews menuViews = new MenuViews(scanner);

        MenuApplication menuApplication = new MenuApplication(menuViews);
        menuApplication.start();
    }

    private void start() {
        Command menu = menuViews.menu();
        switch (menu.getOption()) {
            case 1:
                startGame();
                break;
            case 2:

            case 0:
        }
    }

    private void startGame() {
        Command command = menuViews.startGameMenu();

    }
}
