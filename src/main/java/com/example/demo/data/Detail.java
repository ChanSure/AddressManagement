package com.example.demo.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class Detail implements Serializable {
    private String name;
    private String phone;
    private String mail;
    private String location;
    private String qq;
    private String message;

    public Detail(String name, String phone , String mail, String location, String qq, String message){
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.location = location;
        this.qq = qq;
        this.message = message;
    }
}
