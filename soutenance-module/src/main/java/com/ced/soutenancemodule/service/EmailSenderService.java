package com.ced.soutenancemodule.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailSendService")
public class EmailSenderService{


    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private SimpleMailMessage preConfiguredMessage;

    public void sendSimpleMessage( String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@gmail.com");
        //message.setFrom("diversinfo8086@gmail.com")
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void EnvoieMessage(String to){
        sendSimpleMessage(to, "demande Rapporteurs", "Merci de corriger le rapport");
    }
    //Method to compose and send the Email
    public void sendEMail(String to, String subject, String text)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    /*Method to send the Pre Configured Email
    public void sendPreConfiguredMail(String message)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }*/
}
