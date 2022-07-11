package com.model;

public class User {
    private int id; //编号
    private String name; //账号
    private String password; //密码
    //生成构造方法
    public User() {
        super();
    }
    //生成构造方法
    public User(String name, String password) {
        
        this.name = name;
        this.password = password;
    }
    //生成getters方法
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return name;
    }
    public void setUserName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
