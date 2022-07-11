package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.BookType;
import com.util.StringUtil;

/**
 * 图书类别Dao类
 */

public class BookTypeDao {
    /**
     * 图书类别添加进库
     * @param cno
     * @param bookType
     * @return int
     * 操作记录数
     * @throws Exception
     */
    public int add(Connection cno,BookType bookType) throws Exception{
        int i=0;
        String sql="insert into t_booktype(bookTypeName,bookTypeDesc) values(?,?)";
        PreparedStatement pstmt=cno.prepareStatement(sql);
        pstmt.setString(1, bookType.getBookTypeName());
        pstmt.setString(2, bookType.getBookTypeDesc());
        //1成功
        return pstmt.executeUpdate();

        
    }
    /**
     * 图书类别查询
     * @param cno
     * @param bookType
     * @return  ResultSet
     * 操作记录数
     * @throws Exception
     */

    
    public ResultSet list(Connection cno,BookType bookType) throws Exception{
        StringBuffer sb=new StringBuffer("select * from t_booktype");
        if(StringUtil.isNotEmpty(bookType.getBookTypeName())){
            sb.append(" and bookTypeName like '%"+bookType.getBookTypeName()+"%'");
        }
        PreparedStatement pstmt=cno.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
        
    }
    /**
     * 图书类别删除
     * @param cno
     * @param bookType
     * @return int
     * 操作记录数
     * @throws Exception
     */
    public int delete(Connection cno,String id) throws Exception{
        String sql="delete from t_booktype where id=?";
        PreparedStatement pstmt=cno.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate(); //删除图书类别
    }
    /**
     * 图书类别修改
     * @param cno
     * @param bookType
     * @return int
     * 操作记录数
     */
    public int update(Connection cno,BookType bookType)throws Exception{
        String sql="update t_booktype set bookTypeName=?,bookTypeDesc=? where id=?";
        PreparedStatement pstmt=cno.prepareStatement(sql);
        pstmt.setString(1, bookType.getBookTypeName());//获取图书类别名称
        pstmt.setString(2, bookType.getBookTypeDesc());
        pstmt.setInt(3, bookType.getId());
        return pstmt.executeUpdate();
    }
}
