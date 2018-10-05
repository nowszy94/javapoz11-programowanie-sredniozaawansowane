package com.sda.library.domain.filtering;

import com.sda.library.domain.model.Book;

import java.util.Map;
import java.util.stream.Stream;

public class BookYearFilterAction implements FilterAction {

    @Override
    public boolean isMyResponsibility(Map<String, Object> parameters) {
        return parameters.containsKey("YEAR");
    }

    @Override
    public Stream<Book> action(Stream<Book> stream, Map<String, Object> parameters) {
        Object year = parameters.get("YEAR");
        return stream.filter(e-> e.getYear().equals(year));
    }
}
