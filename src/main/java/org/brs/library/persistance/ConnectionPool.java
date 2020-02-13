package org.brs.library.persistance;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {

    private static DataSource source;

    static {
        PoolProperties properties = new PoolProperties();
        properties.setUrl("jdbc:mysql://localhost:3306/library");
        properties.setDriverClassName("com.mysql.cj.jdbc.Driver");
        properties.setUsername("root");
        properties.setPassword("root");
        source = new DataSource(properties);
    }

    public static Connection getConnection() throws SQLException {
        return source.getConnection();
    }
}
