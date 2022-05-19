package com.ced.preinscriptionmodule.repository;

import com.ced.preinscriptionmodule.model.Preinscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreinscriptionRepo extends JpaRepository<Preinscription, Long> {

    public Preinscription findByCin(String cin);


}
