package com.hexagonal.apiwebhexa.domain.usecase;

import com.hexagonal.apiwebhexa.domain.exception.ReglesMetierException;
import com.hexagonal.apiwebhexa.fixture.HabitantsFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PreparerEnvoiMailTests {

    private static final String PRENOM = "prenom";
    private static final String NOM = "nom";
    private PreparerEnvoiMail preparerEnvoiMail;
    private GestionMailAdapterStub gestionMailPort;

    private final String OBJET = "Objet du mail";
    private final String PATH_TO_TEMPLATE = "src/main/resources/templates/mailTemplate.txt";

    private Map<String, String> donneesPourMail;


    @BeforeEach
    void setUp() {
        gestionMailPort = new GestionMailAdapterStub("smtpPort", "smtpHost", "smtpEmeteur");
        preparerEnvoiMail = new PreparerEnvoiMail(gestionMailPort, PATH_TO_TEMPLATE);
        donneesPourMail = new HashMap<>();
    }

    @Test
    void shouldNotWorkWithNoMail() {
        assertThrows(ReglesMetierException.class, () -> {
            preparerEnvoiMail.envoyerMail(HabitantsFixture.getNoOne(), OBJET);
        });
    }

    @Test
    void shouldNotSendMailsBecauseOfBadMail() {
        assertThrows(ReglesMetierException.class, () -> {
            preparerEnvoiMail.envoyerMail(HabitantsFixture.getHabitantsWithBadMails(), OBJET);
        });
    }

    @Test
    void shouldOnlySendGoodMails() {
        preparerEnvoiMail.envoyerMail(HabitantsFixture.getHabitantsWithOneGoodMails(), OBJET);

        assertEquals(1, gestionMailPort.getEmailsRecu().size());
        assertEquals("thierry.dupont@mail.com", gestionMailPort.getEmailsRecu().get(0));
        assertEquals(1, gestionMailPort.getDonneesPourMail().size());
        assertEquals("Thierry", gestionMailPort.getDonneesPourMail().get(0).get(PRENOM));
        assertEquals("DUPONT", gestionMailPort.getDonneesPourMail().get(0).get(NOM));
    }

    @Test
    void shouldSendAllMails() {
        preparerEnvoiMail.envoyerMail(HabitantsFixture.getAllHabitantsWithGoodMails(), OBJET);

        assertEquals(2, gestionMailPort.getEmailsRecu().size());
        assertEquals(2, gestionMailPort.getDonneesPourMail().size());
        assertEquals("src/main/resources/templates/mailTemplate.txt", gestionMailPort.getPathToTemplate());
    }
}
