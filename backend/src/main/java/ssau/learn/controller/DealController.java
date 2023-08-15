package ssau.learn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ssau.learn.dto.ClientDto;
import ssau.learn.dto.DealDto;
import ssau.learn.service.ClientService;
import ssau.learn.service.DealService;

import java.util.Date;
import java.util.List;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1/deals")
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;

    @GetMapping
    public List<DealDto> getDeals(@RequestParam(required = false) Date date) {
        return dealService.getAll(date);
    }

    @GetMapping(path = "/{id}")
    public DealDto getDeal(@PathVariable Long id) {
        return dealService.getDeal(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public DealDto create(@RequestBody DealDto dto) {
        if (dto.getId() != null) {
            dto.setId(null);
        }
        return dealService.save(dto);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public DealDto update(@PathVariable Long id, @RequestBody DealDto dto) {
        dto.setId(id);
        return dealService.save(dto);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        dealService.delete(id);
    }


}
