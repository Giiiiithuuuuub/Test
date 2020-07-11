package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-21 22:12
 */
public class ConnectionTest {
    @Test
    public void test4() throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.jdbc.Driver";
        String usernaem = "root";
        String password = "1231";
        String url = "jdbc:mysql://localhost:3306/test";

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, usernaem, password);

        System.out.println(connection);

        connection.close();
    }

    @Test
    public void test1() throws IOException, ClassNotFoundException, SQLException {

        InputStream resourceAsStream = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);

        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverName = properties.getProperty("driverName");

        Class.forName(driverName);

        Connection connection = DriverManager.getConnection(url, username, password);

        System.out.println(connection);
    }

    @Test
    public void test2() throws Exception {

        Properties properties = new Properties();
        properties.load(ConnectionTest.class.getClassLoader().getResourceAsStream("druid.properties"));

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        Connection connection = dataSource.getConnection();
        System.out.println(connection);

    }

    @Test
    public void test3() throws SQLException {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setPassword("1231");
        dataSource.setUsername("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(100);
        dataSource.setMaxWait(1000);
        dataSource.setFilters("wall");

        DruidPooledConnection connection = dataSource.getConnection();
        System.out.println(connection);
    }

}
