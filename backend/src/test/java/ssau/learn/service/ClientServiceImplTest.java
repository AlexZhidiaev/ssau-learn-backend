package ssau.learn.service;

import org.junit.Test;
import ssau.learn.dao.BookRepository;
import ssau.learn.dao.ClientRepository;
import ssau.learn.dto.BookDto;
import ssau.learn.dto.ClientDto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientServiceImplTest {
    @Test
    public void testSelect() throws SQLException {

       ClientRepository clientRepository = mock(ClientRepository.class);
        DataSource dataSource = mock(DataSource.class);

        Connection connection = mock(Connection.class);
        when(dataSource.getConnection()).thenReturn(connection);

        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement("select id, fio from client")).thenReturn(preparedStatement);

        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong("id")).thenReturn(1L).thenThrow(SQLException.class);
        when(resultSet.getString("fio")).thenReturn("Cl 1").thenThrow(SQLException.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        ClientService clientService = new ClientServiceImpl(clientRepository, dataSource);
        List<ClientDto> clients = clientService.select();
        assertEquals(1, clients.size());
        assertEquals(1L, clients.get(0).getId().longValue());
        assertEquals("Cl 1", clients.get(0).getFio());

    }
}
