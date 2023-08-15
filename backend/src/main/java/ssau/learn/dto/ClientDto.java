package ssau.learn.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import ssau.learn.entity.Book;
import ssau.learn.entity.Client;

@Data
public class ClientDto {
    private Long id;
    private String fio;
    private String document;

    public Client toClient() {
        Client client = new Client();
        BeanUtils.copyProperties(this,client);
        return client;
    }

    public static ClientDto fromClient(Client client){
        ClientDto dto = new ClientDto();
        BeanUtils.copyProperties(client,dto);
        return dto;
    }

}
