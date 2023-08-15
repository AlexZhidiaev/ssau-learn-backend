package ssau.learn.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import ssau.learn.entity.Deal;

import java.sql.Date;

@Data
public class DealDto {
    private Long id;
    private Date date;
    private String time;
    private Long commission;

    private Long client_id;
    private Long real_estate_id;
    private Long realtor_id;
    private Long deal_type_id;

    public Deal toDeal() {
        Deal deal = new Deal();
        BeanUtils.copyProperties(this, deal);
        return deal;
    }

    public static DealDto fromDeal(Deal deal) {
        DealDto dto = new DealDto();
        BeanUtils.copyProperties(deal, dto);
        return dto;
    }
}
