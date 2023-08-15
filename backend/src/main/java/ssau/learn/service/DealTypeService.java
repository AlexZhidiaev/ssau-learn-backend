package ssau.learn.service;

import ssau.learn.dto.ClientDto;
import ssau.learn.dto.DealTypeDto;
import ssau.learn.entity.DealType;

import java.util.List;


public interface DealTypeService {

    List<DealTypeDto> getAll(String fio);
    DealTypeDto getDealType(Long id);

    void delete(Long id);

    DealTypeDto save(DealTypeDto dealType);


}