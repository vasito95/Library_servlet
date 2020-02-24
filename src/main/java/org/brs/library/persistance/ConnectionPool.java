package org.brs.library.persistance;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ConnectionPool {

    private static DataSource source;

    static {
        ResourceBundle rb = ResourceBundle.getBundle("database");
        PoolProperties properties = new PoolProperties();
        properties.setUrl(rb.getString("database.url"));
        properties.setDriverClassName(rb.getString("database.driver"));
        properties.setMaxIdle(Integer.parseInt(rb.getString("database.maxIdle")));
        properties.setMaxActive(Integer.parseInt("database.maxActive"));
        properties.setUsername("root");
        properties.setPassword("root");
        source = new DataSource(properties);
    }

    public static Connection getConnection() throws SQLException {
        return source.getConnection();
    }
}
