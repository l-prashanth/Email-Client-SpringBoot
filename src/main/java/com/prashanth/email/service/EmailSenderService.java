package com.prashanth.email.service;

import com.prashanth.email.model.InputFields;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;

@Service
public interface EmailSenderService {


    void handleModel(Model model, InputFields inputFields);

    void sendSimpleEmail(Model model,String toEmail,
                         String subject,
                         String body
    );

}
