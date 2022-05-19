package com.example.usermodule.Repository;

import com.example.usermodule.model.ResponsableFD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsableFDRepo extends JpaRepository<ResponsableFD, Long> {
}
