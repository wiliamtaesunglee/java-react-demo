package com.react.react.repository;

import com.react.react.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findUserByUsername(String username);
}
