package org.library.controller.model.dao.implement;

import org.library.controller.model.dao.BookDao;
import org.library.controller.model.entity.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCBookDao implements BookDao {

    private Connection connection;

    public JDBCBookDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Book entity) {

    }

    @Override
    public Book findById(Long id) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        List<Book> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()){
            ResultSet rs = ps.executeQuery(
                    "select * from books ");
            while ( rs.next() ){
                Book car = extractFromResultSet(rs);
                resultList.add(car);
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
        result.setName( rs.getString("name") );
        result.setIsInUse( rs.getBoolean("is_in_use"));


        return result;
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
