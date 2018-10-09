package com.sda.library.domain;

import com.sda.library.domain.model.Book;
import com.sda.library.domain.model.Borrow;
import com.sda.library.domain.model.BorrowStatus;
import com.sda.library.domain.port.BorrowRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BorrowService {

    private BooksService booksService;
    private BorrowRepository borrowRepository;

    public BorrowService(BooksService booksService, BorrowRepository borrowRepository) {
        this.booksService = booksService;
        this.borrowRepository = borrowRepository;
    }

    public Borrow borrow(String bookId, String username) {
        Optional<Book> book = booksService.findById(bookId);
        Borrow borrow = null;
        if (book.isPresent()) {
            borrow = new Borrow(book.get(), username);
            borrowRepository.save(borrow);
        }
        return borrow;
    }

    public List<Borrow> findByUserAndStatus(String username, BorrowStatus status) {
        return borrowRepository.findAll()
                .stream()
                .filter(e -> username.equals(e.getUser()))
                .filter(e -> status.equals(e.getBorrowStatus()))
                .collect(Collectors.toList());
    }
}
