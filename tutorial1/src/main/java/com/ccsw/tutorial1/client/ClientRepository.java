package com.ccsw.tutorial1.client;
import java.util.List;
import com.ccsw.tutorial1.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameIgnoreCase( String name);

}
