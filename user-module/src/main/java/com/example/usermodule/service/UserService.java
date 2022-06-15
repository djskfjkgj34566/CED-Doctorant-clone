package com.example.usermodule.service;

import com.example.usermodule.Repository.UserRepo;
import com.example.usermodule.dto.PasswordDto;
import com.example.usermodule.model.Doctorant;
import com.example.usermodule.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public int sendPageUpdatePassword(String email) throws NoSuchAlgorithmException {
        if(!userRepo.existsByEmail(email)) return -1;
        User user = userRepo.findByEmail(email).get();
        user.setEnabled(false);
        if(update(user)==1){
            String vkey = user.getVkey();
            String to = email;
            String subject = "Réinitialisation de mot de passe";
            String text1 = "Vueillez cliquer sur le lien suivant pour réinitialiser votre mot de passe :\n " +
                    "http://localhost:3000/user/update/password/"+vkey;
            String text2 = "Vueillez cliquer sur le lien suivant pour réinitialiser votre mot de passe :\n " +
                    "http://localhost:3006/user/update/password/"+vkey;
            if(user.getAuthorities().contains("DOCTORANT") || user.getAuthorities().contains("Encadrant")){
                sendSimpleMessage(to, subject, text1);
            }else{
                sendSimpleMessage(to, subject, text2);
            }
            return 1;
        }
        return -2;

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

    public int updateUserPassword(PasswordDto passwordDto) {
        User user = userRepo.findByCin(passwordDto.getCin());
        user.setEnabled(true);
        String pw_hash = passwordEncoder.encode(passwordDto.getPassword());
        user.setPassword(pw_hash);
        return update(user);
    }

    public int update(User user){
        if(user.getId()==null || user==null) return -1;
        userRepo.save(user);
        return 1;
    }

    public User getByVkey(String vkey) {
        return userRepo.findByVkey(vkey);
    }
}
