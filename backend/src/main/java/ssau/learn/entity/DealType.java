package ssau.learn.entity;

import lombok.Data;

import javax.persistence.*;



@Entity
@Table(name = "deal_type")
@Data
@SequenceGenerator(name="dealtypeIdGenerator", sequenceName="dealtype_id_seq", initialValue=1, allocationSize=1)
public class DealType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="dealtypeIdGenerator")
    private Long id;
    private String name;
    private Long code;
}
