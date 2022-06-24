package com.example.usermodule.service;

import com.example.usermodule.Repository.AuthorityRepo;
import com.example.usermodule.Repository.EncadrantRepo;
import com.example.usermodule.dto.PasswordDto;
import com.example.usermodule.model.Authority;
import com.example.usermodule.model.Encadrant;
import com.example.usermodule.model.ResponsableCED;
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
import java.util.Optional;

@Service
public class EncadrantService {

    @Autowired
    private EncadrantRepo encadrantRepo;
    @Autowired
    private AuthorityRepo authRepo;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public Encadrant findByVkey(String vkey) {
        return encadrantRepo.findByVkey(vkey);
    }

    public int save(Encadrant encadrant) throws NoSuchAlgorithmException {
        if(encadrantRepo.existsByCin(encadrant.getCin())) return -1;
        String originalString = encadrant.getCin()+encadrant.getEmail();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
        String vkey = bytesToHex(encodedhash);
        encadrant.setVkey(vkey);
        Authority auth = authRepo.save(new Authority("Encadrant"));
        List<Authority> authorities = new ArrayList<>();
        authorities.add(auth);
        encadrant.setAuthorities(authorities);
        encadrantRepo.save(encadrant);
        sendSimpleMessage(encadrant.getEmail(), "Compte créer avec succès", "Veuillez définir un mot de passe\n Veuillez clicquer sur le lien suivant pour créer un mot de passe pour votre compte :\nhttp://localhost:3000/encadrant/setpassword/"+vkey);
        return 1;
    }
    public List<Encadrant> findAll() {
        return encadrantRepo.findAll();
    }

    public Optional<Encadrant> findById(Long id) {
        System.out.println("la valeur de l'd envoyer par le front end est : "+id);
        return encadrantRepo.findById(id);
    }

    public Long countData() {
        return encadrantRepo.countNumberOfData();
    }

    public List<Encadrant> findEncadrantsBySpecialite(String specialite) {
        return encadrantRepo.findEncadrantsBySpecialite(specialite);
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

    public void sendSimpleMessage( String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("diversinfo8086@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public int setEncadrantPassword(PasswordDto passwordDto) {
        Encadrant responsableCED = encadrantRepo.findByCin(passwordDto.getCin());
        if(responsableCED!=null){
            String pw_hash = passwordEncoder.encode(passwordDto.getPassword());
            responsableCED.setPassword(pw_hash);
            responsableCED.setEnabled(true);
            return update(responsableCED);
        }
        return -2;
    }

    public int update(Encadrant encadrant){
        if(encadrant.getId()==null || encadrant==null) return -1;
        encadrantRepo.save(encadrant);
        return 1;
    }
}
