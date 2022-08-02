package com.example.usermodule.Repository;

import com.example.usermodule.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    User findByVkey(String vkey);
    User findByCin(String cin);
    User findUserById(Long id);
}
