package com.hexagonal.apiwebhexa.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.hexagonal.application")
public class ProprieteApplication {

    private String pathToMailTemplate;
    private String smtpPort;
    private String smtpHost;
    private String smtpEmetteur;

    public String getPathToMailTemplate() {
        return pathToMailTemplate;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public String getSmtpEmetteur() {
        return smtpEmetteur;
    }

    public void setPathToMailTemplate(String pathToMailTemplate) {
        this.pathToMailTemplate = pathToMailTemplate;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public void setSmtpEmetteur(String smtpEmetteur) {
        this.smtpEmetteur = smtpEmetteur;
    }
}
