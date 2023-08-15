package ssau.learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ssau.learn.entity.Client;
import ssau.learn.entity.Realtor;

import java.util.List;

public interface RealtorRepository extends JpaRepository<Realtor, Long> {
    List<Realtor> findAll();
    List<Realtor> findAllByFioContaining(String fio);
}
