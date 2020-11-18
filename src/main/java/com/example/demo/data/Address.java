package com.example.demo.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Vector;

@Data
public class Address implements Serializable {
    private Vector<Detail>detail;

    public Address(){
        detail = new Vector<Detail>();
        detail.add(new Detail("张三","13712345678","abc@163.com","北京市海淀区","1301234567",""));
    }
}
