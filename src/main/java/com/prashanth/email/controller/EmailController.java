package com.prashanth.email.controller;

import com.prashanth.email.model.InputFields;
import com.prashanth.email.service.EmailSenderService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class EmailController {

    private EmailSenderService emailSenderService;

    @GetMapping("/send-mail")
    public String showForm(Model model, @ModelAttribute InputFields inputFields) {
        return "sendmail";
    }

    @PostMapping("/processForm")
    public String submitCreditCard(InputFields inputFields, @RequestParam("attachments") List<MultipartFile> attachments) {
        log.info("ALL" + inputFields);
        emailSenderService.sendSimpleEmail(inputFields.getRecipient(),
                inputFields.getName(), inputFields.getSubject(), inputFields.getBody(), inputFields.getBcc(),
                inputFields.getCc(), attachments);
        return "redirect:/mail-status";
    }

    @GetMapping("/mail-status")
    public String showForm() {
        return "status";
    }
}
