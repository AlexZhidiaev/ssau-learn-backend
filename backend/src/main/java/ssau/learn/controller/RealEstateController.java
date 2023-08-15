package ssau.learn.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ssau.learn.dto.RealEstateDto;
import ssau.learn.dto.RealtorDto;
import ssau.learn.service.RealEstateService;
import ssau.learn.service.RealtorService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1/realEstates")
@RequiredArgsConstructor
public class RealEstateController {

    private final RealEstateService realEstateService;


    @GetMapping
    public List<RealEstateDto> getRealEstates(@RequestParam(required = false) String address) {
        return realEstateService.getAll(address);
    }

    @GetMapping(path = "/{id}")
    public RealEstateDto getRealEstate(@PathVariable Long id) {
        return realEstateService.getRealEstate(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public RealEstateDto create(@RequestBody RealEstateDto dto) {
        if (dto.getId() != null) {
            dto.setId(null);
        }
        return realEstateService.save(dto);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RealEstateDto update(@PathVariable Long id, @RequestBody RealEstateDto dto) {
        dto.setId(id);
        return realEstateService.save(dto);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        realEstateService.delete(id);
    }

    @PostMapping(path="/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RealEstateDto evaluate(@PathVariable Long id, @RequestBody RealEstateDto dto){
        if (dto.getId() != null) {
            dto.setId(null);
        }
        System.out.println("Controller---"+ id);
        return realEstateService.evaluate(dto);
    }

}
