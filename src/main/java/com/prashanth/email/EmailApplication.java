package com.prashanth.email;

import com.prashanth.email.service.EmailSenderService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@AllArgsConstructor
public class EmailApplication {

	private EmailSenderService emailSenderService;


	public static void main(String[] args) {
		SpringApplication.run(EmailApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() throws MessagingException {
//		emailSenderService.sendSimpleEmail("rockyrocky123.7@gmail.com",
//				"This is email body",
//				"This is email subject");
//
//	}

}
