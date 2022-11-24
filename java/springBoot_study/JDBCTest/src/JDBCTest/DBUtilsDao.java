package JDBCTest;

import com.mysql.jdbc.Driver;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class DBUtilsDao {
    public List findall() throws SQLException {
        QueryRunner run = new QueryRunner(new C3p0Utils().getDataSoure());
        String sql = "select * from user";
        List list= (List) run.query(sql,new BeanListHandler(UserBean.class));
        return list;
    }
    public UserBean find(int id) throws SQLException{
        QueryRunner run = new QueryRunner(new C3p0Utils().getDataSoure());
        String sql = "select * from user where id = ?";
        UserBean userBean = (UserBean) run.query(sql, new BeanHandler(UserBean.class),new Object[]{id});
        return userBean;
    }
}
