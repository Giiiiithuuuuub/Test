
import java.sql.*;
import java.util.Properties;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-06-22 14:14
 */
public class Test1 {

    public static void main(String[] args) throws SQLException {

//        String url = ThinClientUtil.getConnectionUrl("localhost108", 8765);
//
//        Connection connection = DriverManager.getConnection(url);
//
//        PreparedStatement statement = connection.prepareStatement("select * from \"test\"");
//
//        ResultSet resultSet = statement.executeQuery();
//
//        while (resultSet.next()){
//            System.out.println(resultSet.getString(1) + ":" + resultSet.getString(2));
//        }

        String url = "jdbc:phoenix:localhost108,localhost109,localhost110:2181";

        Properties properties = new Properties();
        properties.setProperty("phoenix.schema.isNamespaceMappingEnabled","true");

        Connection connection = DriverManager.getConnection(url,properties);

        PreparedStatement statement = connection.prepareStatement("select * from \"test\"");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + ":" + resultSet.getString(2));
        }

    }
}
