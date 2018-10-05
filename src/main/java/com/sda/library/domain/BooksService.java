package com.sda.library.domain;

import com.sda.library.domain.filtering.BooksFilteringChain;
import com.sda.library.domain.model.Book;
import com.sda.library.domain.port.BooksRepository;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BooksService {
    private BooksRepository booksRepository;
    private BooksFilteringChain chain;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
        this.chain = new BooksFilteringChain();
    }

    public List<Book> findByTitle(String title) {
        if (StringUtils.isBlank(title)) {
            return Collections.emptyList();
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("TITLE", title);
        return filterBooks(parameters);
    }

    public List<Book> findByAuthor(String author) {
        if (StringUtils.isBlank(author)) {
            return Collections.emptyList();
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("AUTHOR", author);
        return filterBooks(parameters);
    }

    public List<Book> findByDate(Integer date) {
        if (date == null) {
            return Collections.emptyList();
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("YEAR", date);
        return filterBooks(parameters);
    }

    private List<Book> filterBooks(Map<String, Object> filterParameters) {
        return chain.filter(booksRepository.findAll(), filterParameters)
                .collect(Collectors.toList());
    }
}
