package ssau.learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ssau.learn.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    Boolean existsByLoginOrEmail(String login, String email);
}