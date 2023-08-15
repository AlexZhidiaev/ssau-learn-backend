package ssau.learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ssau.learn.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByNameContaining(String name);
}
