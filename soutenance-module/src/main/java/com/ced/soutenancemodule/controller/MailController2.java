package com.ced.soutenancemodule.controller;

import com.ced.soutenancemodule.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class MailController2 {
   /* @Autowired
    private MailService notificationService;

    @Autowired
    private User user;

    @RequestMapping("send-mail")
    public String send() {
        user.setEmailAddress("gofresnel@gmail.com");
        try {
            notificationService.sendEmail(user);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Your mail has been send to the user.";
    }
    @RequestMapping("send-mail-attachment")
    public String sendWithAttachment() throws MessagingException {
        user.setEmailAddress("gofresnel@gmail.com");
        try {
            notificationService.sendEmailWithAttachment(user);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Your mail has been send to the user.";
    }*/
}
