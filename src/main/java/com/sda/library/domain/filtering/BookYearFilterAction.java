package com.sda.library.domain.filtering;

import com.sda.library.domain.model.Book;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BookYearFilterAction extends SimpleAbstractFilterAction {

    @Override
    protected String getKey() {
        return "YEAR";
    }

    @Override
    protected Predicate<Book> predicate(Map<String, Object> parameters) {
        return e -> e.getYear().equals(parameters.get("YEAR"));
    }
}
