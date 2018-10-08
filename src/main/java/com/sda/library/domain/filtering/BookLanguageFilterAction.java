package com.sda.library.domain.filtering;

import com.sda.library.domain.model.Book;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.function.Predicate;

public class BookLanguageFilterAction extends SimpleAbstractFilterAction {
    @Override
    protected String getKey() {
        return "LANGUAGE";
    }

    @Override
    protected Predicate<Book> predicate(Map<String, Object> parameters) {
        return e -> StringUtils.containsIgnoreCase(e.getLanguage(), (String) parameters.get("LANGUAGE"));
    }
}
