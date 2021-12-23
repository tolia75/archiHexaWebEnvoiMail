package com.hexagonal.apiwebhexa.domain.usecase;

import com.hexagonal.apiwebhexa.domain.entities.Personne;
import com.hexagonal.apiwebhexa.domain.exception.ReglesMetierException;
import com.hexagonal.apiwebhexa.domain.port.primaire.GestionMail;
import com.hexagonal.apiwebhexa.domain.port.secondaire.GestionMailPort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PreparerEnvoiMail implements GestionMail {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final String PRENOM = "prenom";
    private static final String NOM = "nom";

    private GestionMailPort gestionMailPort;
    private String pathToMailTemplate;

    public PreparerEnvoiMail(GestionMailPort gestionMailPort, String pathToMailTemplate) {
        this.gestionMailPort = gestionMailPort;
        this.pathToMailTemplate = pathToMailTemplate;
    }

    public void envoyerMail(List<Personne> destinataires, String sujet) {
        final List<Personne> destinatairesVerifies = verifieDestinataires(destinataires);
        final List<String> adressesMailsVerifiees = destinatairesVerifies.stream().map(Personne::getEmail).collect(Collectors.toList());
        final List<Map<String, String>> donneesPourMails = getDonneesDestinataires(destinatairesVerifies);

        gestionMailPort.envoyerMail(adressesMailsVerifiees, sujet, pathToMailTemplate, donneesPourMails);
    }

    private List<Map<String, String>> getDonneesDestinataires(List<Personne> destinatairesVerifies) {
        final List<Map<String, String>> donneesPourMails = new ArrayList<>();

        destinatairesVerifies.stream().forEach(destinataire -> {
            Map<String, String> donneesPourMailUnique = new HashMap<>();
            donneesPourMailUnique.put(PRENOM, destinataire.getPrenom());
            donneesPourMailUnique.put(NOM, destinataire.getNom());
            donneesPourMails.add(donneesPourMailUnique);
        });
        return donneesPourMails;
    }

    private List<Personne> verifieDestinataires(List<Personne> destinataires) {
        final List<String> adressesMails = destinataires.stream().map(Personne::getEmail).collect(Collectors.toList());
        adressesMails.stream().findFirst().orElseThrow(() -> new ReglesMetierException("Aucun mail n'est remonté. Envoi annulé"));
        final List<String> adressesMailsVerifiees =  adressesMails.stream().filter(mail -> VALID_EMAIL_ADDRESS_REGEX.matcher(mail).find())
                .collect(Collectors.toList());
        adressesMailsVerifiees.stream().findFirst().orElseThrow(() -> new ReglesMetierException("Aucun mail reçu n'est valide"));

        List<Personne> destinatairesVerifies;
        if (adressesMails.size() != adressesMailsVerifiees.size()) {
            destinatairesVerifies = destinataires.stream()
                    .filter(destinataire -> adressesMailsVerifiees.stream().anyMatch(mailVerifie -> destinataire.getEmail().matches(mailVerifie)))
                    .collect(Collectors.toList());
        } else {
            destinatairesVerifies = destinataires;
        }

        return destinatairesVerifies;
    }
}
