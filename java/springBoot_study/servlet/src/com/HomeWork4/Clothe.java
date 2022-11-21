package com.HomeWork4;

import java.io.Serializable;

public class Clothe implements Serializable {
    private static final long serialVersionUID=1L;
    private String id;
    private String name;
    public Clothe(){}
    public Clothe(String id, String name){
        this.id=id;
        this.name=name;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
}
