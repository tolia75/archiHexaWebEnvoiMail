package com.hexagonal.apiwebhexa.infrastructure.database;

import com.hexagonal.apiwebhexa.domain.entities.CategorieSocioProfessionnelle;
import com.hexagonal.apiwebhexa.domain.entities.Personne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HabitantsDatabaseAdaptaterTest {

    private HabitantsDatabaseAdaptater habitantsDatabaseAdaptater;
    private HabitantH2Repository habitantH2Repository;

    @BeforeEach
    void setUp() {
        habitantsDatabaseAdaptater = new HabitantsDatabaseAdaptater(habitantH2Repository);
    }

    @Test
    void shouldMapPersonneToPersonneDataBase() {
        Personne personne = new Personne.PersoneBuilder()
                .id(Long.valueOf(1))
                .nom("nom")
                .prenom("prenom")
                .age(25)
                .mail("email")
                .categorieSocioProfessionelle(CategorieSocioProfessionnelle.AGRICULTEURS)
                .build();

        PersonneDataBase personneDataBase = habitantsDatabaseAdaptater.personneToPersonneDatabase(personne);

        assertEquals(personne.getId(), personneDataBase.getId());
        assertEquals(personne.getPrenom(), personneDataBase.getPrenom());
        assertEquals(personne.getNom(), personneDataBase.getNom());
        assertEquals(personne.getAge(), personneDataBase.getAge());
        assertEquals(personne.getEmail(), personneDataBase.getEmail());
        assertEquals(personne.getCategorieSocioProfessionelle(), personneDataBase.getCategorieSocioProfessionnelle());
    }

    @Test
    void shouldMapPersonneDataBaseToPersonne() {
        PersonneDataBase personneDataBase = new PersonneDataBase(Long.valueOf(1), "prenom", "nom", 25, "email", CategorieSocioProfessionnelle.AGRICULTEURS);

        Personne personne = habitantsDatabaseAdaptater.personneDatabaseToPersonne(personneDataBase);

        assertEquals(personneDataBase.getId(), personne.getId());
        assertEquals(personneDataBase.getPrenom(), personne.getPrenom());
        assertEquals(personneDataBase.getNom(), personne.getNom());
        assertEquals(personneDataBase.getAge(), personne.getAge());
        assertEquals(personneDataBase.getEmail(), personne.getEmail());
        assertEquals(personneDataBase.getCategorieSocioProfessionnelle(), personne.getCategorieSocioProfessionelle());
    }
}
