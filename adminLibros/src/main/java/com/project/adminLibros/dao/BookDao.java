package com.project.adminLibros.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.project.adminLibros.models.Book;

public interface BookDao {

    List<Book> getAllBooks(String bookName, String author, Date releaseDate);

    Book createBook(Book book);

    void updateBook(long id, Book book) throws IOException;

    void deleteBook(String id) throws IOException;

}
