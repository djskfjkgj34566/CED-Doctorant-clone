package com.example.usermodule.service;

import com.example.usermodule.Repository.AuthorityRepo;
import com.example.usermodule.Repository.ResponsableCEDRepo;
import com.example.usermodule.dto.PasswordDto;
import com.example.usermodule.model.Authority;
import com.example.usermodule.model.Encadrant;
import com.example.usermodule.model.ResponsableCED;
import com.example.usermodule.model.ResponsableFD;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RCEDService {

    @Autowired
    private ResponsableCEDRepo responsableCEDRepo;
    @Autowired
    private AuthorityRepo authRepo;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public int save(ResponsableCED responsableCED) throws NoSuchAlgorithmException {
        if(responsableCEDRepo.existsByCin(responsableCED.getCin())) return -1;
        String originalString = responsableCED.getCin()+responsableCED.getEmail();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
        String vkey = bytesToHex(encodedhash);
        responsableCED.setVkey(vkey);
        Authority auth = authRepo.save(new Authority("RESPONSABLECED"));
        List<Authority> authorities = new ArrayList<>();
        authorities.add(auth);
        responsableCED.setAuthorities(authorities);
        responsableCEDRepo.save(responsableCED);
        sendSimpleMessage(responsableCED.getEmail(), "Compte créer avec succès", "Veuillez définir un mot de passe\n Veuillez clicquer sur le lien suivant pour créer un mot de passe pour votre compte :\nhttp://localhost:3006/rced/setpassword/"+vkey);
        return 1;
    }

    public List<ResponsableCED> findAll() {
        return responsableCEDRepo.findAll();
    }

    public Optional<ResponsableCED> findById(Long aLong) {
        return responsableCEDRepo.findById(aLong);
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

    public ResponsableCED getByVkey(String vkey){
        return responsableCEDRepo.findByVkey(vkey);
    }

    public int setRCEDPassword(PasswordDto passwordDto) {
        ResponsableCED responsableCED = responsableCEDRepo.findByCin(passwordDto.getCin());
        if(responsableCED!=null){
            String pw_hash = passwordEncoder.encode(passwordDto.getPassword());
            responsableCED.setPassword(pw_hash);
            responsableCED.setEnabled(true);
            return update(responsableCED);
        }
        return -2;
    }

    public int update(ResponsableCED rced){
        if(rced.getId()==null || rced==null) return -1;
        responsableCEDRepo.save(rced);
        return 1;
    }
}
