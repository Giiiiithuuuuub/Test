package customerdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-24 20:27
 */
public interface CustomerDAO {

    //增
    int add(Connection connection,Customer customer) throws SQLException;

    //删
    int deleteById(Connection connection,Integer id) throws SQLException;

    //改
    boolean update(Connection connection,Customer customer) throws SQLException;

    //查
    Customer getCustomer(Connection connection,Integer id) throws SQLException;

    //查询全部
    List<Customer> getAll(Connection connection) throws SQLException;
    //查询最大生日
    Customer getMaxBirth(Connection connection) throws SQLException;
}
