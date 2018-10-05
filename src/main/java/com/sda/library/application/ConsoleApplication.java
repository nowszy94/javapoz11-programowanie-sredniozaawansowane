package com.sda.library.application;

import com.sda.library.domain.BooksService;
import com.sda.library.domain.model.Book;
import com.sda.library.domain.port.BooksRepository;
import com.sda.library.infrastructure.json.JsonBooksRepository;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {

    private ConsoleViews consoleViews;
    private BooksService booksService;

    public ConsoleApplication() {
        BooksRepository booksRepository = new JsonBooksRepository(
                new File("C:\\sda\\javapoz11-programowanie-sredniozaawansowane\\src\\main\\resources\\books.json")
        );
        this.consoleViews = new ConsoleViews(new Scanner(System.in));
        this.booksService = new BooksService(booksRepository);
    }

    public void start() {
        boolean flag = true;
        while(flag) {
            Integer option = consoleViews.menu();
            switch (option) {
                case 1:
                    showBooks();
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("zla opcja");
            }
        }
    }

    private void showBooks() {
        Integer option = consoleViews.showBooksMenu();
        switch (option) {
            case 1:
                String title = consoleViews.getBookName();
//                long before = System.currentTimeMillis();
                List<Book> books = booksService.findByTitle(title);
//                long after = System.currentTimeMillis();
//                System.out.println(after - before);
                consoleViews.displayBooks(books);
                break;
        }
    }
}
