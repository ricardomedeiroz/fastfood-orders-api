package com.ricardomedeiros.fastfoodorders.services;

import com.ricardomedeiros.fastfoodorders.entities.Menu;
import com.ricardomedeiros.fastfoodorders.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class MenuService implements Serializable {
    @Autowired
    private MenuRepository menuRepository;


    public List<Menu> findAll(){
    return menuRepository.findAll();
    }

    public Menu findById (Long id){

        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Menu insert(Menu menu) {
        return menuRepository.save(menu);
    }

    public void deleteById (Long id){
        menuRepository.deleteById(id);
    }




}
