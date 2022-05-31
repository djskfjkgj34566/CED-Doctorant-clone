package com.example.chatmodule.Repository;

import com.example.chatmodule.module.DemandeEncadrement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeEncadrementRepo extends JpaRepository<DemandeEncadrement, Long> {

    DemandeEncadrement findByEncadrantId(Long id);
}
