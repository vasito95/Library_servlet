package org.library.controller.model.dao.implement;
import org.library.controller.model.dao.OrderDao;
import org.library.controller.model.entity.Order;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JDBCOrderDao implements OrderDao {

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;

    @Override
    public void create(Order entity) {
        String query = "INSERT INTO book_order (book_id, book_name, date_to, user_name, usr_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(query)){
            st.setLong(1, entity.getBookId());
            st.setString(2, entity.getBookName());
            st.setDate(3, Date.valueOf(entity.getDateTo()));
            st.setString(4, entity.getUserName());
            st.setLong(5, entity.getUsrId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> findAll() {
        List<Order> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(
                    "select * from book_order");
            while (rs.next()) {
                Order order = extractFromResultSet(rs);
                resultList.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    static Order extractFromResultSet(ResultSet rs) throws SQLException{
        Order result = new Order();
        result.setId(rs.getLong("id"));
        result.setBookId(rs.getLong("book_id"));
        result.setBookName(rs.getString("book_name"));
        result.setDateTo(LocalDate.parse(rs.getString("date_to")));
        result.setUserName(rs.getString("user_name"));
        result.setUsrId(rs.getLong("usr_id"));
        return result;
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM book_order where id=?";
        try (PreparedStatement st = connection.prepareStatement(query)){
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void acceptOrder(Long id) {
        String query = "UPDATE ";
        try (PreparedStatement st = connection.prepareStatement(query)){
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
