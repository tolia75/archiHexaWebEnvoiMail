package com.hexagonal.apiwebhexa.domain.port.secondaire;

import com.hexagonal.apiwebhexa.domain.exception.EnvoiMailException;

import java.util.List;
import java.util.Map;

public interface GestionMailPort {

    void envoyerMail(List<String> mails, String objet, String pathToTemplate, List<Map<String, String>> donneesPourMails) throws EnvoiMailException;
}
