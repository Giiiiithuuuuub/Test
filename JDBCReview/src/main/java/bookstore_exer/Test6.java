package bookstore_exer;

import com.atguigu.jdbcutils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-25 1:51
 */
public class Test6 {

    @Test
    public void test() throws SQLException {
        Connection connection = JDBCUtils.getConnection();

        Scanner scanner = new Scanner(System.in);

        System.out.println("请登录");

        System.out.print("请输入用户名：");
        String name = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();

        String sql = "select count(*) from users where username = ? and password = password(?)";
        QueryRunner queryRunner = new QueryRunner();

        ScalarHandler<Long> objectScalarHandler = new ScalarHandler<>();
        Long result = queryRunner.query(connection, sql, objectScalarHandler, name, password);

        if (result != 0){

            System.out.print("登录成功");
        }else {
            System.out.println("登录失败，用户名或密码错误");
        }

        JDBCUtils.closeResource(connection);

    }
}
