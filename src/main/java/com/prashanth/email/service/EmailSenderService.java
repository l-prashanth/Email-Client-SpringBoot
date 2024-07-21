package com.prashanth.email.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface EmailSenderService {


    void sendSimpleEmail(String toEmail,String name,
                         String subject,
                         String body,String bcc,String cc, List<MultipartFile> attachments
    );

}