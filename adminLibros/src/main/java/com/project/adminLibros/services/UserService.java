package com.project.adminLibros.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.adminLibros.dao.BookDao;
import com.project.adminLibros.models.Book;

@Service
public class UserService {

    @Autowired
    private BookDao bookDao;

    public List<Book> getAllBooks(String bookName, String author, Date releaseDate) throws IOException {
        return bookDao.getAllBooks(bookName, author, releaseDate);
    }

    public Book createBook(Book body) throws IOException {
        return bookDao.createBook(body);
    }

}
