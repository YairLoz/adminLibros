package com.project.adminLibros.services;

import com.project.adminLibros.dao.BookDao;
import com.project.adminLibros.models.Book;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public List<Book> getAllBooks(String bookName, String author, Date releaseDate) {
        return bookDao.getAllBooks(bookName, author, releaseDate);
    }

    public Book createBook(Book body) {
        return bookDao.createBook(body);
    }

    public void updateBook(long id, Book book) throws IOException {
        bookDao.updateBook(id, book);
    }

    public void deleteBook(String id) throws IOException {
        bookDao.deleteBook(id);
    }
}
