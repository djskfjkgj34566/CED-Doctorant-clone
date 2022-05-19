package com.example.usermodule.Repository;

import com.example.usermodule.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, Integer> {
}
