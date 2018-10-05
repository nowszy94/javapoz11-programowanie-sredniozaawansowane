package com.sda.library.application;

import com.sda.library.domain.model.Book;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ConsoleViews {
    private Scanner scanner;

    public ConsoleViews(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer menu() {
        System.out.println("Biblioteka");
        System.out.println("1. Ksiazki");
        System.out.println("2. ...");
        System.out.println("0. Koniec");
        return getNumberFromUser();
    }

    public Integer showBooksMenu() {
        System.out.println("1. Znajdz po nazwie");
        System.out.println("2. Znajdz po autorze");
        System.out.println("3. Znajdz po datcie wydania");
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
        System.out.println(book.getTitle() + " (" + book.getYear() + ") - " + book.getAuthor());
    }

    public String getAuthor() {
        System.out.println("Podaj autora ksiazki");
        return scanner.nextLine();
    }
}
