package com.ricardomedeiros.fastfoodorders.config;
import com.ricardomedeiros.fastfoodorders.entities.Menu;
import com.ricardomedeiros.fastfoodorders.enums.Category;
import com.ricardomedeiros.fastfoodorders.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private MenuRepository menuRepository;


    @Override
    public void run(String... args) throws Exception {



        Menu m1 = new Menu(null,"Hamburguer", "salad, cheese, bacon", 5.0, Category.BURGUER, true);
        Menu m2 = new Menu(null,"Coke", "500ml", 2.0, Category.DRINK, true);
        Menu m3 = new Menu(null,"Nutella", "200g", 5.0, Category.SIDE, true);
        Menu m4 = new Menu(null,"French fries", "200g", 3.0, Category.BURGUER, true);

        menuRepository.saveAll(Arrays.asList(m1,m2,m3,m4));


    }

}
