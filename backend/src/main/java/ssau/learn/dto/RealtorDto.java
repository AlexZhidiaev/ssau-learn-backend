package ssau.learn.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import ssau.learn.entity.Realtor;


@Data
public class RealtorDto {
    private Long id;
    private String fio;
    private Long inn;

    public Realtor toRealtor() {
        Realtor realtor = new Realtor();
        BeanUtils.copyProperties(this,realtor);
        return realtor;
    }

    public static RealtorDto fromRealtor(Realtor realtor){
        RealtorDto dto = new RealtorDto();
        BeanUtils.copyProperties(realtor,dto);
        return dto;
    }

}