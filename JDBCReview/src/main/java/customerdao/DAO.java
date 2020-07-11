package customerdao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-24 20:27
 */
public abstract class DAO {

    private QueryRunner queryRunner = new QueryRunner();

    //增删改

    public int update(Connection conn , String sql,Object...args) throws SQLException {
        int update = queryRunner.update(conn, sql, args);
        return update;
    }

    //查一条记录
    public <T> T getInstance(Connection conn,String sql,Class<T> clazz, Object...args) throws SQLException {
        BeanHandler<T> handler = new BeanHandler<>(clazz);
        T instance = queryRunner.query(conn, sql, handler, args);
        return instance;
    }

    //查询多条记录
    public <T> List<T> getList(Connection conn, String sql,Class<T> clazz,Object...args) throws SQLException {
        BeanListHandler<T> beanListHandler = new BeanListHandler<>(clazz);
        List<T> query = queryRunner.query(conn, sql, beanListHandler, args);
        return query;
    }

    //查询特定值
    public <T> T getValue(Connection conn,String sql,Class<T> clazz,Object...args) throws SQLException {
        ScalarHandler<T> handler = new ScalarHandler<>();
        T query = (T)queryRunner.query(conn, sql, handler, args);
        return query;
    }
}
