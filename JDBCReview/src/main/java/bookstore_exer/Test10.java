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
import java.util.Scanner;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-25 2:16
 */
public class Test10 {
    @Test
    public void test() throws SQLException {
        Connection connection = JDBCUtils.getConnection();

        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入用户名：");
        String name = scanner.next();

        String sql = "select * from order_items join orders on orders.id = order_items.order_id where user_id = (select id from orders where user_id = (select id from users where username = ?))";
        QueryRunner queryRunner = new QueryRunner();

        BeanListHandler<Order> handler = new BeanListHandler<>(Order.class);

        List<Order> query = queryRunner.query(connection, sql, handler, name);


query.forEach(System.out::println);


        JDBCUtils.closeResource(connection);
    }
}
