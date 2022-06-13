package com.example.usermodule.service;

import com.example.usermodule.Repository.AuthorityRepo;
import com.example.usermodule.Repository.DoctorantRepo;
import com.example.usermodule.dto.PasswordDto;
import com.example.usermodule.dto.PreinscriptionDto;
import com.example.usermodule.fiegn.PreinscriptionFeign;
import com.example.usermodule.model.Authority;
import com.example.usermodule.model.Doctorant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorantService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PreinscriptionFeign preinscriptionFeign;
    @Autowired
    private DoctorantRepo repository;
    @Autowired
    private AuthorityRepo authRepo;
    @Autowired
    private JavaMailSender emailSender;


    public int setDoctorantPassword(PasswordDto passwordDto){
        Doctorant doctorant = repository.findByCin(passwordDto.getCin());
        if(doctorant!=null){
            String pw_hash = passwordEncoder.encode(passwordDto.getPassword());
            doctorant.setPassword(pw_hash);
            doctorant.setEnabled(true);
            return update(doctorant);
        }
        return -2;
    }

    public int update(Doctorant doctorant){
        if(doctorant.getId()==null || doctorant==null) return -1;
        repository.save(doctorant);
        return 1;
    }

    public Doctorant getByVkey(String vkey){
        return repository.findByVkey(vkey);
    }

    public void sendSimpleMessage( String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("diversinfo8086@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public int rejeter(PreinscriptionDto preinscription) {
        preinscription.setStatus("rejete");
        if (preinscriptionFeign.update(preinscription) == 1) {
            sendSimpleMessage(preinscription.getEmail(), "Rejet de la candidature", "Votre candidature à été rejeter à près son traitement");
            return 1;
        }
        return -1;
    }

    public Doctorant save(PreinscriptionDto preinscrition) throws NoSuchAlgorithmException {
        preinscrition.setStatus("accepte");
        if(preinscriptionFeign.update(preinscrition)==1) {
            String originalString = preinscrition.getCin()+preinscrition.getEmail();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
            String vkey = bytesToHex(encodedhash);
            Doctorant docteur = new Doctorant();
            docteur.setSpecialite(preinscrition.getSpecialite());
            docteur.setNom(preinscrition.getNom());
            docteur.setPrenom(preinscrition.getPrenom());
            docteur.setCin(preinscrition.getCin());
            docteur.setEmail(preinscrition.getEmail());
            docteur.setAddress(preinscrition.getAddress());
            docteur.setDate_preinscription(preinscrition.getDate_preinscription());
            docteur.setVkey(vkey);
            Authority auth = authRepo.save(new Authority("DOCTORANT"));
            List<Authority> authorities = new ArrayList<>();
            authorities.add(auth);
            docteur.setAuthorities(authorities);
            repository.save(docteur);
            sendSimpleMessage(preinscrition.getEmail(), "Admition de la préinscription", "votre préinscription est validée\n Veuillez clicquer sur le lien suivant pour créer un mot de passe pour votre compte :\nhttp://localhost:3000/setpassword/"+vkey);
            return docteur;
        }
            return null;
    }

    public List<Doctorant> findAll(){
        return repository.findAll();
    }

    public Doctorant findById(Long id) {
        return repository.findById(id).get();
    }

    public Long countData() {
        return repository.countNumberOfData();
    }

    public String getSpecialite(Long id){
        return repository.findById(id).get().getSpecialite();
    }

    /*
    public AuthenticationResponseDto authentication(AuthenticationRequestDto requestDto, HttpServletRequest request) {
        AuthenticationResponseDto response = new AuthenticationResponseDto();
        Doctorant doctorant = repository.findByEmail(requestDto.getEmail()).get();
        if(doctorant==null){
            response.setDoctorant(null);
            response.setResult(-1);
        }else{
            if(!passwordEncoder.matches(requestDto.getPassword(), doctorant.getPassword())){
                response.setDoctorant(null);
                response.setResult(-2);
            }else{
                response.setDoctorant(doctorant);
                response.setResult(1);
                request.getSession().setAttribute("doctorant", doctorant);
            }
        }
        return response;
    }*/

}
