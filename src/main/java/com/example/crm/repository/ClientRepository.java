package com.example.crm.repository;

import com.example.crm.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameContainingIgnoreCase(String name);

    Page<Client> findAll(Pageable pageable);
}