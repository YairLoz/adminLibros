package com.project.adminLibros.dao.imp;

import com.project.adminLibros.models.Book;
import com.project.adminLibros.dao.BookDao;
import com.project.adminLibros.utils.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@Repository
public class BookDaoImp implements BookDao {

    private final String filePath = "adminLibros/books.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private StringUtil stringUtil = new StringUtil();

    @Override
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
                        (releaseDate == null || releaseDate.equals(book.getReleaseDate()))) {
                    filteredBooks.add(book);
                }
            }

            if (filteredBooks.size() == 0) {
                return new ArrayList<>();
            }

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
    public Book updateBook(long id, Book book) {

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

            boolean updated = false;

            for (int i = 0; i < books.size(); i++) {
                Book currentBook = books.get(i);
                Long currentBookId = currentBook.getId();
                if (currentBookId != null && currentBookId == id) {
                    if (book.getBookName() != null) {
                        currentBook.setBookName(book.getBookName());
                    }
                    if (book.getAuthor() != null) {
                        currentBook.setAuthor(book.getAuthor());
                    }
                    if (book.getReleaseDate() != null) {
                        currentBook.setReleaseDate(book.getReleaseDate());
                    }
                    books.set(i, currentBook);
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                return null;
                // throw new NoSuchElementException();
            }

            mapper.writeValue(file, books);

            return book;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String deleteBook(String bookName, String author, Date releaseDate) {
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

            boolean deleted = false;

            for (int i = 0; i < books.size(); i++) {
                Book currentBook = books.get(i);
                if ((bookName == null || bookName.equals(currentBook.getBookName()))
                        && (author == null || author.equals(currentBook.getAuthor()))
                        && (releaseDate == null || releaseDate.equals(currentBook.getReleaseDate()))) {
                    books.remove(i);
                    deleted = true;
                    break;
                }
            }

            if (!deleted) {
                throw new NoSuchElementException();
            }

            mapper.writeValue(file, books);

            return "Libro eliminado exitosamente";

        } catch (IOException e) {
            e.printStackTrace();
            return "No se pudo eliminar el libro";

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return "No se encontró el libro con el ID proporcionado";
        }
    }

}
