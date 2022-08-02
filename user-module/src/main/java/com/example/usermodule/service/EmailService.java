package com.example.usermodule.service;

import com.example.usermodule.model.EmailDetails;

public interface EmailService {
    public String SendSimpleMail(EmailDetails emailDetails);
    public String SendMailWithAttachment(EmailDetails emailDetails);
}
