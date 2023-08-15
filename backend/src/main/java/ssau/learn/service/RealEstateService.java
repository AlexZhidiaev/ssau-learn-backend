package ssau.learn.service;

import ssau.learn.dto.RealEstateDto;
import ssau.learn.dto.RealtorDto;
import ssau.learn.entity.RealEstate;

import java.util.List;

public interface RealEstateService {

    List<RealEstateDto> getAll(String address);

    RealEstateDto getRealEstate(Long id);

    void delete(Long id);

    RealEstateDto save(RealEstateDto realEstate);

    RealEstateDto evaluate(RealEstateDto realEstate);
}
