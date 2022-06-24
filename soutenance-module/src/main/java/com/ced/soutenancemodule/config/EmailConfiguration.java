package com.ced.soutenancemodule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

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
}
