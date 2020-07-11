package customerdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-24 20:28
 */
public class CustomerDAOImp extends DAO implements CustomerDAO {
    @Override
    public int add(Connection connection, Customer customer) throws SQLException {
        String sql = "insert into customers values (?,?,?,?)";
        int update = update(connection, sql, customer.getId(), customer.getName(), customer.getEmail(), customer.getBirth());
        return update;
    }

    @Override
    public int deleteById(Connection connection, Integer id) throws SQLException {
        String sql = "delete from customers where id = ?";
        int update = update(connection, sql, id);
        return update;
    }

    @Override
    public boolean update(Connection connection, Customer customer) throws SQLException {
        String sql = "update customers set name = ?,email = ?,birth = ? where id = ?";
        int update = update(connection, sql, customer.getName(), customer.getEmail(), customer.getBirth(), customer.getId());
        if (update!= 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Customer getCustomer(Connection connection, Integer id) throws SQLException {
        String sql = "select id,name,email,birth from customers where id = ?";
        Customer instance = getInstance(connection, sql, Customer.class, id);
        return instance;
    }

    @Override
    public List<Customer> getAll(Connection connection) throws SQLException {
        String sql = "select id,name,email,birth from customers";
        List<Customer> list = getList(connection, sql, Customer.class);
        return list;
    }

    @Override
    public Customer getMaxBirth(Connection connection) throws SQLException {
        String sql = "select id,name,email,birth from customers having max(birth)";
        Customer value = getValue(connection, sql, Customer.class);
        return value;
    }
}
