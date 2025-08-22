package com.tempjunior.book_jesus_application.domain.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MailSenderService {
    private final String a = "";

    public void sendEmail(String to, String subject, String name) throws IOException {
        Email from = new Email("junior_zelito17@hotmail.com");
        Email toEmail = new Email(to);
        Content emailContent = new Content("text/html", content(name));
        Mail mail = new Mail(from, subject, toEmail, emailContent);


        SendGrid sg = new SendGrid(a);
        Request request = new Request();

        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody());
        }catch (IOException exception){
            throw new IOException("Error to send email" + exception.getMessage());
        }
    }

    protected String content(String name){
        return """
    <html>
    <head>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }
            .email-container {
                max-width: 600px;
                margin: 20px auto;
                background-color: #ffffff;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            }
            .header {
                background-color: #4CAF50;
                color: white;
                padding: 20px;
                text-align: center;
            }
            .content {
                padding: 30px;
                text-align: left;
            }
            .button {
                display: inline-block;
                padding: 12px 24px;
                background-color: #4CAF50;
                color: #ffffff;
                text-decoration: none;
                border-radius: 5px;
                font-weight: bold;
            }
            .footer {
                background-color: #f0f0f0;
                color: #999999;
                text-align: center;
                padding: 15px;
                font-size: 12px;
            }
        </style>
    </head>
    <body>
        <div class="email-container">
            <div class="header">
                <h1 style="margin: 0;">Bem-vindo(a) à Book-Jesus!</h1>
            </div>
            <div class="content">
                <h2 style="color: #333333;">Olá, %s!</h2>
                <p style="color: #666666; line-height: 1.6;">
                    Seja muito bem-vindo(a) à Livraria Book-Jesus! Ficamos honrados em tê-lo(a) conosco em nossa missão de espalhar a palavra através da leitura.
                </p>
                <p style="color: #666666; line-height: 1.6;">
                    Estamos aqui para ajudá-lo(a) a encontrar os livros perfeitos para enriquecer sua jornada espiritual. Sinta-se à vontade para explorar nosso acervo.
                </p>
                <p style="text-align: center; margin-top: 30px;">
                    <a href="URL_DA_SUA_LOJA" class="button">Explorar Livros</a>
                </p>
            </div>
            <div class="footer">
                <p style="margin: 0;">Com carinho,</p>
                <p style="margin: 0;">Equipe Livraria Book-Jesus</p>
            </div>
        </div>
    </body>
    </html>
""".formatted(name);
    }
}
