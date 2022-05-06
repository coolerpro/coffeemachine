package com.coffeemachine.services;

import com.coffeemachine.entity.Good;
import com.coffeemachine.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodService {

    @Autowired
    private GoodRepository goodRepository;

    public List<Good> findAll(){
        return goodRepository.findAll();
    }
    public void save(Good good){
        goodRepository.save(good);
    }
}
