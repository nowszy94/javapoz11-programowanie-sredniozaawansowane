package com.sda.library.domain;

import com.sda.library.domain.exceptions.InvalidPagesValueException;
import com.sda.library.domain.filtering.BooksFilteringChain;
import com.sda.library.domain.model.Book;
import com.sda.library.domain.port.BooksRepository;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
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

    public List<Book> findByLanguage(String language) {
        return findBy(language, "LANGUAGE");
    }

    private List<Book> findBy(String value, String key) {
        if (StringUtils.isBlank(value)) {
            return Collections.emptyList();
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(key, value);
        return filterBooks(parameters);
    }

    public List<Book> findByPagesRange(Integer from, Integer to) throws InvalidPagesValueException {
        validatePagesRangeArgument(from, to);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("PAGES_FROM", from);
        parameters.put("PAGES_TO", to);
        return filterBooks(parameters);
    }

    private void validatePagesRangeArgument(Integer from, Integer to) throws InvalidPagesValueException {
        if (from < 0 || to < 0) {
            throw new InvalidPagesValueException("Arguments can not be negative");
        }
        if (from > to) {
            throw new InvalidPagesValueException("From can not be greater than to");
        }
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

    public Map<String, Long> getAuthors() {
        List<Book> books = booksRepository.findAll();
        return books.stream()
                .map(e -> e.getAuthor())
                .distinct()
                .collect(Collectors.toMap(
                        author -> author,
                        author -> books.stream()
                                .filter(book -> author.equals(book.getAuthor()))
                                .count())
                );
    }

    public Optional<Book> findById(String bookId) {
        return booksRepository.findAll()
                .stream()
                .filter(e -> e.getId().equals(bookId))
                .findFirst();
    }
}
