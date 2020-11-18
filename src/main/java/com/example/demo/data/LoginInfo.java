package com.example.demo.data;

import lombok.Data;

import javax.swing.*;
import java.io.Serializable;

@Data
public class LoginInfo implements Serializable {
    // 序列化接口
    private String username;
    private String password;
    private String message;
}