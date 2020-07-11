package com.atguigu.primitive;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-24 16:07
 */
public class PrimitiveTest {

    @Test
    public void test() {
        //采用原始方式获取数据库连接
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1231");

            //设置事务
            connection.setAutoCommit(false);

            //编写sql
            String sql = "insert into customers(name,email,birth) values(?,?,?)";

            //调用插入方法
            update(connection,sql,"于海峰","IronMan@gmail.com","1994-07-07");

            connection.commit();
        } catch (Exception e) {
            if (connection != null){
                try {
                    //出现异常，回滚事务
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (connection != null){

                try {
                    //将连接还原为自动提交，特别是使用连接池，最好还原为自动提交
                    connection.setAutoCommit(true);
                    //关闭资源
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    //使用原始方式封装的增删改操作
    public void update(Connection connection,String sql,Object...args){
        //预编译sql
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);

            //填充占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1,args[i]);
            }

            //执行sql
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
