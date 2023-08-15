package ssau.learn.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "realtor")
@Data
@SequenceGenerator(name="realtorIdGenerator", sequenceName="realtor_id_seq", initialValue=1, allocationSize=1)
public class Realtor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="realtorIdGenerator")
    private Long id;
    private String fio;
    private Long inn;
}
