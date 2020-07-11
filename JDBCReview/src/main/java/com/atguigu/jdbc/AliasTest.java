package com.atguigu.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.atguigu.bean.OrderBean;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-24 20:18
 */
public class AliasTest {

    @Test
    public void testAlias() throws Exception {

        InputStream inputStream = DbUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();

        properties.load(inputStream);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();

        String sql = "select name ordername,email orderemail,birth orderDate from customers where id = ?";

        BeanHandler<OrderBean> orderBeanBeanHandler = new BeanHandler<>(OrderBean.class);

        QueryRunner queryRunner = new QueryRunner();

        OrderBean query = queryRunner.query(connection, sql, orderBeanBeanHandler, 10);

        System.out.println(query);


    }
}
