package ssau.learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ssau.learn.entity.Client;
import ssau.learn.entity.RealEstate;

import java.util.List;

public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {
    List<RealEstate> findAll();
    List<RealEstate> findAllByAddressContaining(String address);
}
