package org.brs.library.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brs.library.model.dao.BookDao;
import org.brs.library.model.entity.Book;
import org.brs.library.model.entity.Order;
import org.brs.library.model.entity.User;
import org.brs.library.persistance.ConnectionPool;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCBookDao implements BookDao {

    private static Logger LOG = LogManager.getLogger(JDBCBookDao.class);

    public static final String INSERT_BOOK = "INSERT INTO book (name, is_in_use, attribute) VALUES (?, ?, ?)";
    public static final String INSERT_AUTHOR_BY_BOOK_ID = "INSERT INTO author (book_id, authors) VALUES (?, ?)";
    public static final String UPDATE_BOOK_DO_RETURN = "UPDATE book SET user_id=?, in_use_by=?, is_in_use=? where book.id=? and book.user_id=?";
    public static final String UPDATE_BOOK_ASSIGN_TO_USER = "UPDATE book SET user_id=?, in_use_by=?, is_in_use=? where book.id=? and book.is_in_use=?";
    public static final String SELECT_ALL_FREE = "select * from book where book.is_in_use =?";
    public static final String SELECT_ALL_BOOKS_WITH_READERS = "select book.id, book.name, book.in_use_by, user.username from book inner join user on book.user_id=user.id";
    public static final String SELECT_ALL_BY_USER_ID = "select * from book where book.user_id =?";
    public static final String SELECT_BY_NAME = "SELECT * from book where name =?";
    public static final String SELECT_ALL_BOOKS = "select * from book";
    public static final String SELECT_AUTHORS_BY_BOOK_ID = "select authors from author where book_id=?";
    public static final String SELECT_ALL_BY_NAME_LIKE = "select * FROM book WHERE name like ?";
    public static final String SELECT_ALL_BY_ATTRIBUTE_LIKE = "select * FROM book WHERE attribute like ?";
    public static final String SELECT_ALL_BY_AUTHOR_LIKE = "select * FROM book WHERE id IN (SELECT book_id FROM author WHERE author.authors like ?)";
    public static final String DELETE_BOOK = "DELETE FROM book WHERE id=?";
    public static final String SELECT_BY_ID = "SELECT * FROM book WHERE id=?";
    public static final String UPDATE_BOOK = "UPDATE book SET name=?, attribute=? where id=?";
    public static final String DELETE_AUTHORS = "DELETE FROM author WHERE book_id=?";

    @Override
    public void create(Book entity) {
        final long id;
        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement st = connection.prepareStatement(INSERT_BOOK, Statement.RETURN_GENERATED_KEYS)) {
                LOG.error("1");
                connection.setAutoCommit(false);
                LOG.error("2");
                st.setString(1, entity.getName());
                st.setBoolean(2, entity.getIsInUse());
                st.setString(3, entity.getAttribute());
                st.executeUpdate();
                LOG.error("3");
                ResultSet rs = st.getGeneratedKeys();
                LOG.error("3.1");
                if (rs.next()) {
                    LOG.error("3.2");
                    id = rs.getLong(1);
                    for (String author : entity.getAuthors()) {
                        LOG.error(entity.getAuthors());
                        LOG.error("3.4");
                        insertAuthors(connection, id, author);
                        LOG.error("3.5");
                    }
                }
                LOG.error("4");
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                LOG.error(e.getMessage());
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage());
        }
    }

    @Override
    public void returnBook(Long id, Long userId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement st = connection.prepareStatement(UPDATE_BOOK_DO_RETURN)) {
            st.setString(1, null);
            st.setDate(2, null);
            st.setBoolean(3, false);
            st.setLong(4, id);
            st.setLong(5, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void assignUserToBook(Order order) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement st = connection.prepareStatement(UPDATE_BOOK_ASSIGN_TO_USER)) {
            st.setLong(1, order.getUserId());
            st.setDate(2, Date.valueOf(order.getDateTo()));
            st.setBoolean(3, true);
            st.setLong(4, order.getBookId());
            st.setBoolean(5, false);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public List<Book> findAllByAuthorName(String name) {
        return getBooks(name, SELECT_ALL_BY_AUTHOR_LIKE);
    }

    @Override
    public List<Book> findAllByNameLike(String name) {
        return getBooks(name, SELECT_ALL_BY_NAME_LIKE);
    }

    @Override
    public List<Book> findAllByAttributeLike(String attribute) {
        return getBooks(attribute, SELECT_ALL_BY_ATTRIBUTE_LIKE);
    }

    @Override
    public List<Book> findAllFreeBooks() {
        List<Book> resultList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_FREE)) {
            ps.setBoolean(1, false);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = extractFromResultSet(rs);
                book.setAuthors(findAuthorsById(book.getId()));
                resultList.add(book);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return resultList;
    }

    @Override
    public List<Book> findAllBooksWithReaders() {
        List<Book> resultList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_BOOKS_WITH_READERS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = extractWithUserFromResultSet(rs);
                resultList.add(book);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return resultList;
    }

    @Override
    public List<Book> findAllByUserId(Long id) {
        List<Book> resultList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_BY_USER_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = extractFromResultSet(rs);
                resultList.add(book);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return resultList;
    }

    @Override
    public Optional<Book> findBookByName(String name) {
        Optional<Book> book = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_BY_NAME)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                book = Optional.of(extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> resultList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_BOOKS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = extractFromResultSet(rs);
                book.setAuthors(findAuthorsById(book.getId()));
                resultList.add(book);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return resultList;
    }

    @Override
    public Optional<Book> findById(Long id) {
        Optional<Book> bookOptional = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = extractFromResultSet(rs);
                book.setAuthors(findAuthorsById(book.getId()));
                bookOptional = Optional.of(book);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return bookOptional;
    }

    @Override
    public void update(Book entity) {
        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement st = connection.prepareStatement(UPDATE_BOOK)) {
                connection.setAutoCommit(false);
                st.setString(1, entity.getName());
                st.setString(2, entity.getAttribute());
                st.setLong(3, entity.getId());
                st.executeUpdate();
                deleteAuthors(connection, entity.getId());
                for (String author : entity.getAuthors()) {
                    insertAuthors( connection, entity.getId(), author);
                }
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                LOG.error(e.getMessage());
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement st = connection.prepareStatement(DELETE_BOOK)) {
            st.setLong(1, id);
            deleteAuthors(connection,id);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
    }

    private void deleteAuthors(Connection connection, Long id) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(DELETE_AUTHORS)) {
            st.setLong(1, id);
            st.execute();
        }
    }

    private void insertAuthors(Connection connection, Long id, String author) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(INSERT_AUTHOR_BY_BOOK_ID)) {
            st.setLong(1, id);
            st.setString(2, author);
            st.execute();
        }
    }

    private List<String> findAuthorsById(Long id) {
        List<String> resultList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_AUTHORS_BY_BOOK_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String author = rs.getString("authors");
                resultList.add(author);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return resultList;
    }

    private List<Book> getBooks(String attribute, String query) {
        List<Book> resultList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, attribute);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = extractFromResultSet(rs);
                book.setAuthors(findAuthorsById(book.getId()));
                resultList.add(book);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
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
        result.setUser(User.builder().setUsername(rs.getString("username")).build());
        if (rs.getString("in_use_by") != null) {
            result.setInUseBy(LocalDate.parse(rs.getString("in_use_by")));
        }
        return result;
    }
}
