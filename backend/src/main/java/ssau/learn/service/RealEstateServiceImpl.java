package ssau.learn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssau.learn.controller.RealEstateController;
import ssau.learn.dao.RealEstateRepository;
import ssau.learn.dao.RealtorRepository;
import ssau.learn.dto.RealEstateDto;
import ssau.learn.dto.RealtorDto;
import ssau.learn.entity.RealEstate;

import javax.sql.DataSource;
import java.net.http.HttpClient;
import java.util.List;
import java.util.stream.Collectors;

import static ssau.learn.pythonCaller.RealEstateHandler.getCost;


@Service
@RequiredArgsConstructor
public class RealEstateServiceImpl implements RealEstateService {

    private final DataSource dataSource;
    private final RealEstateRepository realEstateRepository;

    @Override
    public List<RealEstateDto> getAll(String address) {
        return (address == null ? realEstateRepository.findAll() : realEstateRepository.findAllByAddressContaining(address)).
                stream().map(RealEstateDto::fromRealEstate).collect(Collectors.toList());
    }

    @Override
    public RealEstateDto getRealEstate(Long id) {
        return RealEstateDto.fromRealEstate(realEstateRepository.getById(id));
    }


    @Override
    public void delete(Long id) {
        realEstateRepository.deleteById(id);
    }

    @Override
    public RealEstateDto save(RealEstateDto dto) {
        return RealEstateDto.fromRealEstate(realEstateRepository.save(dto.toRealEstate()));
    }

    @Override
    public RealEstateDto evaluate(RealEstateDto dto){
        dto.setCost((long) getCost(dto));
        return RealEstateDto.fromRealEstate(realEstateRepository.save(dto.toRealEstate()));
    }
}

