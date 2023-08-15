package ssau.learn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import ssau.learn.dao.ClientRepository;
import ssau.learn.dto.ClientDto;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    private final DataSource dataSource;


    @Override
    public List<ClientDto> getAll(String fio) {
        return (fio == null ? clientRepository.findAll() : clientRepository.findAllByFioContaining(fio)).
                stream().map(ClientDto::fromClient).collect(Collectors.toList());
    }

    @Override
    public ClientDto getClient(Long id) {
        return ClientDto.fromClient(clientRepository.getById(id));
    }


    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDto save(ClientDto dto) {
        return ClientDto.fromClient(clientRepository.save(dto.toClient()));
    }

    @Override
    public List<ClientDto> select() {
        return new NamedParameterJdbcTemplate(dataSource).query("select id, fio from client", Collections.emptyMap(), (rs, rowNum) -> {
            ClientDto clientDto = new ClientDto();
            clientDto.setId(rs.getLong("id"));
            clientDto.setFio(rs.getString("fio"));
            return clientDto;
        });
    }

}