package org.brs.library.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brs.library.model.dao.OrderDao;
import org.brs.library.model.entity.Order;
import org.brs.library.persistance.ConnectionPool;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCOrderDao implements OrderDao {

    private static Logger LOG = LogManager.getLogger(JDBCOrderDao.class);

    public static final String INSERT_ORDER = "INSERT INTO book_order (book_id, book_name, date_to, user_name, user_id) VALUES (?, ?, ?, ?, ?)";
    public static final String DELETE_ALL_ORDERS = "DELETE FROM book_order where id=?";
    public static final String SELECT_ORDER_BY_ID = "SELECT * FROM book_order WHERE id=?";
    public static final String SELECT_ALL_ORDERS = "SELECT * FROM book_order limit ?,?";
    public static final String GET_ORDERS_COUNT = "SELECT COUNT(*) as numberOfOrders FROM book_order";

    @Override
    public void create(Order entity) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement st = connection.prepareStatement(INSERT_ORDER)) {
            st.setLong(1, entity.getBookId());
            st.setString(2, entity.getBookName());
            st.setDate(3, Date.valueOf(entity.getDateTo()));
            st.setString(4, entity.getUserName());
            st.setLong(5, entity.getUserId());
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }

    public List<Order> findAll(long numberOfItems, long offset) {
        List<Order> resultList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ORDERS)) {
            ps.setLong(1, offset);
            ps.setLong(2, numberOfItems);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = extractFromResultSet(rs);
                resultList.add(order);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return resultList;
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement st = connection.prepareStatement(DELETE_ALL_ORDERS)) {
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
    }

    static Order extractFromResultSet(ResultSet rs) throws SQLException {
        Order result = new Order();
        result.setId(rs.getLong("id"));
        result.setBookId(rs.getLong("book_id"));
        result.setBookName(rs.getString("book_name"));
        result.setDateTo(LocalDate.parse(rs.getString("date_to")));
        result.setUserName(rs.getString("user_name"));
        result.setUserId(rs.getLong("user_id"));
        return result;
    }

    @Override
    public Optional<Order> findById(Long id) {
        Optional<Order> order = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                order = Optional.of(extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return order;
    }

    public long getNumberOfOrders() {
        Long numberOfOrders = 0L;
        try (Connection connection = ConnectionPool.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(GET_ORDERS_COUNT);
            while (rs.next()) {
                numberOfOrders = Long.parseLong(rs.getString("numberOfOrders"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return numberOfOrders;
    }

    @Override
    public void close() throws Exception {

    }
}
