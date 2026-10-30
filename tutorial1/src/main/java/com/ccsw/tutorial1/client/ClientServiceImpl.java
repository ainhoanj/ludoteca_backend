package com.ccsw.tutorial1.client;

import com.ccsw.tutorial1.client.model.Client;
import com.ccsw.tutorial1.client.model.ClientDto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Client get(Long id) {

        return this.clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<ClientDto> findAll() {

        return this.clientRepository.findAll().stream()
                .map(category -> modelMapper.map(category, ClientDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public void save(Long id, ClientDto dto) {

        List<Client> existences = this.clientRepository.findByNameIgnoreCase(dto.getName());

        for (Client existence : existences) {
            if (id == null || !existence.getId().equals(id)) {
                throw new IllegalArgumentException("Ya existe un cliente con ese nombre");
           }
        }


        Client client;

        if (id == null) {
            client = new Client();
        } else {
            client = this.get(id);
        }

        client.setName(dto.getName());

        this.clientRepository.save(client);
    }

    @Override
    public void delete(Long id) throws Exception {

        if(this.get(id) == null){
            throw new Exception("Not exists");
        }

        this.clientRepository.deleteById(id);
    }

}
