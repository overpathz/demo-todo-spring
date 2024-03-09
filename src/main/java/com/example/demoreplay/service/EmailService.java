package com.example.demoreplay.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSenderImpl javaMailSender;
    private final Environment env;
    private String senderEmail;

    @PostConstruct
    public void init() {
        senderEmail = env.getProperty("SPRING_MAIL_USERNAME");
        String password = env.getProperty("SPRING_MAIL_PASSWORD");
        javaMailSender.setUsername(senderEmail);
        javaMailSender.setPassword(password);
    }

    public void sendEmail(String to, String subject, String text) {
        log.info("Sending email to={}", to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to); // mail
        message.setFrom(senderEmail);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
