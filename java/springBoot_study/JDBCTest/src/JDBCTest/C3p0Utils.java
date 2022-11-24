package JDBCTest;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class C3p0Utils {
    private static DataSource ds;
    static{
        ds= new ComboPooledDataSource();
    }
    public DataSource getDataSoure(){
        return ds;
    }
}
