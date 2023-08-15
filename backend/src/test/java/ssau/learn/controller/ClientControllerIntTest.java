package ssau.learn.controller;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import ssau.learn.SsauLearnApplication;
import ssau.learn.dto.BookDto;
import ssau.learn.dto.ClientDto;
import ssau.learn.entity.Role;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SsauLearnApplication.class)
public class ClientControllerIntTest {
    @Autowired
    private ClientController clientController;

    @BeforeClass
    public static void init() {
        SecurityContextHolder.getContext().setAuthentication(new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singletonList(new SimpleGrantedAuthority(Role.ROLE_ADMIN.toString()));
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return "login";
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "login";
            }
        });
    }

    @Test
    @Transactional
    public void testBookOperations() {
        List<ClientDto> clients = clientController.getClients(null);
        assertEquals(0, clients.size());

        ClientDto client = new ClientDto();
        client.setFio("Name 1");
        clientController.create(client);
        client = new ClientDto();
        client.setFio("Name 2");
        clientController.create(client);
        clients = clientController.getClients(null);
        assertEquals(2, clients.size());
        clients = clientController.getClients("2");
        assertEquals(1, clients.size());

        client = new ClientDto();
        client.setId(1L);
        client.setFio("Name 3");
        clientController.update(1L, client);
        client = clientController.getClient(1L);
        assertEquals("Name 3", client.getFio());

        clientController.delete(2L);
        clients = clientController.getClients(null);
        assertEquals(1, clients.size());
    }
}
