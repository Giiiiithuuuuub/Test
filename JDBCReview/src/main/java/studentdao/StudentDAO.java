package studentdao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-25 0:07
 */
public interface StudentDAO {

    int addStudent(Connection connection);


    int deleteByExamCard(Connection connection);

    void getStudent(Connection connection);

}
