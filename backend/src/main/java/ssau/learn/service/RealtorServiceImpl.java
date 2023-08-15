package ssau.learn.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssau.learn.dao.RealtorRepository;
import ssau.learn.dto.RealtorDto;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RealtorServiceImpl implements RealtorService{

    private final DataSource dataSource;
    private final RealtorRepository realtorRepository;

    @Override
    public List<RealtorDto> getAll(String fio) {
        return (fio == null ? realtorRepository.findAll() : realtorRepository.findAllByFioContaining(fio)).
                stream().map(RealtorDto::fromRealtor).collect(Collectors.toList());
    }

    @Override
    public RealtorDto getRealtor(Long id) {
        return RealtorDto.fromRealtor(realtorRepository.getById(id));
    }


    @Override
    public void delete(Long id) {
        realtorRepository.deleteById(id);
    }

    @Override
    public RealtorDto save(RealtorDto dto) {
        return RealtorDto.fromRealtor(realtorRepository.save(dto.toRealtor()));
    }
}
