package ssau.learn.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import ssau.learn.entity.Client;
import ssau.learn.entity.RealEstate;

@Data
public class RealEstateDto {
    private Long id;
    private String address;
    private Long cost;
    private Double area;

    public RealEstate toRealEstate() {
        RealEstate realEstate = new RealEstate ();
        BeanUtils.copyProperties(this,realEstate);
        return realEstate;
    }

    public static RealEstateDto fromRealEstate(RealEstate realEstate){
        RealEstateDto dto = new RealEstateDto();
        BeanUtils.copyProperties(realEstate,dto);
        return dto;
    }
}
