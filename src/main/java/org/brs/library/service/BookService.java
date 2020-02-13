package org.brs.library.service;

import org.brs.library.model.dao.BookDao;
import org.brs.library.model.entity.Book;
import org.brs.library.model.entity.Order;

import java.util.List;
import java.util.Optional;

public class BookService {
    private BookDao dao;

    public BookService(BookDao dao) {
        this.dao = dao;
    }

    public void returnBook(Long bookId, Long userId) {
        this.dao.returnBook(bookId, userId);
    }

    public List<Book> findAll() {
        return this.dao.findAll();
    }

    public List<Book> findAllByNameLike(String name) {
        return this.dao.findAllByNameLike(name);
    }

    public List<Book> findAllByAttributeLike(String attribute) {
        return this.dao.findAllByAttributeLike(attribute);
    }

    public List<Book> findAllByAuthorName(String author) {
        return this.dao.findAllByAuthorName(author);
    }

    public List<Book> findAllFreeBooks() {
        return dao.findAllFreeBooks();
    }

    public List<Book> findAllByUserId(Long id) {
        return this.dao.findAllByUserId(id);
    }

    public Optional<Book> findBookByName(String name) {
        return this.dao.findBookByName(name);
    }

    public List<Book> findAllBooksWithReaders() {
        return this.dao.findAllBooksWithReaders();
    }

    public void assignUserToBook(Order order) {
        this.dao.assignUserToBook(order);
    }

    public void save(Book book) {
        this.dao.create(book);
    }

    public void update(Book book){
        this.dao.update(book);
    }

    public void delete(Long id){
        this.dao.delete(id);
    }

    public Optional<Book> findById(Long id){
        return this.dao.findById(id);
    }
}
