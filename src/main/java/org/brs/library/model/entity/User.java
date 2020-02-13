package org.brs.library.model.entity;

import java.util.Set;

public class User {

    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private Boolean isActive;
    private Set<Role> roles;
    private Set<Book> books;

    public User() {
    }

    public User(String username, String email, String phoneNumber, String password, Boolean isActive, Set<Role> roles, Set<Book> books) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.isActive = isActive;
        this.roles = roles;
        this.books = books;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String username;
        private String email;
        private String phoneNumber;
        private String password;
        private Boolean isActive;
        private Set<Role> roles;
        private Set<Book> books;

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setIsActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public UserBuilder setRoles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public UserBuilder serPassword(Set<Book> books) {
            this.books = books;
            return this;
        }

        public User build() {
            return new User(username, email, phoneNumber, password, isActive, roles, books);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
