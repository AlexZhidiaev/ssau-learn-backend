package ssau.learn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ssau.learn.dto.ClientDto;
import ssau.learn.service.ClientService;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public List<ClientDto> getClients(@RequestParam(required = false) String fio) {
        return clientService.getAll(fio);
    }

    @GetMapping(path = "/{id}")
    public ClientDto getClient(@PathVariable Long id) {
        return clientService.getClient(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ClientDto create(@RequestBody ClientDto dto) {
        if (dto.getId() != null) {
            dto.setId(null);
        }
        return clientService.save(dto);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ClientDto update(@PathVariable Long id, @RequestBody ClientDto dto) {
        dto.setId(id);
        return clientService.save(dto);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }


}