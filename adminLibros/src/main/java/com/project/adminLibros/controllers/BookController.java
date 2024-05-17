package com.project.adminLibros.controllers;

import com.project.adminLibros.models.Book;
import com.project.adminLibros.services.BookService;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(name = "book_name", required = false) String bookName,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "release_date", required = false) @DateTimeFormat(pattern = "yyyy/MM/dd") Date releaseDate) {
        return new ResponseEntity<>(this.bookService.getAllBooks(bookName, author, releaseDate), HttpStatus.OK);
        // return bookService.getAllBooks(bookName, author, releaseDate);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody @Valid Book book) {
        return new ResponseEntity<>(this.bookService.createBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable String id, @RequestBody Book book) throws IOException {
        bookService.updateBook(Long.parseLong(id), book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) throws IOException {
        bookService.deleteBook(id);
    }

}
