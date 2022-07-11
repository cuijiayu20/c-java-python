package com.dao;

import javax.swing.*;

import com.model.User;
import com.util.DbUtil;
import com.util.StringUtil;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.*;
public class Register {
     //注册界面
        public static void registerUi() {
            // TODO Auto-generated method stub
            //创建JFrame对象
            JFrame frame = new JFrame("注册");
            //设置窗口大小
            frame.setSize(400, 300);
            //设置窗口居中
            frame.setLocationRelativeTo(null);
            //设置窗口不可改变大小
            frame.setResizable(false);
            //设置窗口关闭时退出程序
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //设置窗口内容面板
            frame.getContentPane().setLayout(null);
            //创建JLabel对象
            JLabel label1 = new JLabel("用户名：");
            //设置JLabel对象的位置
            label1.setBounds(50, 50, 80, 30);
            //创建JTextField对象
            JTextField text1 = new JTextField();
            //设置JTextField对象的位置
            text1.setBounds(120, 50, 150, 30);
            //创建JLabel对象
            JLabel label2 = new JLabel("密码：");
            //设置JLabel对象的位置
            label2.setBounds(50, 100, 80, 30);
            //创建JTextField对象
            JTextField text2 = new JTextField();
            //设置JTextField对象的位置
            text2.setBounds(120, 100, 150, 30);
            frame.setVisible(true);
            //加入面板
            frame.getContentPane().add(label1);
            frame.getContentPane().add(text1);
            frame.getContentPane().add(label2);
            frame.getContentPane().add(text2);
            //确定button
            JButton button = new JButton("确定");
            button.setBounds(150, 150, 80, 30);
            frame.getContentPane().add(button);
            //取消按钮
            JButton button2 = new JButton("取消");
            button2.setBounds(250, 150, 80, 30);
            frame.getContentPane().add(button2);

            

            //设置监听
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    // TODO Auto-generated method stub
                    LoginUi UI=new LoginUi();
                    DbUtil dbutil = new DbUtil();//创建DbUtil对象建立连接
                    UserDo userdo = new UserDo();//创建User对象
                    Connection con=null;
                    User currentUser=null;
                    //获取用户名和密码
                    String name = text1.getText();
                    String password = text2.getText();
                    //判断用户名和密码是否为空
                    User user =new User(name,password);//创建User对象
                    try {
                        con=dbutil.getCon();//建立连接
                    
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    
                    try {
                        currentUser = userdo.login(con,user);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }//调用login方法
    
                    if(StringUtil.isEmpty(name)||StringUtil.isEmpty(password)){
                        JOptionPane.showMessageDialog(null, "用户名或密码不能为空！", "提示", JOptionPane.WARNING_MESSAGE);
                    }else{
                        //判断用户名是否已存在
                        if(currentUser!=null){
                            JOptionPane.showMessageDialog(null, "用户名已存在！", "提示", JOptionPane.WARNING_MESSAGE);
                        }else{
                            //创建User对象
                            UserIn user1 = new UserIn();
                            try {
                                user1.registerIn(con,user);
                                //成功弹窗
                                JOptionPane.showMessageDialog(null, "注册成功！", "提示", JOptionPane.WARNING_MESSAGE);
                                //关闭窗口
                                frame.dispose();
                                UI.initUI();//返回登录界面

                            } catch (Exception e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                                //失败弹窗
                                JOptionPane.showMessageDialog(null, "注册失败！", "提示", JOptionPane.WARNING_MESSAGE);
                                
                            }
                        }
                    }
                }
                
            });
            //取消按钮的监听事件
            button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    //关闭窗口
                    frame.dispose();
                }
            });
        
    
            

            

        
        }
    
    
}
class UserIn {
    public User registerIn(Connection con,User user)throws Exception{
        User resultUser = null;//设置一个返回的用户对象
        con.setAutoCommit(true);//设置事务不自动提交
        String sql="insert into t_user(userName,password) values(?,?);";//设置查询语句查询对应的用户
        PreparedStatement pstmt = con.prepareStatement(sql);//查询语句
        pstmt.setString(1, user.getUserName());//设置查询语句的参数，第一个为用户
        pstmt.setString(2, user.getPassword());//设置查询语句参数，第二个为密码
        pstmt.executeUpdate();
        return resultUser;
        

        

    }
}
