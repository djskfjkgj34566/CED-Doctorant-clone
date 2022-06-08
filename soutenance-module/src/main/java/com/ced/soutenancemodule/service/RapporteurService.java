package com.ced.soutenancemodule.service;

import com.ced.soutenancemodule.model.Rapporteur;
import com.ced.soutenancemodule.repository.RapporteurRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RapporteurService {

    @Autowired
    private RapporteurRepo repo;

    @Autowired
    private ResourceLoader resourceLoader;

    public int save(Rapporteur rapporteur){
        int nbr = countByDoctorantId(rapporteur.getDoctorantId());
        rapporteur.setIndice(nbr+1);
        repo.save(rapporteur);
        return 1;
    }

    public List<Rapporteur> findByDoctorantId(Long id) {
        return repo.findByDoctorantId(id);
    }

    public ResponseEntity<byte[]> exportReport(Long doctorantId) throws IOException, JRException {
        /*String path = "D://JasperRepports//";
        List<Rapporteur> rapporteurs = findByDoctorantId(doctorantId);
        File file = ResourceUtils.getFile("classpath:Rapporteurs.jrxml");
        JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(rapporteurs);
        Map<String, Object> params = new HashMap<String, Object>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, params, ds);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path+"rapporteurs_soutenance.pdf");
        return "path : "+path;*/


        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(findByDoctorantId(doctorantId), false);

        Map<String, Object> parameters = new HashMap<>();

        String path = resourceLoader.getResource("classpath:Rapporteurs.jrxml").getURI().getPath();

        JasperReport compileReport = JasperCompileManager
                .compileReport(new FileInputStream(path));

        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);


        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

        System.err.println(data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);

    }

    public int countByDoctorantId(Long id) {
        return repo.countByDoctorantId(id);
    }
}
