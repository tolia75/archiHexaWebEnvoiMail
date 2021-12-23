package com.hexagonal.apiwebhexa.app.rest;

import com.hexagonal.apiwebhexa.domain.entities.Personne;
import com.hexagonal.apiwebhexa.domain.port.secondaire.GestionMailPort;
import com.hexagonal.apiwebhexa.domain.usecase.PreparerEnvoiMail;

import java.util.List;

public class PreparerEnvoiMailStub extends PreparerEnvoiMail {

    public PreparerEnvoiMailStub(GestionMailPort gestionMailPort, String pathToMailTemplate) {
        super(gestionMailPort, pathToMailTemplate);
    }

    @Override
    public void envoyerMail(List<Personne> destinataires, String sujet) {
        super.envoyerMail(destinataires, sujet);
    }
}
