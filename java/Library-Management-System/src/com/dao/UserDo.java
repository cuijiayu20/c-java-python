package com.dao;
import java.sql.*;

import com.model.User;
/**00000000000
 * 用户登录类
 * @author doct20
 * @date 2018年12月18日
 */
public class UserDo {
    /**
     * 用户登录
     * @param user
     * @return
     * @throws Exception
     * @author doct20
     */
    public User login(Connection con,User user)throws Exception{
        User resultUser = null;//设置一个返回的用户对象
        String sql="select * from t_user where userName=? and password=? ";//设置查询语句查询对应的用户
        PreparedStatement pstmt = con.prepareStatement(sql);//查询语句
        pstmt.setString(1, user.getUserName());//设置查询语句的参数，第一个为用户
        pstmt.setString(2, user.getPassword());//设置查询语句参数，第二个为密码
        ResultSet rs= pstmt.executeQuery();//执行查询语句返回结果集
        if(rs.next()){//判断是否由下一条记录
            resultUser = new User();//创建一个用户对象
            resultUser.setId(rs.getInt("id"));//设置用户编号，
            resultUser.setUserName(rs.getString("userName"));//设置用户名,设置userd的成员变量
            resultUser.setPassword(rs.getString("password"));//设置密码，设置userd的成员变量

        }
        return resultUser;
        

    }
}
