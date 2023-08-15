package ssau.learn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ssau.learn.dto.ClientDto;
import ssau.learn.dto.DealTypeDto;
import ssau.learn.service.ClientService;
import ssau.learn.service.DealTypeService;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1/dealTypes")
@RequiredArgsConstructor
public class DealTypeController {
    private final DealTypeService dealTypeService;

    @GetMapping
    public List<DealTypeDto> getDealTypes(@RequestParam(required = false) String name) {
        return dealTypeService.getAll(name);
    }

    @GetMapping(path = "/{id}")
    public DealTypeDto getDealType(@PathVariable Long id) {
        return dealTypeService.getDealType(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public DealTypeDto create(@RequestBody DealTypeDto dto) {
        if (dto.getId() != null) {
            dto.setId(null);
        }
        return dealTypeService.save(dto);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public DealTypeDto update(@PathVariable Long id, @RequestBody DealTypeDto dto) {
        dto.setId(id);
        return dealTypeService.save(dto);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        dealTypeService.delete(id);
    }


}