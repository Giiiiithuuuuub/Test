package bookstore_exer;

import com.atguigu.jdbcutils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-25 1:37
 */
public class Test4 {
    
    @Test
    public void test() {
        Connection connection = JDBCUtils.getConnection();

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入用户名：");
        String username = scanner.next();

        System.out.println("请输入密码：");
        String password = scanner.next();

        System.out.println("请输入邮箱：");
        String eamil = scanner.next();


        String sql =  "insert into users values (null,?,password(?),?)";

        QueryRunner queryRunner = new QueryRunner();

        try {
            int update = queryRunner.update(connection, sql, username, password,eamil);
            if (update != 0){
                System.out.println("添加成功");
            }else{
                System.out.println("添加失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection);
        }


    }
}
