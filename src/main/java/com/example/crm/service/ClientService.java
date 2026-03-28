package com.example.crm.service;

import com.example.crm.entity.Client;
import com.example.crm.repository.ClientRepository;
import org.springframework.stereotype.Service;
import com.example.crm.dto.ClientRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(ClientRequest request) {
        Client client = new Client();
        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setPhone(request.getPhone());

        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client updateClient(Long id, Client updatedClient) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        client.setName(updatedClient.getName());
        client.setEmail(updatedClient.getEmail());
        client.setPhone(updatedClient.getPhone());

        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
    public Page<Client> getClientsPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clientRepository.findAll(pageable);
    }

    public List<Client> searchClientsByName(String name) {
        return clientRepository.findByNameContainingIgnoreCase(name);
    }
}