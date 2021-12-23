package com.hexagonal.apiwebhexa.domain.usecase;

import com.hexagonal.apiwebhexa.domain.entities.CategorieSocioProfessionnelle;
import com.hexagonal.apiwebhexa.domain.entities.Personne;
import com.hexagonal.apiwebhexa.domain.exception.ReglesMetierException;
import com.hexagonal.apiwebhexa.domain.exception.ValidatorMetierException;
import com.hexagonal.apiwebhexa.domain.port.primaire.GestionHabitants;
import com.hexagonal.apiwebhexa.domain.port.secondaire.HabitantsPort;
import com.hexagonal.apiwebhexa.fixture.HabitantsFixture;
import com.hexagonal.apiwebhexa.infrastructure.database.HabitantH2Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetHabitantsTests {

    private GestionHabitants getHabitants;
    private HabitantsPort habitantsPort;
    private HabitantH2Repository habitantH2Repository;

    @BeforeEach
    void setUp() {
        habitantsPort = new HabitantsDatabaseAdaptaterStub(habitantH2Repository);
        getHabitants = null;
    }

    @Test
    void shoudGetEmailsFromAllHabitants() {
        getHabitants = new GetHabitants(habitantsPort);

        List<Personne> emails = getHabitants.getAllHabitants();
        Personne personne1 = HabitantsFixture.getPersonne1();

        assertFalse(emails.isEmpty());
        assertEquals(2, emails.size());
        assertEquals(HabitantsFixture.getPersonne1(), emails.get(0));
        assertEquals(HabitantsFixture.getPersonne2(), emails.get(1));
    }

    @Test
    void shoudGetAllHabitantsParCategorieSocioProfesionnelleAgriculteur() {
        getHabitants = new GetHabitants(habitantsPort);

        List<Personne> emails = getHabitants.getAllHabitantsParCategorieSocioProfessionnelle(CategorieSocioProfessionnelle.AGRICULTEURS.getCodeValue());

        assertFalse(emails.isEmpty());
        assertEquals(1, emails.size());
        assertEquals(HabitantsFixture.getPersonne1(), emails.get(0));
    }

    @Test
    void shoudGetAllHabitantsParCategorieSocioProfesionnelleArtisantEtCommercant() {
        getHabitants = new GetHabitants(habitantsPort);

        List<Personne> emails = getHabitants.getAllHabitantsParCategorieSocioProfessionnelle(CategorieSocioProfessionnelle.ARTISANS_COMMERCANTS.getCodeValue());

        assertFalse(emails.isEmpty());
        assertEquals(1, emails.size());
        assertEquals(HabitantsFixture.getPersonne2(), emails.get(0));
    }

    @Test
    void shoudGetNoneHabitantsBecauseCategorieSocioProfessionnelleDoesNotExist() {
        getHabitants = new GetHabitants(habitantsPort);

        assertThrows(ValidatorMetierException.class, () -> {
            getHabitants.getAllHabitantsParCategorieSocioProfessionnelle(32);
        });
    }

    @Test
    void shoudGetAllHabitantsPaTrancheDageEntre20Et30() {
        getHabitants = new GetHabitants(habitantsPort);

        List<Personne> emails = getHabitants.getAllHabitantsParTrancheAge(20, 30);

        assertFalse(emails.isEmpty());
        assertEquals(1, emails.size());
        assertEquals(HabitantsFixture.getPersonne1(), emails.get(0));
    }

    @Test
    void shoudGetAllHabitantsPaTrancheDageEntre50Et60() {
        getHabitants = new GetHabitants(habitantsPort);

        List<Personne> emails = getHabitants.getAllHabitantsParTrancheAge(50, 60);

        assertFalse(emails.isEmpty());
        assertEquals(1, emails.size());
        assertEquals(HabitantsFixture.getPersonne2(), emails.get(0));
    }

    @Test
    void shoudNotGetHabitantsBecauseAgeMinimumIsNegatif() {
        getHabitants = new GetHabitants(habitantsPort);

        assertThrows(ValidatorMetierException.class, () -> {
            getHabitants.getAllHabitantsParTrancheAge(-2, 60);
        });
    }

    @Test
    void shoudNotGetHabitantsBecauseAgeMaximumIsNegatif() {
        getHabitants = new GetHabitants(habitantsPort);

        assertThrows(ValidatorMetierException.class, () -> {
            getHabitants.getAllHabitantsParTrancheAge(10, -2);
        });
    }

    @Test
    void shoudNotGetHabitantsBecauseAgeMaximumIsSmallerThanAgeMinimum() {
        getHabitants = new GetHabitants(habitantsPort);

        assertThrows(ValidatorMetierException.class, () -> {
            getHabitants.getAllHabitantsParTrancheAge(10, 2);
        });
    }
}
