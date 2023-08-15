package ssau.learn.service;

import ssau.learn.dto.ClientDto;
import ssau.learn.dto.RealtorDto;

import java.util.List;

public interface RealtorService {

    List<RealtorDto> getAll(String fio);
    RealtorDto getRealtor(Long id);

    void delete(Long id);

    RealtorDto save(RealtorDto realtor);
}
