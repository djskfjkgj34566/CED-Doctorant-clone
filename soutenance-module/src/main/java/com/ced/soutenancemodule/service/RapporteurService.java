package com.ced.soutenancemodule.service;

import com.ced.soutenancemodule.model.Rapporteur;
import com.ced.soutenancemodule.repository.RapporteurRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RapporteurService {

    @Autowired
    private RapporteurRepo repo;

    public int save(Rapporteur rapporteur){
        repo.save(rapporteur);
        return 1;
    }

    public List<Rapporteur> findByDoctorantId(Long id) {
        return repo.findByDoctorantId(id);
    }

    public String exportReport(Long doctorantId) throws FileNotFoundException, JRException {
        String path = "D://JasperRepports//";
        List<Rapporteur> rapporteurs = findByDoctorantId(doctorantId);
        File file = ResourceUtils.getFile("classpath:Rapporteurs.jrxml");
        JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(rapporteurs);
        Map<String, Object> params = new HashMap<String, Object>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, params, ds);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path+"rapporteurs_soutenance.pdf");
        return "path : "+path;
    }

    public Long countByDoctorantId(Long id) {
        return repo.countByDoctorantId(id);
    }
}
