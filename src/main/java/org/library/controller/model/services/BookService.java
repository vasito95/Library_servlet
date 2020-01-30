package org.library.controller.model.services;

import org.library.controller.model.dao.BookDao;
import org.library.controller.model.entity.Book;

import java.util.List;

public class BookService {
    BookDao dao;

    public BookService(BookDao dao) {
        this.dao = dao;
    }


    public List<Book> findAll(){
        return dao.findAll();
    }
}
