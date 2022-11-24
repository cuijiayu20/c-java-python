package JDBCTest;

import javax.sql.DataSource;

public class test {
    public static void main(String[] args) {
        try {
            C3p0Utils a= new C3p0Utils();
            DataSource x = a.getDataSoure();
            System.out.print("true");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print("false");
        }
    }
}
