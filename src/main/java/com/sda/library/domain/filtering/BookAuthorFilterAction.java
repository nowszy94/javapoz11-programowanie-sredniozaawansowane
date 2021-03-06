package com.sda.library.domain.filtering;

import com.sda.library.domain.model.Book;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BookAuthorFilterAction extends SimpleAbstractFilterAction {
    @Override
    protected String getKey() {
        return "AUTHOR";
    }

    @Override
    protected Predicate<Book> predicate(Map<String, Object> parameters) {
        return e -> StringUtils.containsIgnoreCase(e.getAuthor(), (String) parameters.get("AUTHOR"));
    }
}
