package org.brs.library.model.dao;

import org.brs.library.model.entity.Book;
import org.brs.library.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface BookDao extends GenericDao<Book> {
    List<Book> findAllByUserId(Long id);
    Optional<Book> findBookByName(String name);
    List<Book> findAllBooksWithReaders();
    void assignUserToBook(Order order);
    void returnBook(Long id, Long userId);
    List<Book> findAllFreeBooks();
    List<Book> findAllByNameLike(String name);
    List<Book> findAllByAuthorName(String author);
    List<Book> findAllByAttributeLike(String attribute);
    void update(Book entity);
    Optional<Book> findById(Long id);
    List<Book> findAll();
}
