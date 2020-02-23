package org.brs.library.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brs.library.exception.OperationFailedException;
import org.brs.library.model.dao.UserDao;
import org.brs.library.model.entity.Role;
import org.brs.library.model.entity.User;
import org.brs.library.persistance.ConnectionPool;

import java.sql.*;
import java.util.Optional;

public class JDBCUserDao implements UserDao {

    private static Logger LOG = LogManager.getLogger(JDBCUserDao.class);

    public static final String INSERT_NEW_USER = "INSERT INTO user (username, email, phone_number, password, is_active, role) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String INSERT_USER_ROLE = "INSERT INTO user_role (user_id, roles) VALUES (?, ?)";
    public static final String SELECT_ALL_USERS_WITH_ROLES = "SELECT * from user where " +
            "user.email=? and user.password=?";
    public static final String DELETE_USER_BY_ID = " delete from user where id=?";
    public static final String SELECT_BY_NAME = "SELECT * from user left join library.user_role on user.id=user_role.user_id where user.username=?";

    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS_WITH_ROLES)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void create(User entity) throws OperationFailedException {
        long id = -1L;

        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
                connection.setAutoCommit(false);
                ps.setString(1, entity.getUsername());
                ps.setString(2, entity.getEmail());
                ps.setString(3, entity.getPhoneNumber());
                ps.setString(4, entity.getPassword());
                ps.setBoolean(5, entity.getActive());
                ps.setString(6, entity.getRole().name());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getLong(1);
                }
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                throw new OperationFailedException(e);
            }
        } catch (SQLException e) {
            throw new OperationFailedException(e);
        }
    }

    static User extractFromResultSet(ResultSet rs) throws SQLException {
        User result = new User();
        result.setId(rs.getLong("id"));
        result.setUsername(rs.getString("username"));
        result.setEmail(rs.getString("email"));
        result.setRole(Role.valueOf(rs.getString("role")));
        return result;
    }

    @Override
    public void close() throws Exception {
    }
}
