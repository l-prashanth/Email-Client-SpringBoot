package com.prashanth.email.service;

import com.prashanth.email.model.InputFields;
import com.prashanth.email.utils.ModelUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.TriConsumer;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

import static com.prashanth.email.constants.Constants.MY_MAIL;
import static com.prashanth.email.model.enums.OperationId.EMAIL_FIELDS;
import static com.prashanth.email.utils.CommonUtil.*;
import static com.prashanth.email.utils.CommonUtil.messageFillerWithInputName;

@Service
@AllArgsConstructor
@Slf4j
public class EmailSenderServiceImpl implements EmailSenderService {
    private JavaMailSender javaMailSender;

    @Override
    @SneakyThrows
    public void sendSimpleEmail(String toEmail, String name,
                                String subject,
                                String body, String bcc, String cc, List<MultipartFile> attachments) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        message.setFrom(MY_MAIL);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        String contentBody;
        // Add attachments only if the list is not empty and the attachment name is not blank
        if (isNotNullOrEmpty(attachments)) {
            for (MultipartFile attachment : attachments) {
                if (isNotNullOrEmpty(attachment) && isNotNullOrEmpty(Objects.requireNonNull(attachment.getOriginalFilename()).trim())) {
                    ByteArrayResource byteArrayResource = new ByteArrayResource(attachment.getBytes());
                    helper.addAttachment(Objects.requireNonNull(attachment.getOriginalFilename()), byteArrayResource);
                }
            }
        }

        if (isNullOrEmpty(name) && (isNotNullOrEmpty(bcc) || isNotNullOrEmpty(cc))) {
            contentBody = messageFillerWithoutToName(body);
        } else {
            contentBody = isNotNullOrEmpty(name) ? messageFillerWithInputName(name, body) : messageFiller(toEmail, body);
        }
        setCommonEmailProperties(helper, contentBody, bcc, cc);
        javaMailSender.send(message);
        System.out.println("Mail Sent...");
    }



    private void setCommonEmailProperties(MimeMessageHelper helper, String contentBody, String... values) throws MessagingException {
        if (isNotNullOrEmpty(values[0])) {
            helper.setBcc(values[0]);
            helper.setText(contentBody, true);
            // Set the second parameter to true for HTML content
        }
        if (isNotNullOrEmpty(values[1])) {
            helper.setCc(values[1]);
            helper.setText(contentBody, true);
        }
        if (isNullOrEmpty(values[0], values[1])) {
            helper.setText(contentBody, true);
        }
    }
}
