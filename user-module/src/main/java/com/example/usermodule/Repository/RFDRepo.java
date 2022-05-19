package com.example.usermodule.Repository;

import com.example.usermodule.model.Doctorant;
import com.example.usermodule.model.ResponsableFD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RFDRepo extends JpaRepository<ResponsableFD, Long> {

    ResponsableFD findByCin(String cin);
    ResponsableFD findByVkey(String vkey);
}
