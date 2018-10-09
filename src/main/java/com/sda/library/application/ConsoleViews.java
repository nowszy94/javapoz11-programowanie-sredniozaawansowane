package com.sda.library.application;

import com.sda.library.domain.model.Book;
import com.sda.library.domain.model.Borrow;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleViews {
    private Scanner scanner;

    public ConsoleViews(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer menu() {
        System.out.println("Biblioteka");
        System.out.println("1. Ksiazki");
        System.out.println("2. Autorzy");
        System.out.println("3. Wypozyczenia");
        System.out.println("0. Koniec");
        return getNumberFromUser();
    }

    public Integer showBooksMenu() {
        System.out.println("1. Znajdz po nazwie");
        System.out.println("2. Znajdz po autorze");
        System.out.println("3. Znajdz po dacie wydania");
        System.out.println("4. Znajdz po jezyku wydania");
        System.out.println("5. Znajdz po zakresie stron");
        System.out.println("0. Wyjdz");
        return getNumberFromUser();
    }

    public String getBookName() {
        System.out.println("Podaj nazwe ksiazki");
        return scanner.nextLine();
    }

    public void displayBooks(List<Book> books) {
        if (books.size() > 0) {
//          books.forEach(this::displayShortenedBook);
            books.forEach(book -> displayShortenedBook(book));
        } else {
            System.out.println("Brak ksiazek do wyswietlenia");
        }
        waitForAction();
    }

    private void waitForAction() {
        System.out.println("Wcisnij enter zeby kontynuowac");
        scanner.nextLine();
    }

    private Integer getNumberFromUser() {
        Integer option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    private void displayShortenedBook(Book book) {
        System.out.println(book.getId() + ". " + book.getTitle() + " (" + book.getYear() + ") - " + book.getAuthor());
    }

    public String getAuthor() {
        System.out.println("Podaj autora ksiazki");
        return scanner.nextLine();
    }

    public Integer getDate() {
        System.out.println("Podaj date wydania");
        return getNumberFromUser();
    }

    public String getLanguage() {
        System.out.println("Podaj jezyk");
        return scanner.nextLine();
    }

    public Integer getFromPages() {
        System.out.println("Podaj dolny zakres stron");
        return getNumberFromUser();
    }

    public Integer getToPages() {
        System.out.println("Podaj gorny zakres stron");
        return getNumberFromUser();
    }

    public void displayError(String message) {
        System.out.println("ERROR " + message);
        waitForAction();
    }

    public void displayAuthors(Map<String, Long> authors) {
        authors.entrySet()
                .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue()));
    }

    public String getBookId() {
        System.out.println("Podaj identyfikator ksiazki");
        return scanner.nextLine();
    }

    public String getUsername() {
        System.out.println("Podaj nazwe uzytkownika");
        return scanner.nextLine();
    }

    public void borrowSuccess(String bookTitle) {
        System.out.println("Brawo! Udalo sie wypozyczyc ksiazke " + bookTitle);
    }

    public void borrowFailure() {
        System.out.println("Nie udalo sie wypozyczyc ksiazki");
    }

    public void displayBorrows(List<Borrow> borrowList) {
        if (borrowList.size() == 0) {
            System.out.println("Brak wypozyczen");
        } else {
            for (int i = 0; i < borrowList.size(); i++) {
                Borrow borrow = borrowList.get(i);
                String bookTitle = borrow.getBook().getTitle();
                Instant borrowDate = borrow.getBorrowDate();
                System.out.println((i + 1) + ". " + bookTitle + "\t" + borrowDate);
            }
        }
    }
}
