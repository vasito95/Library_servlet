package org.library.controller.model.dao;

import org.library.controller.model.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao extends GenericDao<Book> {
    List<Book> findAllByUserId(Long id);
    Optional<Book> findBookByName(String name);
    List<Book> findAllBooksWithReaders();
}
