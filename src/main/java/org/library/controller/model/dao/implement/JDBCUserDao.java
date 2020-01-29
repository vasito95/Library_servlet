package org.library.controller.model.dao.implement;

import org.library.controller.model.dao.UserDao;
import org.library.controller.model.entity.Role;
import org.library.controller.model.entity.User;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {
    String insert = "INSERT INTO user (username, email, phone_number, password, is_active) VALUES (?, ?, ?, ?, ?)";
    Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    public Optional<User> findByEmailAndPassword(String email, String pass) {
        String query = "SELECT * from user left join user_role on user.id=user_role.user_id where " +
                "user.email=? and user.password=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, pass);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(extractFromResultSet(resultSet));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByName(String name) {
        String query = "SELECT * from library.user left join library.user_role on user.id=user_role.user_id where user.username=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(extractFromResultSet(resultSet));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void create(User entity) {
        Long id= -1L;
        try (PreparedStatement st = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, entity.getUsername());
            st.setString(2, entity.getEmail());
            st.setString(3, entity.getPhoneNumber());
            st.setString(4, entity.getPassword());
            st.setBoolean(5, entity.getIsActive());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()){
                id=rs.getLong(1);
            }
            setUserRole(id,entity.getRoles().iterator().next());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void setUserRole(Long id, Role role){

        String query = "INSERT INTO user_role (user_id, roles) VALUES (?, ?)";
        try (PreparedStatement st = connection.prepareStatement(query)){
            st.setLong(1, id);
            st.setString(2, role.toString());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static User extractFromResultSet(ResultSet rs) throws SQLException {
        User result = new User();

        result.setId(rs.getLong("id"));
        result.setUsername(rs.getString("username"));
        result.setEmail(rs.getString("email"));
        result.setRoles(Collections.singleton(Role.valueOf(rs.getString("roles"))));
        System.out.println(result);
        return result;
    }

    @Override
    public void delete(Long id) {
        String delete = " delete from users where id=?";
        try (PreparedStatement st = connection.prepareStatement(delete)) {
            st.setLong(1, id);
            st.execute();
            System.out.println("Well done!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void close() throws Exception {

    }
}
