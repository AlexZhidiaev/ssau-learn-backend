package ssau.learn.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "realEstate")
@Data
@SequenceGenerator(name="realEstateIdGenerator", sequenceName="realEstate_id_seq", initialValue=1, allocationSize=1)
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="realEstateIdGenerator")
    private Long id;
    private String address;
    private Long cost;
    private Double area;
}
