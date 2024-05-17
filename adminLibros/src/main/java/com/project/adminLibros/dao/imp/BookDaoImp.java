package com.project.adminLibros.dao.imp;

import com.project.adminLibros.models.Book;
import com.project.adminLibros.dao.BookDao;
import com.project.adminLibros.utils.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

// import org.springframework.transaction.annotation.Transactional;

@Repository
public class BookDaoImp implements BookDao {

    private final String filePath = "adminLibros/books.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private StringUtil stringUtil = new StringUtil(); // Create an instance of StringUtil

    public List<Book> getAllBooks(String bookName, String author, Date releaseDate) {

        try {

            File file = new File(filePath);
            if (!file.exists()) {
                throw new IOException("No existe el archivo de libros");
            }

            List<Book> filteredBooks = new ArrayList<>();

            for (Book book : objectMapper.readValue(file, new TypeReference<List<Book>>() {
            })) {
                if ((bookName == null
                        || stringUtil.normalizeString(book.getBookName())
                                .contains(stringUtil.normalizeString(bookName)))
                        &&
                        (author == null || stringUtil.normalizeString(book.getAuthor())
                                .contains(stringUtil.normalizeString(author)))
                        &&
                        // !no busca bien
                        (releaseDate == null || releaseDate.equals(book.getReleaseDate()))) {
                    // System.out.println(releaseDate);
                    filteredBooks.add(book);
                }
            }

            if (filteredBooks.size() == 0) {
                throw new IOException("No se encontraron libros con los parametros ingresados");
            }
            ;

            return filteredBooks;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Book createBook(Book book) {

        ObjectMapper mapper = new ObjectMapper();

        try {

            File file = new File(filePath);
            if (!file.exists()) {
                throw new IOException("No existe el archivo de libros");
            }

            List<Book> books = new ArrayList<>();

            if (file.length() != 0) {
                books = mapper.readValue(file, new TypeReference<List<Book>>() {
                });
            }

            books.add(book);

            mapper.writeValue(file, books);

            return book;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateBook(long id, Book book) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {

            File file = new File(filePath);
            if (!file.exists()) {
                throw new IOException("No existe el archivo de libros");
            }

            List<Book> books = new ArrayList<>();

            if (file.length() != 0) {
                books = mapper.readValue(file, new TypeReference<List<Book>>() {
                });
            }

            // for (int i = 0; i < books.size(); i++) {
            // if (books.get(i).getId().equals(id)) {
            // books.set(i, book);
            // break;
            // }
            // }

            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getId().equals(id)) {
                    Book existingBook = books.get(i);
                    if (book.getBookName() != null) {
                        existingBook.setBookName(book.getBookName());
                    }
                    if (book.getAuthor() != null) {
                        existingBook.setAuthor(book.getAuthor());
                    }
                    if (book.getReleaseDate() != null) {
                        existingBook.setReleaseDate(book.getReleaseDate());
                    }
                    // Add more properties to update if needed
                    books.set(i, existingBook);
                    break;
                }
            }

            mapper.writeValue(file, books);

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteBook(String id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {

            File file = new File(filePath);
            if (!file.exists()) {
                throw new IOException("No existe el archivo de libros");
            }

            List<Book> books = new ArrayList<>();

            if (file.length() != 0) {
                books = mapper.readValue(file, new TypeReference<List<Book>>() {
                });
            }

            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getId().equals(Long.parseLong(id))) {
                    books.remove(i);
                    break;
                }
            }

            mapper.writeValue(file, books);

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
