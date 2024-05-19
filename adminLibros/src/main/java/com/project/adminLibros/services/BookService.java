package com.project.adminLibros.services;

import com.project.adminLibros.DTO.BookDto;
import com.project.adminLibros.dao.BookDao;
import com.project.adminLibros.models.Book;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public List<BookDto> getAllBooks(String bookName, String author, Date releaseDate) {

        ModelMapper modelMapper = new ModelMapper();

        return bookDao.getAllBooks(bookName, author, releaseDate).stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());

    }

    public BookDto createBook(BookDto bookDto) {

        try {

            ModelMapper modelMapper = new ModelMapper();
            Book book = modelMapper.map(bookDto, Book.class);
            this.bookDao.createBook(book);

            return bookDto;
        } catch (Exception e) {
            throw new UnsupportedOperationException("An error occurred while creating the book.");
        }
    }

    public BookDto updateBook(long id, BookDto bookDto) {

        // try {

        ModelMapper modelMapper = new ModelMapper();
        Book book = modelMapper.map(bookDto, Book.class);
        Book book2 = this.bookDao.updateBook(id, book);

        if (book2 == null) {
            return null;
        }

        return bookDto;
    }

    public String deleteBook(String bookName, String author, Date releaseDate) {

        return this.bookDao.deleteBook(bookName, author, releaseDate);
    }
}
