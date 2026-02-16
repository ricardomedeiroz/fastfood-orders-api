package com.ricardomedeiros.fastfoodorders.services;
import com.ricardomedeiros.fastfoodorders.entities.Client;
import com.ricardomedeiros.fastfoodorders.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;


    public List<Client> findAll(){
        return repository.findAll();
    }

    public Client findById(Long id){

        return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Client insert (Client client){

        return repository.save(client);
    }

    public void delete(Long id){
        repository.deleteById(id);

    }



}
