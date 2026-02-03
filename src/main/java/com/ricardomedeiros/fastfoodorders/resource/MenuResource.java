package com.ricardomedeiros.fastfoodorders.resource;
import com.ricardomedeiros.fastfoodorders.entities.Menu;
import com.ricardomedeiros.fastfoodorders.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuResource {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public ResponseEntity<List<Menu>>findAll(){
        List<Menu> list = menuService.findAll();
        return ResponseEntity.ok().body(list);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Menu>findById(@PathVariable Long id){
    Menu menu = menuService.findById(id);
    return ResponseEntity.ok().body(menu);

    }

    @PostMapping
    public ResponseEntity<Menu> insert(@RequestBody Menu obj){
     obj = menuService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
     return ResponseEntity.created(uri).body(obj);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        menuService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
