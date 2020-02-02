package org.library.controller.model.dao.implement;

import org.library.controller.model.dao.BookDao;
import org.library.controller.model.entity.Book;
import org.library.controller.model.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCBookDao implements BookDao {

    private Connection connection;

    public JDBCBookDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Book entity) {
        final Long id;
        String query = "INSERT INTO book (name, is_in_use, attribute) VALUES (?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, entity.getName());
            st.setBoolean(2, entity.getIsInUse());
            st.setString(3, entity.getAttribute());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
                entity.getAuthors().forEach(s -> insertAuthors(id, s));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void insertAuthors(Long id, String author) {
        String query = "INSERT INTO author (book_id, authors) VALUES (?, ?)";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);
            st.setString(2, author);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> findAllBooksWithReaders(){
        String query = "select book.id, book.name, book.in_use_by, user.username from book inner join user on book.user_id=user.id";
        List<Book> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {
                Book book = extractWithUserFromResultSet(rs);
                resultList.add(book);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    public List<Book> findAllByUserId(Long id) {
        String query = "select * from book where book.user_id =?";
        List<Book> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = extractFromResultSet(rs);
                resultList.add(book);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public Optional<Book> findBookByName(String name) {
        Optional<Book> book = Optional.empty();
        String query = "SELECT * from book where name =?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                book = Optional.of(extractFromResultSet(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(
                    "select * from book");
            while (rs.next()) {
                Book book = extractFromResultSet(rs);
                book.setAuthors(findAuthorsById(book.getId()));
                resultList.add(book);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    public List<String> findAuthorsById(Long id) {
        List<String> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select authors from author where book_id=?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String author = rs.getString("authors");
                resultList.add(author);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    static Book extractFromResultSet(ResultSet rs)
            throws SQLException {
        Book result = new Book();
        result.setId(rs.getLong("id"));
        result.setName(rs.getString("name"));
        result.setIsInUse(rs.getBoolean("is_in_use"));
        if (rs.getString("in_use_by") != null) {
            result.setInUseBy(LocalDate.parse(rs.getString("in_use_by")));
        }
        result.setAttribute(rs.getString("attribute"));
        return result;
    }

    static Book extractWithUserFromResultSet(ResultSet rs)
            throws SQLException {
        Book result = new Book();
        result.setId(rs.getLong("id"));
        result.setName(rs.getString("name"));
        result.setUser(User.builder().username(rs.getString("username")).build());
        if (rs.getString("in_use_by") != null) {
            result.setInUseBy(LocalDate.parse(rs.getString("in_use_by")));
        }
        return result;
    }

    @Override
    public Book findById(Long id) {
        return null;
    }
    @Override
    public void update(Book entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
