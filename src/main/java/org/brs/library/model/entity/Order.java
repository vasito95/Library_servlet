package org.brs.library.model.entity;

import java.time.LocalDate;

public class Order {

    private Long id;
    private Long userId;
    private Long bookId;
    private String userName;
    private String bookName;
    private LocalDate dateTo;

    public Order() {
    }

    public Order(Long userId, Long bookId, String userName, String bookName, LocalDate dateTo) {
        this.userId = userId;
        this.bookId = bookId;
        this.userName = userName;
        this.bookName = bookName;
        this.dateTo = dateTo;
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public static class OrderBuilder {
        private Long userId;
        private Long bookId;
        private String userName;
        private String bookName;
        private LocalDate dateTo;

        public OrderBuilder setBookId(final Long bookId) {
            this.bookId = bookId;
            return this;
        }

        public OrderBuilder setUserId(final Long userId) {
            this.userId = userId;
            return this;
        }

        public OrderBuilder setUserName(final String userName) {
            this.userName = userName;
            return this;
        }

        public OrderBuilder setBookName(final String bookName) {
            this.bookName = bookName;
            return this;
        }

        public OrderBuilder setDateTo(final LocalDate dateTo) {
            this.dateTo = dateTo;
            return this;
        }

        public Order build() {
            return new Order(userId, bookId, userName, bookName, dateTo);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
