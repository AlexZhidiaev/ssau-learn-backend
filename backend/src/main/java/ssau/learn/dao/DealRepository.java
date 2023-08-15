package ssau.learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssau.learn.entity.Client;
import ssau.learn.entity.Deal;
import ssau.learn.entity.DealType;

import java.util.Date;
import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {

    List<Deal> findAll();

    List<Deal> findAllByDateContaining(Date date);
}
