package ssau.learn.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "deal")
@Data
@SequenceGenerator(name="dealIdGenerator", sequenceName="deal_id_seq", initialValue=1, allocationSize=1)
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="dealIdGenerator")
    private Long id;
    private Date date;
    private String time;
    private Long commission;

    private Long client_id;
    private Long real_estate_id;
    private Long realtor_id;
    private Long deal_type_id;
}
