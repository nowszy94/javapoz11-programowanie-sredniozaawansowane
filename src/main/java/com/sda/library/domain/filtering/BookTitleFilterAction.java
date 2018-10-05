package com.sda.library.domain.filtering;

import com.sda.library.domain.model.Book;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.stream.Stream;

public class BookTitleFilterAction implements FilterAction {

    @Override
    public boolean isMyResponsibility(Map<String, Object> parameters) {
        return parameters.containsKey("TITLE");
    }

    @Override
    public Stream<Book> action(Stream<Book> stream, Map<String, Object> parameters) {
        return stream.filter(e -> StringUtils.containsIgnoreCase(e.getTitle(), (String) parameters.get("TITLE")));
    }
}
