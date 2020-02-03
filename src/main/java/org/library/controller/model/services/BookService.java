package org.library.controller.model.services;

import org.library.controller.model.dao.BookDao;
import org.library.controller.model.entity.Book;
import org.library.controller.model.entity.Order;

import java.util.List;
import java.util.Optional;

public class BookService {
    private BookDao dao;

    public BookService(BookDao dao) {
        this.dao = dao;
    }

    public void returnBook(Long bookId, Long userId){
        this.dao.returnBook(bookId,userId);
    }

    public List<Book> findAll(){
        return this.dao.findAll();
    }

    public List<Book> findAllFreeBooks(){
        return dao.findAllFreeBooks();
    }

    public List<Book> findAllByUserId(Long id){
        return this.dao.findAllByUserId(id);
    }

    public Optional<Book> findBookByName(String name){
        return this.dao.findBookByName(name);
    }

    public List<Book> findAllBooksWithReaders(){
        return this.dao.findAllBooksWithReaders();
    }

    public void assignUserToBook(Order order){
        this.dao.assignUserToBook(order);
    }

    public void save(Book book){
        this.dao.create(book);
    }
}
