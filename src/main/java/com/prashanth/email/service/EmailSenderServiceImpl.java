package com.prashanth.email.service;

import com.prashanth.email.model.InputFields;
import com.prashanth.email.utils.ModelUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import static com.prashanth.email.constants.Constants.*;
import static com.prashanth.email.model.enums.OperationId.EMAIL_FIELDS;

@Service
@AllArgsConstructor
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {
    private JavaMailSender javaMailSender;
    private ModelUtil modelUtil;


    public void handleModel(Model model, InputFields inputFields){
        modelUtil.modelHandler(EMAIL_FIELDS,model,inputFields.getRecipient(),inputFields.getSubject(),inputFields.getBody());
    }

    public void sendSimpleEmail(Model model,String toEmail,
                                String subject,
                                String body
    ) {
        log.info(toEmail);
        log.info(subject);
        log.info(body);
//        model.addAttribute(RECIPIENT_MODEL, toEmail);
//        model.addAttribute(SUBJECT_MODEL, subject);
//        model.addAttribute(BODY_MODEL, body);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("prashanth.py0101@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        javaMailSender.send(message);
        System.out.println("Mail Send...");
    }
}
