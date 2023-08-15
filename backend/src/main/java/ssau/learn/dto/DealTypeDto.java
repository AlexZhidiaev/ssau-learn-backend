package ssau.learn.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import ssau.learn.entity.Client;
import ssau.learn.entity.DealType;

@Data
public class DealTypeDto {
    private Long id;
    private String name;
    private Long code;

    public DealType toDealType() {
        DealType dealType = new DealType();
        BeanUtils.copyProperties(this,dealType);
        return dealType;
    }

    public static DealTypeDto fromDealType(DealType dealType){
        DealTypeDto dto = new DealTypeDto();
        BeanUtils.copyProperties(dealType,dto);
        return dto;
    }

}