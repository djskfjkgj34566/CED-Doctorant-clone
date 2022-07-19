package com.ced.soutenancemodule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfiguration {

    @Bean
    public SimpleMailMessage emailTemplate() {
        //SimpleMailMessagepublic SimpleMailMessage emailTemplate()
        SimpleMailMessage message = new SimpleMailMessage();

            message.setTo("gofresnel@gmail.com");
            message.setFrom("iversinfo8086@gmail.com");
            message.setSubject("Testing the Spring Boot Email");
            message.setText("First successful Email using the Spring Boot");

            return message;
    }

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("diversinfo8086@gmail.com");
        mailSender.setPassword("ltlrzvpewjyzhdzl");

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);

        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }
}
