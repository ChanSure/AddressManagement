package com.example.demo.ajaxController;

import com.example.demo.dao.AddressRepository;
import com.example.demo.data.Address;
import com.example.demo.data.Detail;
import com.example.demo.data.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Vector;

@RestController
@RequestMapping("/ajax")
public class AjaxController {
    private AddressRepository addressRepository;
    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    @PostMapping("/add")
    String check( @RequestBody Phone in) {
        if(addressRepository.findByPhone(in.getPhone()).isEmpty()){
            return "{\"msg\" : \"success\"}";
        }else {
            return "{\"msg\" : \"exist\"}";
        }
    }
}
