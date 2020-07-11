package bookstore_exer;

import com.atguigu.jdbcutils.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-25 1:59
 */
public class Test7 {

    @Test
    public void test() throws SQLException {
        Connection connection = JDBCUtils.getConnection();

        BeanListHandler<Book> handler = new BeanListHandler<>(Book.class);

        String sql = "select `id`,`title`,`author`,`price`,`sales`,`stock`,`img_path` from books";

        QueryRunner queryRunner = new QueryRunner();

        List<Book> query = queryRunner.query(connection,sql,handler);

        query.forEach(System.out::println);

        JDBCUtils.closeResource(connection);
    }
}
