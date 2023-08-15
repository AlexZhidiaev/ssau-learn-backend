package ssau.learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssau.learn.entity.Client;
import ssau.learn.entity.DealType;

import java.util.List;

@Repository
public interface DealTypeRepository extends JpaRepository<DealType, Long> {

    List<DealType> findAll();
    List<DealType> findAllByNameContaining(String name);
}
