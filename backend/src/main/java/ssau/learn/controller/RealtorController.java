package ssau.learn.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ssau.learn.dto.RealtorDto;
import ssau.learn.service.RealtorService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1/realtors")
@RequiredArgsConstructor
public class RealtorController {
    private final RealtorService realtorService;


    @GetMapping
    public List<RealtorDto> getRealtors(@RequestParam(required = false) String fio) {
        return realtorService.getAll(fio);
    }

    @GetMapping(path = "/{id}")
    public RealtorDto getRealtor(@PathVariable Long id) {
        return realtorService.getRealtor(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public RealtorDto create(@RequestBody RealtorDto dto) {
        if (dto.getId() != null) {
            dto.setId(null);
        }
        return realtorService.save(dto);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RealtorDto update(@PathVariable Long id, @RequestBody RealtorDto dto) {
        dto.setId(id);
        return realtorService.save(dto);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        realtorService.delete(id);
    }



}
