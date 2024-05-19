package com.project.adminLibros.dao;

import java.util.Date;
import java.util.List;

import com.project.adminLibros.models.Book;

public interface BookDao {

    List<Book> getAllBooks(String bookName, String author, Date releaseDate);

    Book createBook(Book book);

    Book updateBook(long id, Book book);

    String deleteBook(String bookName, String author, Date releaseDate);

}
