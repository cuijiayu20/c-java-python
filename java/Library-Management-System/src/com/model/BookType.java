package com.model;
/**
 * 完成图书类别
 * @author DOCT20
 */

public class BookType {
    private int id;  //编号
    private String  bookTypeName;//图书类别名称
    private String  bookTypeDesc;//图书类别描述
    public BookType(){
        super();        
    }
    public BookType(String bookTypeName,String bookTypeDesc){
        super();
        this.bookTypeName=bookTypeName;
        this.bookTypeDesc=bookTypeDesc;
    }
    public BookType(int id,String bookTypeName,String bookTypeDesc){
        super();
        this.id=id;
        this.bookTypeName=bookTypeName;
        this.bookTypeDesc=bookTypeDesc;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getBookTypeName(){
        return bookTypeName;
    }
    public void setBookTypeName(String bookTypeName){
        this.bookTypeName=bookTypeName;
    }
    public String getBookTypeDesc(){
        return bookTypeDesc;
    }
    public void setBookTypeDesc(String bookTypeDesc){
        this.bookTypeDesc=bookTypeDesc;
    }
    //答打印对象内调用
    //该方法是Object方法自动调用
    public String toString(){
        return bookTypeName;
    }
}
