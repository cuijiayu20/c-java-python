package com.util;


import java.sql.*;
/*
* 数据库工具类
* @author DOCT20
*/
public class DbUtil {
    private String dbUrl = "jdbc:mysql://localhost:3306/db_book";//数据库连接地址
    private String dbUserName = "root";//数据库用户名
    private String dbPassword = "2004528cjY";//密码
    private String jdbcName="com.mysql.jdbc.Driver";//数据库驱动
    
    
    //获取数据库连接

    public Connection getCon() throws Exception {
        Class.forName(jdbcName);//加载数据库驱动
        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);//获取数据库连接
        return con;
    }

    //关闭数据库连接
    public void closeCon(Connection con) throws Exception {
        if(con !=null){
            con.close();
        }
    }
    // public static void main(String[] args) {
    //     DbUtil dbUtil = new DbUtil();
    //     try {
    //         Connection con = dbUtil.getCon();
    //         System.out.println("成功");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         System.out.println("失败");
    //     }
    // }
    


}
