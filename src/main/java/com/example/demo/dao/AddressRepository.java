package com.example.demo.dao;

import com.example.demo.entity.ListData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<ListData, Long> {
    ListData findByName(String name);
    List<ListData> findByPhone(String phone);
}
