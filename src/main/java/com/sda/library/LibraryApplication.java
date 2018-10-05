package com.sda.library;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.library.application.ConsoleApplication;
import com.sda.library.infrastructure.json.BookDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LibraryApplication {
    public static void main(String[] args) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String path = "C:\\sda\\javapoz11-programowanie-sredniozaawansowane\\src\\main\\resources\\books.json";
//        List<BookDto> books = objectMapper.readValue(new File(path), new TypeReference<List<BookDto>>(){});
//        System.out.println();

        new ConsoleApplication().start();
    }
}
