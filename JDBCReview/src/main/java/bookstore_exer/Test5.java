package bookstore_exer;

import com.atguigu.jdbcutils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-25 1:47
 */
public class Test5 {

    @Test
    public void test() throws SQLException {
        Connection connection = JDBCUtils.getConnection();

        QueryRunner queryRunner = new QueryRunner();

        String sql = "insert into books values(null,?,?,?,?,?,?)";
        queryRunner.update(connection,sql,"从入门到放弃","于海峰",99.8,12,34,"upload/books/从入门到放弃.jpg");

        JDBCUtils.closeResource(connection);
    }
}
