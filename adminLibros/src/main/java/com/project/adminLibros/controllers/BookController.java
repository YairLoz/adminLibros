package com.project.adminLibros.controllers;

import com.project.adminLibros.DTO.BookDto;
import com.project.adminLibros.models.Book;
import com.project.adminLibros.services.BookService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(
            @RequestParam(name = "book_name", required = false) String bookName,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "release_date", required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") Date releaseDate) {

        List<BookDto> books = this.bookService.getAllBooks(bookName, author, releaseDate);

        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid BookDto book) {
        return new ResponseEntity<>(this.bookService.createBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable String id, @RequestBody BookDto book) {

        if (!id.matches("\\d+")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        BookDto bookDto = this.bookService.updateBook(Long.parseLong(id), book);

        if (bookDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteBook(
            @RequestParam(name = "book_name", required = false) String bookName,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "release_date", required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") Date releaseDate) {

        // if (id != null) {
        // Long.parseLong(id);
        // if (!id.matches("\\d+")) {
        // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid id.");
        // }
        // }

        return new ResponseEntity<>(this.bookService.deleteBook(bookName, author, releaseDate),
                HttpStatus.OK);
    }

}
