package ssau.learn.service;


import ssau.learn.dto.ClientDto;

import java.util.List;

public interface ClientService {

    List<ClientDto> getAll(String fio);
    ClientDto getClient(Long id);

    void delete(Long id);

    ClientDto save(ClientDto client);


    List<ClientDto> select();
}