package ru.ilya.shopcraftercore.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilya.shopcraftercore.entity.auth.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
