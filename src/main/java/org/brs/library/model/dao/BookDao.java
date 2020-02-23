package org.brs.library.model.dao;

import org.brs.library.model.entity.Book;
import org.brs.library.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface BookDao extends GenericDao<Book> {
    List<Book> findAllBooksWithReaders();
    List<Book> findAllByAttributeLike(String attribute);
    List<Book> findAllByAuthorName(String author);
    List<Book> findAllByNameLike(String name);
    List<Book> findAllFreeBooks();
    List<Book> findAllByUserId(Long id);
    List<Book> findAll();
    Optional<Book> findBookByName(String name);
    Optional<Book> findById(Long id);
    void assignUserToBook(Order order);
    void returnBook(Long id, Long userId);
    void update(Book entity);
    void delete(Long id);
}
