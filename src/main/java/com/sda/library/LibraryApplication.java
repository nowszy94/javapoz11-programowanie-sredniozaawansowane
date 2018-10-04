package com.sda.library;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LibraryApplication {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String path = "C:\\sda\\javapoz11-programowanie-sredniozaawansowane\\src\\main\\resources\\books.json";
        List<Book> books = objectMapper.readValue(new File(path), new TypeReference<List<Book>>(){});
        System.out.println();
    }
}
