package customerdao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-24 20:28
 */
public class CustomerTest {
    private Connection connection = null;
    @Before
    public void before(){
        connection = MyUtils.getConnection();
    }

    @After
    public void after(){
        try {
            if (!connection.isClosed()){
                MyUtils.close(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = simpleDateFormat.parse("1994-07-07");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Customer customer = new Customer(41,"IronMan","IronMan@gmail.com",new Date(date.getTime()));

        CustomerDAOImp customerDAOImp = new CustomerDAOImp();
        try {
            int add = customerDAOImp.add(connection, customer);
            System.out.println("增加了" + add + "条记录");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetMaxBirth(){

        CustomerDAOImp customerDAOImp = new CustomerDAOImp();
        try {
            Customer maxBirth = customerDAOImp.getMaxBirth(connection);
            System.out.println(maxBirth.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
