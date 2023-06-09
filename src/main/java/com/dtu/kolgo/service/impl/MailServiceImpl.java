package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.MailDto;
import com.dtu.kolgo.service.MailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String username;
    @Value("${mail.display-name}")
    private String senderName;
    private final JavaMailSender mailSender;

    public void send(MailDto details, boolean isHtml) {
        // Creating a mime message
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        // Try block to check for exceptions
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(username, senderName);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setSubject(details.getSubject());
            mimeMessageHelper.setText(details.getBody(), isHtml);

            mailSender.send(mimeMessage);
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            log.error("Mail Service Exception", e);
        }
    }

    public String sendMailWithAttachment(MailDto details) {
        // Creating a mime message
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            // Setting multipart as true for attachments to be sent
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setFrom(username, senderName);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setSubject(details.getSubject());
            mimeMessageHelper.setText(details.getBody());

            // Adding the attachment
            FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
            mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

            // Sending the mail
            mailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (Exception e) {
            // Display message when exception occurred
            log.error("Mail Service Exception", e);
            return "Error while sending mail !!";
        }
    }

}
