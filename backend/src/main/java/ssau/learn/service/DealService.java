package ssau.learn.service;

import ssau.learn.dto.DealDto;
import ssau.learn.dto.DealTypeDto;

import java.util.Date;
import java.util.List;

public interface DealService {
    List<DealDto> getAll(Date date);
    DealDto getDeal(Long id);

    void delete(Long id);

    DealDto save(DealDto deal);
}
