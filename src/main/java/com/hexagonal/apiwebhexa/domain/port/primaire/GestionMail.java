package com.hexagonal.apiwebhexa.domain.port.primaire;

import com.hexagonal.apiwebhexa.domain.entities.Personne;

import java.util.List;

public interface GestionMail {

    void envoyerMail(List<Personne> destinataires, String sujet);

}
