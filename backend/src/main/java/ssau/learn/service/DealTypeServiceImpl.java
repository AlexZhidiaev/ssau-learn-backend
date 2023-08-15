package ssau.learn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssau.learn.dao.ClientRepository;
import ssau.learn.dao.DealTypeRepository;
import ssau.learn.dto.ClientDto;
import ssau.learn.dto.DealTypeDto;
import ssau.learn.entity.DealType;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DealTypeServiceImpl implements DealTypeService{

    private final DataSource dataSource;
    private final DealTypeRepository dealTypeRepository;

    @Override
    public List<DealTypeDto> getAll(String fio) {
        return (fio == null ? dealTypeRepository.findAll() : dealTypeRepository.findAllByNameContaining(fio)).
                stream().map(DealTypeDto::fromDealType).collect(Collectors.toList());
    }

    @Override
    public DealTypeDto getDealType(Long id) {
        return DealTypeDto.fromDealType(dealTypeRepository.getById(id));
    }


    @Override
    public void delete(Long id) {
        dealTypeRepository.deleteById(id);
    }

    @Override
    public DealTypeDto save(DealTypeDto dto) {
        return DealTypeDto.fromDealType(dealTypeRepository.save(dto.toDealType()));
    }

}
