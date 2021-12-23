package com.hexagonal.apiwebhexa.infrastructure.mail;

import com.hexagonal.apiwebhexa.domain.exception.EnvoiMailException;
import com.hexagonal.apiwebhexa.domain.port.secondaire.GestionMailPort;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class GestionMailAdapter implements GestionMailPort {

    private static final String DETECTION_VARIABLE_A_REMPLACER = "#";
    private String smtpPort;
    private String smtpHost;
    private String smtpEmetteur;

    public GestionMailAdapter(String smtpPort, String smtpHost, String smtpEmetteur) {
        this.smtpPort = smtpPort;
        this.smtpHost = smtpHost;
        this.smtpEmetteur = smtpEmetteur;
    }

    @Override
    public void envoyerMail(List<String> emails, String objet, String pathToTemplate, List<Map<String, String>> donneesPourMails) {
        emails.forEach(emailAdresse ->
            donneesPourMails.forEach(donneesPourMail -> {
                try {
                    Properties properties = new Properties();

                    Session session = Session.getDefaultInstance(properties);
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(smtpEmetteur));

                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailAdresse));
                    message.setSubject(objet);

                    String corpsDuMessage = new String(Files.readAllBytes(Paths.get(pathToTemplate)));
                    for (Map.Entry<String, String> donnee : donneesPourMail.entrySet()) {
                        corpsDuMessage = corpsDuMessage.replaceAll(DETECTION_VARIABLE_A_REMPLACER + donnee.getKey(), donnee.getValue());
                    }
                    message.setText(corpsDuMessage);

                    Transport.send(message);

                } catch (MessagingException | IOException e) {
                    throw new EnvoiMailException(e.getMessage());
                }
            })
        );
    }
}
