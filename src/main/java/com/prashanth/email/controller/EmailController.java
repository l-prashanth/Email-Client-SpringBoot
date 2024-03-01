package com.prashanth.email.controller;

import com.prashanth.email.model.InputFields;
import com.prashanth.email.service.EmailSenderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class EmailController {

    private EmailSenderService emailSenderService;

    @GetMapping("/send-mail")
    public String showForm(Model model, @ModelAttribute InputFields inputFields) {
        emailSenderService.handleModel(model,inputFields);

        return "sendmail";
    }

    @PostMapping("/processForm")
    public String submitCreditCard(Model model,InputFields inputFields)
                                   {
        emailSenderService.sendSimpleEmail(model,inputFields.getRecipient(), inputFields.getSubject(), inputFields.getBody());
        return "redirect:/sendmail";
    }
}
