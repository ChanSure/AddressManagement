package com.example.demo.func;

import com.example.demo.data.Address;
import com.example.demo.data.Detail;

import java.util.Vector;

public class AddressCheck {
    public static boolean checkValid(Address address, Detail detail){
        Vector<Detail> list = address.getDetail();
        for (int i = 0; i < list.size(); i++){
            if (list.elementAt(i).getName().equals(detail.getName())){
                return false;
            }
        }
        return true;
    }

    public static void updateElem(Address address, Detail detail){
        int index = -1;
        Vector<Detail> list = address.getDetail();
        for (int i = 0; i < list.size(); i++){
            //System.out.println("index!");
            if (list.elementAt(i).getName().equals(detail.getName())){
                index = i;
                break;
            }
        }
        //System.out.println(index);
        if (-1 != index){
            //System.out.println("index1!");
            list.set(index, detail);
        }

    }

    public static void deleteElem(Address address, int index) {
        address.getDetail().remove(index);
    }
}
