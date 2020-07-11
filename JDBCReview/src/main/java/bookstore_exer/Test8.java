package bookstore_exer;

import com.atguigu.jdbcutils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-25 2:09
 */
public class Test8 {

    @Test
    public void test() throws SQLException {
        Connection connection = JDBCUtils.getConnection();

        BeanHandler<Book> handler = new BeanHandler<>(Book.class);

        String sql = "select * from books where sales = (select max(sales) from books)";

        QueryRunner queryRunner = new QueryRunner();


        Book query = queryRunner.query(connection, sql, handler);
        System.out.println(query.toString());


        JDBCUtils.closeResource(connection);
    }
}
