package com.example.crm.controller;

import com.example.crm.entity.Client;
import com.example.crm.service.ClientService;
import org.springframework.web.bind.annotation.*;
import com.example.crm.dto.ClientRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Создать клиента")
    @PostMapping
    public Client createClient(@RequestBody @Valid ClientRequest request) {
        return clientService.createClient(request);
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "Client deleted!";
    }
    @GetMapping("/page")
    public Page<Client> getClientsPage(@RequestParam int page, @RequestParam int size) {
        return clientService.getClientsPage(page, size);
    }

    @Operation(summary = "Получить всех клиентов")
    @GetMapping("/search")
    public List<Client> searchClients(@RequestParam String name) {
        return clientService.searchClientsByName(name);
    }
}