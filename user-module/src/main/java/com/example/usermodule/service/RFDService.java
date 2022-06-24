package com.example.usermodule.service;

import com.example.usermodule.Repository.AuthorityRepo;
import com.example.usermodule.Repository.RFDRepo;
import com.example.usermodule.dto.PasswordDto;
import com.example.usermodule.model.Authority;
import com.example.usermodule.model.Doctorant;
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
public class RFDService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RFDRepo rfdRepo;
    @Autowired
    private AuthorityRepo authRepo;
    @Autowired
    private JavaMailSender emailSender;


    public int setRFDPassword(PasswordDto passwordDto){
        ResponsableFD responsableFD = rfdRepo.findByCin(passwordDto.getCin());
        if(responsableFD!=null){
            String pw_hash = passwordEncoder.encode(passwordDto.getPassword());
            responsableFD.setPassword(pw_hash);
            responsableFD.setEnabled(true);
            return update(responsableFD);
        }
        return -2;
    }

    public int update(ResponsableFD rfd){
        if(rfd.getId()==null || rfd==null) return -1;
        rfdRepo.save(rfd);
        return 1;
    }

    public ResponsableFD getByVkey(String vkey){
        return rfdRepo.findByVkey(vkey);
    }



    public int save(ResponsableFD rfd) throws NoSuchAlgorithmException {
        if(rfdRepo.existsByCin(rfd.getCin())) return -1;
        String originalString = rfd.getCin()+rfd.getEmail();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
        String vkey = bytesToHex(encodedhash);
        rfd.setVkey(vkey);
        Authority auth = authRepo.save(new Authority("RESPONSABLEFD"));
        List<Authority> authorities = new ArrayList<>();
        authorities.add(auth);
        rfd.setAuthorities(authorities);
        rfdRepo.save(rfd);
        sendSimpleMessage(rfd.getEmail(), "Compte créer avec succès", "Veuillez définir un mot de passe\n Veuillez clicquer sur le lien suivant pour créer un mot de passe pour votre compte :\nhttp://localhost:3006/rfd/setpassword/"+vkey);
        return 1;
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


    public List<ResponsableFD> findAll() {
        return rfdRepo.findAll();
    }

    public Optional<ResponsableFD> findById(Long aLong) {
        return rfdRepo.findById(aLong);
    }
}
