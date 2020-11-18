package com.example.demo.func;

import com.example.demo.data.LoginInfo;

public class LoginCheck {
    public static boolean isLogin(LoginInfo info){
        if("admin".equals(info.getUsername()) && "123456".equals(info.getPassword())){
            return true;
        } else {
            return false;
        }
    }
}
