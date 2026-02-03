package com.ricardomedeiros.fastfoodorders.repositories;

import com.ricardomedeiros.fastfoodorders.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {


}
