package com.sda.library.domain.filtering;

import com.sda.library.domain.model.Book;

import java.util.Map;
import java.util.stream.Stream;

public interface FilterAction {
    boolean isMyResponsibility(Map<String, Object> parameters);

    Stream<Book> action(Stream<Book> stream, Map<String, Object> parameters);
}
