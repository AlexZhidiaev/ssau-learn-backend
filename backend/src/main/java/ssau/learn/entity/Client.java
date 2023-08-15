package ssau.learn.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Data
@SequenceGenerator(name="clientIdGenerator", sequenceName="client_id_seq", initialValue=1, allocationSize=1)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="clientIdGenerator")
    private Long id;
    private String fio;
    private String document;
}
