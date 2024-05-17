package com.project.adminLibros.config;

import com.project.adminLibros.models.Book;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonInitialization {

    private final String filePath = "adminLibros/books.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() throws IOException {
        try {

            File file = new File(filePath);
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (!created) {
                    throw new IOException("No se pudo crear el archivo.");
                }

                List<Book> books = new ArrayList<>();

                books.add(
                        new Book(
                                "Harry Potter",
                                "J.K. Rowling",
                                new Date(-784512000000L)));

                books.add(
                        new Book(
                                "The Great Gatsby",
                                "F. Scott Fitzgerald",
                                new Date(-784512000000L)));
                books.add(
                        new Book(
                                "Pinocho",
                                "Arturo Valdez",
                                new Date(-784512000000L)));
                books.add(
                        new Book(
                                "To Kill a Mockingbird",
                                "Harper Lee",
                                new Date(-784512000000L)));
                books.add(
                        new Book(
                                "1984",
                                "George Orwell",
                                new Date(-784512000000L)));

                objectMapper.writeValue(file, books);
                // objectMapper.writeValue(file, new ArrayList<>());
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
