package org.library.controller.model.dao.implement;

import org.library.controller.model.dao.BookDao;
import org.library.controller.model.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCBookDao implements BookDao {

    private static Connection connection;

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
                    "select * from book");
            while ( rs.next() ){
                Book book = extractFromResultSet(rs);
                resultList.add(book);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }
    static Book extractFromResultSet(ResultSet rs)
            throws SQLException {
        Book result = new Book();
        Long id = rs.getLong("id");
        result.setId(id);
        result.setName( rs.getString("name") );
        result.setIsInUse( rs.getBoolean("is_in_use"));
        result.setAuthors(findAuthorsById(id));
        result.setAttributes(findAttributeById(id));
        return result;
    }

    public static List<String> findAuthorsById(Long id){
        List<String> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select authors from author where book_id=?")){
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ){
                String author = rs.getString("authors");
                resultList.add(author);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }
    public static List<String> findAttributeById(Long id){
        List<String> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select attributes from attribute where book_id=?")){
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ){
                String attribute = rs.getString("attributes");
                resultList.add(attribute);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
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
