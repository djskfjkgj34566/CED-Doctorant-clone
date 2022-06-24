package com.example.usermodule.Repository;

import com.example.usermodule.model.ResponsableCED;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsableCEDRepo extends JpaRepository<ResponsableCED, Long> {


    ResponsableCED findByVkey(String vkey);
    ResponsableCED findByCin(String cin);
    public boolean existsByCin(String cin);
}
