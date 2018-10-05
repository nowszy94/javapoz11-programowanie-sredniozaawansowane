package com.sda.library.domain.port;

import com.sda.library.domain.model.Book;

import java.util.List;

public interface BooksRepository {
    List<Book> findAll();
}
