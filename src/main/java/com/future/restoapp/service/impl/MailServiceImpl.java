package com.future.restoapp.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.future.restoapp.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class MailServiceImpl implements MailService {

    private static final String NOREPLY_ADDRESS = "noreply@baeldung.com";

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("classpath:/mail-logo.png")
    private Resource resourceFile;

    @Override
    public void sendMessageWithTemplate(
                String to, String subject,
                Map<String, Object> templateModel,
                String pathToAttachment
        ) throws MessagingException {
        Context context = new Context();

        context.setVariables(templateModel);

        String htmlBody = templateEngine.process("mail/reservation", context);

        sendHtmlMessage(to, subject, htmlBody, pathToAttachment);
    }

    private void sendHtmlMessage(String to, String subject,
                                 String htmlBody, String pathToAttachment
        ) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(NOREPLY_ADDRESS);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        if(pathToAttachment != null){
            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);
        }

        emailSender.send(message);
    }

}
