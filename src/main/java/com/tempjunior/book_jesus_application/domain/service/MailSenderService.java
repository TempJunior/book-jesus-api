package com.tempjunior.book_jesus_application.domain.service;

import com.tempjunior.book_jesus_application.domain.dto.usuario_dto.UsuarioCadastroDTO;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailSenderService {
    private final String HOST = "smtp.office365.com";
    private final String email = "junior_sa13tstsp@hotmail.com";
    private final String password = "54050151";


    public void sendEmail(UsuarioCadastroDTO user) throws MessagingException {
        Properties properties = new Properties();

        properties.put("mail.debug", "true");

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("junior_zelito17@hotmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.email()));
        message.setSubject("Que alegria te ver na BOOK-JESUS!!!");


        String htmlContent =
                "<html><body>"
                        + "<h1>Olá, " + user.nome() + "!</h1>"
                        + "<p>Seja bem vindo a nossa livraria <b>Book-Jesus</b>.</p>"
                        + "<p><i>É uma alegria ter você acessando nossa plataforma.</i></p>"
                        + "</body></html>";

        message.setContent(htmlContent, "text/html; charset=utf-8");
        Transport.send(message);
        System.out.println("E-mail enviado com sucesso!");
    }
}
