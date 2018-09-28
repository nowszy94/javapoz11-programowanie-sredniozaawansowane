package com.sda.todo.views;

import com.sda.todo.model.Todo;
import com.sda.todo.model.TodoStatus;
import com.sda.todo.model.TodoUser;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TodoConsoleView {
    private Scanner scanner;

    public TodoConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer menu() {
        System.out.println("Todo application");
        System.out.println("1. Zaloguj");
        System.out.println("2. Zarejestruj");
        System.out.println("3. Dodaj zadanie");
        System.out.println("4. Wyswietl zadania");
        System.out.println("0. Koniec");

        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public String logInName() {
        System.out.println("Podaj nick");
        return scanner.nextLine();
    }

    public String logInPassword() {
        System.out.println("Podaj haslo");
        return scanner.nextLine();
    }

    public String registerName() {
        return logInName();
    }

    public String registerPassword() {
        return logInPassword();
    }

    public String createNewTodoName() {
        System.out.println("Podaj nazwe zadania");
        return scanner.nextLine();
    }

    public String createNewTodoDescription() {
        System.out.println("Podaj opis zadania");
        return scanner.nextLine();
    }

    public void displayError(String message) {
        System.out.println();
        System.out.println("ERROR");
        System.out.println(message);
        System.out.println("ERROR");
        System.out.println();
    }

    public void displaySuccess(String message) {
        System.out.println();
        System.out.println("SUCCESS");
        System.out.println(message);
        System.out.println("SUCCESS");
        System.out.println();
    }

    public void exit() {
        System.out.println("Zapraszamy ponownie");
    }

    public Integer showToDoListWithOptions(List<Todo> allToDo) {
        System.out.println("Lista Zadań");
        System.out.println("------------");
        for (int i = 0; i < allToDo.size(); i++) {
            Todo todo = allToDo.get(i);
            TodoUser creator = todo.getCreator();
            Optional<TodoUser> owner = Optional.ofNullable(todo.getOwner());
            TodoStatus todoStatus = todo.getTodoStatus();
            System.out.println((i + 1) +
                    ". |\"" + todo.getName() +
                    "\" |\"" + creator.getName() +
                    "\"|\" " + owner.orElse(TodoUser.unnasigned()).getName() +
                    "\"|" + todoStatus.toString().toUpperCase());
        }
        System.out.println("----------------");
        System.out.println("1: Wyswietl");
        System.out.println("2: Usuń");
        System.out.println("3: Przypisz");
        System.out.println("4: Zmien Status");
        System.out.println("0: Wyjdz");

        int option = scanner.nextInt();
//        scanner.nextLine();
        return option;
    }

    public Integer getTodoId() {
        System.out.println("Podaj numer zadania");
        int todoId = scanner.nextInt();
        scanner.nextLine();
        return todoId;
    }

    public void showTodoWithDetails(Optional<Todo> todo) {
        String message = todo.map(e -> {
            TodoUser creator = e.getCreator();
            Optional<TodoUser> owner = Optional.ofNullable(e.getOwner());
            return e.getName() + " (" + e.getTodoStatus().toString() + ") (" + e.getCreationDate().toString() + ")\n" +
                    e.getDescription() + "\n" +
                    "Tworca: " + creator.getName() + "\n" +
                    "Przypisane: " + owner.orElse(TodoUser.unnasigned()).getName();
        }).orElse("Zadanie nie istnieje");

//        String message2;
//        if (todo.isPresent()) {
//            Todo todo1 = todo.get();
//            message2 = "...."
//        } else {
//            message2 = "Zadanie nie istnieje";
//        }

        System.out.println(message);
    }

    public String getPossibleId() {
        String possibleId = scanner.nextLine();

//        if (possibleId.length() > 0) {
//            return possibleId.substring(1);
//        } else {
//            return possibleId;
//        }

        return possibleId.length() > 0 ?
                possibleId.substring(1) :
                possibleId;
    }

    public void displayTodoRemove(Optional<Todo> removedTodo) {
        System.out.println(removedTodo.map(e -> "Usunieto zadanie " + e.getName())
                .orElse("Zadanie nie istnieje"));
    }

    public void displayAssignment(Optional<Todo> todo, TodoUser currentUser) {
        System.out.println(todo.map(e -> "Przypisano " + currentUser.getName() + " do zadania \"" + e.getName() + "\"")
                .orElse("Zadanie nie istnieje"));
    }
}
