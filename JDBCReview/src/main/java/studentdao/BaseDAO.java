package studentdao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-25 0:06
 */
public abstract class BaseDAO {

    private QueryRunner queryRunner = new QueryRunner();

    //增删改
    public int update(Connection connection,String sql,Object...args) throws SQLException {
        int update = queryRunner.update(connection, sql, args);
        return update;
    }

    //查
    public <T> T getInstance(Connection connection,String sql,Class<T> clazz, Object...args) throws SQLException {
        BeanHandler<T> handler = new BeanHandler<>(clazz);
        T query = queryRunner.query(connection, sql, handler, args);

        return query;
    }
}
