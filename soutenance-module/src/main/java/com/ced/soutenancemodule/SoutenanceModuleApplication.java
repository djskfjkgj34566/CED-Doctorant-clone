package com.ced.soutenancemodule;

import com.ced.soutenancemodule.service.EmailSenderService;
import com.ced.soutenancemodule.service.EmailSenderService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class SoutenanceModuleApplication  implements CommandLineRunner {
    /*@Autowired
    private EmailSenderService1 service;*/
    @Autowired
    private EmailSenderService emailSendService;

    public static void main(String[] args) {
        SpringApplication.run(SoutenanceModuleApplication.class, args);
    }

    @Override
    public void run(String args[])
    {
        //emailSendService.sendEMail("gofresnel@gmail.com", "TBS", "Tech Blog Station");
        //emailSendService.sendPreConfiguredMail("Tech Blog Station");
    }

    @Bean
    public FilterRegistrationBean simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // *** URL below needs to match the Vue client URL and port ***
        List<String> origins = new ArrayList<>();
        origins.add("http://localhost:3000");
        origins.add("http://localhost:3006");
        config.setAllowedOrigins(origins);
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }



   /* @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() throws MessagingException {

        /*service.sendEmailWithAttachment("spring.email.to@gmail.com",
                "This is Email Body with Attachment...",
                "This email has attachment",
                "C:\\Users\\shabb\\Pictures\\c.gif");

        service.sendSimpleEmail(
                "spring.email.to@gmail.com",
                "This is Email Body with Attachment...",
                "This email has attachment"
        );
    }*/

}