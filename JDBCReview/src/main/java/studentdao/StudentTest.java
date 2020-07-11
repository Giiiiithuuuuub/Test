package studentdao;

import com.atguigu.jdbcutils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-25 0:07
 */
public class StudentTest {
    @Test
    public void testAdd(){
        Connection connection = JDBCUtils.getConnection();

        StudentDAOImp studentDAOImp = new StudentDAOImp();
        studentDAOImp.addStudent(connection);

        JDBCUtils.closeResource(connection);
    }

    @Test
    public void testSelect(){
        Connection connection = JDBCUtils.getConnection();

        StudentDAOImp studentDAOImp = new StudentDAOImp();

        studentDAOImp.getStudent(connection);

        JDBCUtils.closeResource(connection);
    }

    @Test
    public void testDelete(){
        Connection connection = JDBCUtils.getConnection();

        StudentDAOImp studentDAOImp = new StudentDAOImp();

        studentDAOImp.deleteByExamCard(connection);

        JDBCUtils.closeResource(connection);
    }
}
