package com.future.restoapp.service;

import javax.mail.MessagingException;
import java.util.Map;

public interface MailService {

    void sendMessageWithTemplate(
                String to, String subject,
                Map<String, Object> templateModel
        ) throws MessagingException;

}
