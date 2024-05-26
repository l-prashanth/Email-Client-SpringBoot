package com.prashanth.email.service;

import com.prashanth.email.model.InputFields;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface EmailSenderService {


    void sendSimpleEmail(String toEmail,String name,
                         String subject,
                         String body,String bcc,String cc, List<MultipartFile> attachments
    );

}
