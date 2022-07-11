package com.dao;
import java.awt.*;
import javax.swing.*;




import com.model.User;
import com.util.DbUtil;
import com.util.StringUtil;
import com.view.MainFrm;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;

public class LoginUi{
    
    public static void initUI(){
        JFrame frame=new JFrame();
        frame.setTitle("Login");    //设置窗口标题
        frame.setSize(500,550);  //设置窗口大小
        frame.setBackground(Color.WHITE);  //设置窗口背景色
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口
        frame.setLocationRelativeTo(null);//设置窗口居中
        frame.setResizable(false);//设置窗口不可改变大小
        ImageIcon image1=new ImageIcon("D:\\c++-java-python\\java\\Library-Management-System\\src\\Login.jpg");//获取图片
        JLabel label1 = new JLabel( image1,JLabel.CENTER) ; 
        label1.setBounds(0, 0, 500, 550); //把标签设置为和图片等高等宽
        
        JPanel jp1 = new JPanel();//创建面板
        jp1.setLayout(null);//设置绝对布局
        jp1.setOpaque(false);//设置不可见
        jp1.setPreferredSize(new Dimension(500,550));
        frame.getContentPane().add(jp1);//把面板加入到窗口
        frame.setVisible(true);//设置窗口可见
        //添加登录输入框
        JPanel jp3 = new JPanel();//创建面板
        jp3.setLayout(null);//设置绝对布局
        jp3.setOpaque(false);//设置不可见
        jp3.setPreferredSize(new Dimension(100,30));//大小
        frame.getContentPane().add(jp3);//把面板加入到窗口
        JLabel label2=new JLabel("账号:");
        label2.setBounds(100,200,100,30);
        label2.setFont(new Font("宋体",Font.BOLD,20));
        label2.setForeground(Color.BLACK);
        frame.add(label2);
        JTextField text1=new JTextField();


        text1.setBounds(200,200,200,30);


        text1.setFont(new Font("宋体",Font.BOLD,20));
        text1.setForeground(Color.BLACK);
        text1.setBackground(Color.WHITE);
        frame.add(text1);
        //添加密码输入框
        JPanel jp4 = new JPanel();//创建面板
        jp4.setLayout(null);//设置绝对布局
        jp4.setOpaque(false);//设置不可见
        jp4.setPreferredSize(new Dimension(100,30));
        frame.getContentPane().add(jp4);//把面板加入到窗口
        JLabel label3=new JLabel("密码:");

        label3.setBounds(100,250,100,30);

        label3.setFont(new Font("宋体",Font.BOLD,20));
        label3.setForeground(Color.BLACK);
        frame.add(label3);
        JTextField text2=new JTextField();
        text2.setBounds(200,250,200,30);
        text2.setFont(new Font("宋体",Font.BOLD,20));
        text2.setForeground(Color.BLACK);
        text2.setBackground(Color.WHITE);
        frame.add(text2);
        //添加登录按钮
        JPanel jp2 = new JPanel();//创建面板
        jp1.setLayout(null);//设置绝对布局
        jp1.setOpaque(false);//设置不可见
        jp1.setPreferredSize(new Dimension(100,30));
        frame.getContentPane().add(jp1);//把面板加入到窗口
        JButton button1=new JButton("登录");
        button1.setBounds(150,400,100,30);//150
        button1.setFont(new Font("宋体",Font.BOLD,20));
        button1.setForeground(Color.BLACK);
        button1.setBackground(Color.WHITE);
        frame.add(button1);
        //添加注册按钮
        JButton button2=new JButton("注册");
        button2.setBounds(300,400,100,30);
        button2.setFont(new Font("宋体",Font.BOLD,20));
        button2.setForeground(Color.BLACK);
        button2.setBackground(Color.WHITE);
        frame.add(button2);//添加Button2
        frame.add(label1);//添加图片

        //添加登录按钮监听器
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //创建User对象
                DbUtil dbutil = new DbUtil();//创建DbUtil对象建立连接
                UserDo userdo = new UserDo();//创建User对象
                Connection con=null;
                User currentUser=null;
                String name=text1.getText();    //获得输入的用户名
                String password=text2.getText();//获得输入的密码
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


                if(StringUtil.isEmpty(name)||StringUtil.isEmpty(password)){ //如果为空，
                    //消息对话框，提示
                    JOptionPane.showMessageDialog(null,"请输入账号或密码！","提示",JOptionPane.WARNING_MESSAGE);
                }else
                    if(currentUser!=null){//比较数据库的值
                        JOptionPane.showMessageDialog(null,"登录成功！","提示",JOptionPane.WARNING_MESSAGE);
                        frame.dispose();
                        new MainFrm().setVisible(true);
                        //Admin.initUI();  //跳出系统管理员界面（预留的接口）
                        try {
                            dbutil.closeCon(con);
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }finally{
                            try {
                                dbutil.closeCon(con);
                            } catch (Exception e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(null,"账号或密码错误！","提示",JOptionPane.WARNING_MESSAGE);
                    }
                
            }
        });
        //注册UI


        //添加注册按钮监听器
        button2.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                frame.dispose();
                Register.registerUi();
                //Register.initUI();
            }
        });
        
        
    }
// 下拉框

}

