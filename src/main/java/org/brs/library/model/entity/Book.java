package org.brs.library.model.entity;

import java.time.LocalDate;
import java.util.List;

public class Book {
    private Long id;
    private LocalDate inUseBy;
    private Boolean isInUse;
    private String name;
    private List<String> authors;
    private String attribute;
    private User user;

    public Book() {
    }

    public Book(Long id, LocalDate inUseBy, Boolean isInUse, String name, List<String> authors, String attribute, User user) {
        this.inUseBy = inUseBy;
        this.isInUse = isInUse;
        this.name = name;
        this.authors = authors;
        this.attribute = attribute;
        this.user = user;
        this.id = id;
    }

    public static BookBuilder builder() {
        return new BookBuilder();
    }

    public static class BookBuilder {

        private Long id;
        private LocalDate inUseBy;
        private Boolean isInUse;
        private String name;
        private List<String> authors;
        private String attribute;
        private User user;

        public BookBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public BookBuilder setInUseBy(LocalDate inUseBy) {
            this.inUseBy = inUseBy;
            return this;
        }

        public BookBuilder setIsInUse(Boolean isInUse) {
            this.isInUse = isInUse;
            return this;
        }

        public BookBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public BookBuilder setAuthors(List<String> authors) {
            this.authors = authors;
            return this;
        }

        public BookBuilder setAttribute(String attribute) {
            this.attribute = attribute;
            return this;
        }

        public BookBuilder User(User user) {
            this.user = user;
            return this;
        }

        public Book build() {
            return new Book(id, inUseBy, isInUse, name, authors, attribute, user);
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getInUseBy() {
        return inUseBy;
    }

    public void setInUseBy(LocalDate inUseBy) {
        this.inUseBy = inUseBy;
    }

    public Boolean getIsInUse() {
        return isInUse;
    }

    public void setIsInUse(Boolean inUse) {
        isInUse = inUse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
