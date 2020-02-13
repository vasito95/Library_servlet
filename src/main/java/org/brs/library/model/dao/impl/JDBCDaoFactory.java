package org.brs.library.model.dao.impl;

import org.brs.library.model.dao.BookDao;
import org.brs.library.model.dao.DaoFactory;
import org.brs.library.model.dao.OrderDao;
import org.brs.library.model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dateSource;

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/library",
                    "root",
                    "root");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderDao createOrderDao() {
        return new JDBCOrderDao();
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao();
    }

    @Override
    public BookDao createBookDao() {
        return new JDBCBookDao();
    }
}
