package bookstore_exer;

import com.atguigu.jdbcutils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-25 2:13
 */
public class Test9 {

    @Test
    public void test() throws SQLException {
        Connection connection = JDBCUtils.getConnection();


        String sql = "update books set stock = 100 where stock < ?";

        QueryRunner queryRunner = new QueryRunner();

        int update = queryRunner.update(connection, sql, 100);

        System.out.println(update> 0?"成功":"失败");


        JDBCUtils.closeResource(connection);

    }
}
