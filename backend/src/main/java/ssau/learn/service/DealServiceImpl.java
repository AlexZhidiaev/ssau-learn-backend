package ssau.learn.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssau.learn.dao.DealRepository;
import ssau.learn.dto.ClientDto;
import ssau.learn.dto.DealDto;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService{
    private final DataSource dataSource;
    private final DealRepository dealRepository;

    @Override
    public List<DealDto> getAll(Date date) {
        return (date == null ? dealRepository.findAll() : dealRepository.findAllByDateContaining(date)).
                stream().map(DealDto::fromDeal).collect(Collectors.toList());
    }

    @Override
    public DealDto getDeal(Long id) {
        return DealDto.fromDeal(dealRepository.getById(id));
    }


    @Override
    public void delete(Long id) {
        dealRepository.deleteById(id);
    }

    @Override
    public DealDto save(DealDto dto) {
        return DealDto.fromDeal(dealRepository.save(dto.toDeal()));
    }
}
