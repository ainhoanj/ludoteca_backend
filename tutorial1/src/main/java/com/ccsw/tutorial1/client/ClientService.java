package com.ccsw.tutorial1.client;

import com.ccsw.tutorial1.client.model.Client;
import com.ccsw.tutorial1.client.model.ClientDto;


import java.util.List;


public interface ClientService {

    Client get(Long id);

    List<ClientDto> findAll();


    void save(Long id, ClientDto dto);


    void delete(Long id) throws Exception;

}
