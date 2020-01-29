package org.library.controller.model.dao.implement;

import org.library.controller.model.dao.BookDao;
import org.library.controller.model.dao.DaoFactory;
import org.library.controller.model.dao.UserDao;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dateSource;

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/library",
                    "root" ,
                    "root" );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }
    @Override
    public BookDao createBookDao() {
        return new JDBCBookDao(getConnection());
    }
}
