package ssau.learn.controller;

import org.junit.jupiter.api.Test;
import ssau.learn.dto.ClientDto;
import ssau.learn.service.ClientService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientControllerTest {
    @Test
    public void testCreate() {
        ClientService clientService = mock(ClientService.class);
        ClientDto result = new ClientDto();
        result.setFio("Cl 1");
        result.setId(1L);
        when(clientService.save(any(ClientDto.class))).thenReturn(result);

        ClientController clientController = new ClientController(clientService);
        ClientDto client = new ClientDto();
        client.setFio("Cl 1");
        client = clientController.create(client);
        assertEquals("Cl 1", client.getFio());
        assertEquals(1L, client.getId().longValue());
    }
}
