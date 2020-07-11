package com.atguigu.jdbc;

import com.atguigu.bean.Customer;
import com.atguigu.jdbcutils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-22 7:12
 */
public class DbUtilsTest {
    private Connection connection;
    private QueryRunner queryRunner;
    private String sql;

    @Before
    public void before(){
        queryRunner = new QueryRunner();
        connection = JDBCUtils.getConnection();
    }

    @After
    public void after(){
        JDBCUtils.closeResource(connection);
    }



    @Test
    public void test2() throws SQLException {

        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JDBCUtils.getConnection();

        String sql = "delete from customers where id = ?";

        int update = queryRunner.update(connection, sql, 20);

        System.out.println("删除了" + update + "条数据");

        JDBCUtils.closeResource(connection);

    }

    @Test
    public void test1() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();

        Connection connection = JDBCUtils.getConnection();

        String sql = "insert into customers(name,email,birth) values (?,?,?)";

        queryRunner.update(connection,sql,"Tom","123413@email.com","1994-01-23");
    }

    @Test
    public void test3() throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JDBCUtils.getConnection();

        BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);

        String sql = "select name,email,birth from customers where id = ?";

        Customer query = queryRunner.query(connection, sql, handler, 22);
        System.out.println(query);

    }

    @Test
    public void test4() throws SQLException {
        sql = "select name,email,birth from customers where id < ?";
        BeanListHandler<Customer> beanListHandler = new BeanListHandler<>(Customer.class);
        List<Customer> query = queryRunner.query(connection, sql, beanListHandler, 12);

        query.forEach(System.out::println);
    }

    @Test
    public void test5() throws SQLException {
        sql = "select name,email,birth from customers where id = ?";
        ResultSetHandler<Customer> resultSetHandler = new ResultSetHandler<Customer>() {
            @Override
            public Customer handle(ResultSet resultSet) throws SQLException {
                if (resultSet.next()){
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    Date birth = resultSet.getDate("birth");

                    return new Customer(name,email,birth);
                }

                return null;
            }
        };

        Customer query = queryRunner.query(connection, sql, resultSetHandler, 10);
        System.out.println(query);


    }

    @Test
    public void test6() throws SQLException {
        sql = "select count(*) from customers";

        ScalarHandler<Integer> customerScalarHandler = new ScalarHandler<>();

        int query =  queryRunner.query(connection, sql, customerScalarHandler);

        System.out.println(query);

    }

    @Test
    public void test7() throws SQLException {
        sql = "select max(birth) from customers";
        ScalarHandler<Date> birth = new ScalarHandler<>();

        Date query = queryRunner.query(connection, sql, birth);
        System.out.println(query);
    }

    @Test
    public void test8() throws SQLException {
        sql = "insert into customers(name,email,birth) values(?,?,?)";
        Object[][] param = new Object[2][3];
        for (int i = 0; i < param.length; i++) {
            param[i][0] = "IronMan";
            param[i][1] = "aaa@gmail.com";
            param[i][2] = "1994-04-04";
        }
        queryRunner.batch(connection, sql, param);


    }
}
