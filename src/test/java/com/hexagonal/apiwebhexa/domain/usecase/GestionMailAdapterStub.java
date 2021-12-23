package com.hexagonal.apiwebhexa.domain.usecase;

import com.hexagonal.apiwebhexa.infrastructure.mail.GestionMailAdapter;

import java.util.List;
import java.util.Map;

public class GestionMailAdapterStub extends GestionMailAdapter {

    private List<String> emailsRecu;
    private String pathToTemplate;
    private List<Map<String, String>> donneesPourMail;

    public GestionMailAdapterStub(String smtpPort, String smtpHost, String smtpEmetteur) {
        super(smtpPort, smtpHost, smtpEmetteur);
    }

    @Override
    public void envoyerMail(List<String> emails, String objet, String pathToTemplate, List<Map<String, String>> donneesPourMails) {
        this.emailsRecu = null;
        this.pathToTemplate = "";
        this.donneesPourMail = null;

        this.emailsRecu = emails;
        this.pathToTemplate = pathToTemplate;
        this.donneesPourMail = donneesPourMails;
    }

    public List<String> getEmailsRecu() {
        return emailsRecu;
    }

    public String getPathToTemplate() {
        return pathToTemplate;
    }

    public List<Map<String, String>> getDonneesPourMail() {
        return donneesPourMail;
    }
}
