package com.sda.library.application;

import com.sda.library.domain.BooksService;
import com.sda.library.domain.BorrowService;
import com.sda.library.domain.exceptions.InvalidPagesValueException;
import com.sda.library.domain.model.Book;
import com.sda.library.domain.model.Borrow;
import com.sda.library.domain.model.BorrowStatus;
import com.sda.library.domain.port.BooksRepository;
import com.sda.library.infrastructure.json.JsonBooksRepository;
import com.sda.library.infrastructure.memory.InMemoryBorrowRepository;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleApplication {

    private ConsoleViews consoleViews;
    private BooksService booksService;
    private BorrowService borrowService;

    public ConsoleApplication() {
        BooksRepository booksRepository = new JsonBooksRepository(
                new File("C:\\sda\\javapoz11-programowanie-sredniozaawansowane\\src\\main\\resources\\books.json")
        );
        this.consoleViews = new ConsoleViews(new Scanner(System.in));
        this.booksService = new BooksService(booksRepository);
        this.borrowService = new BorrowService(booksService, new InMemoryBorrowRepository());
    }

    public void start() {
        boolean flag = true;
        while(flag) {
            Integer option = consoleViews.menu();
            switch (option) {
                case 1:
                    showBooks();
                    break;
                case 2:
                    showAuthors();
                    break;
                case 3:
                    borrows();
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("zla opcja");
            }
        }
    }

    private void borrows() {
        String username = consoleViews.getUsername();
        List<Borrow> borrowedByUser = borrowService.findByUserAndStatus(username, BorrowStatus.BORROWED);
        consoleViews.displayBorrows(borrowedByUser);
    }

    private void showAuthors() {
        Map<String, Long> authors = booksService.getAuthors();
        consoleViews.displayAuthors(authors);
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
            case 2:
                String author = consoleViews.getAuthor();
                List<Book> booksByAuthor = booksService.findByAuthor(author);
                consoleViews.displayBooks(booksByAuthor);
                break;
            case 3:
                Integer date = consoleViews.getDate();
                List<Book> booksByDate = booksService.findByDate(date);
                consoleViews.displayBooks(booksByDate);
                break;
            case 4:
                String language = consoleViews.getLanguage();
                List<Book> booksByLanguage = booksService.findByLanguage(language);
                consoleViews.displayBooks(booksByLanguage);
                break;
            case 5:
                findByPagesRange();
                break;
        }
        if (option != 0) {
            borrow();
        }
    }

    private void borrow() {
        String id = consoleViews.getBookId();
        String username = consoleViews.getUsername();
        Borrow borrow = borrowService.borrow(id, username);
        if (borrow != null) {
            consoleViews.borrowSuccess(borrow.getBook().getTitle());
        } else {
            consoleViews.borrowFailure();
        }
    }

    private void findByPagesRange() {
        try {
            Integer from = consoleViews.getFromPages();
            Integer to = consoleViews.getToPages();
            List<Book> booksByPages = booksService.findByPagesRange(from, to);
            consoleViews.displayBooks(booksByPages);
        } catch (InvalidPagesValueException e) {
            consoleViews.displayError("Niepoprawne dane");
        }
    }
}
