package com.hexagonal.apiwebhexa.fixture;

import com.hexagonal.apiwebhexa.domain.entities.CategorieSocioProfessionnelle;
import com.hexagonal.apiwebhexa.domain.entities.Personne;

import java.util.List;

public class HabitantsFixture {

    public static List<Personne> getAllHabitants() {
        Personne personne1 = getPersonne1();
        Personne personne2 = getPersonne2();

        return List.of(personne1, personne2);
    }

    public static List<Personne> getAllHabitantsWithGoodMails() {
        Personne personne1 = getPersonne1();
        Personne personne2 = getPersonne2();

        return List.of(personne1, personne2);
    }

    public static List<Personne> getHabitantsWithBadMails() {
        Personne personne1 = getPersonne1WithWrongMail();
        Personne personne2 = getPersonne2WithWrongMail();
        Personne personne3 = getPersonne3WithWrongMail();

        return List.of(personne1, personne2, personne3);
    }

    public static List<Personne> getHabitantsWithOneGoodMails() {
        Personne personne1 = getPersonne1();
        Personne personne2 = getPersonne2WithWrongMail();
        Personne personne3 = getPersonne3WithWrongMail();

        return List.of(personne1, personne2, personne3);
    }

    public static List<Personne> getNoOne() {
        return List.of();
    }

    public static Personne getPersonne1() {
        return new Personne.PersoneBuilder()
                .id(Long.valueOf(1))
                .nom("DUPONT")
                .prenom("Thierry")
                .age(25)
                .mail("thierry.dupont@mail.com")
                .categorieSocioProfessionelle(CategorieSocioProfessionnelle.AGRICULTEURS)
                .build();
    }

    public static Personne getPersonne2() {
        return new Personne.PersoneBuilder()
                .id(Long.valueOf(2))
                .nom("DUPRES")
                .prenom("Martin")
                .age(58)
                .mail("tmartin.dupres@mail.com")
                .categorieSocioProfessionelle(CategorieSocioProfessionnelle.ARTISANS_COMMERCANTS)
                .build();
    }

    public static Personne getPersonne1WithWrongMail() {
        return new Personne.PersoneBuilder()
                .id(Long.valueOf(2))
                .nom("DUPRES")
                .prenom("Martin")
                .age(58)
                .mail("martin.dupresmail.com")
                .categorieSocioProfessionelle(CategorieSocioProfessionnelle.ARTISANS_COMMERCANTS)
                .build();
    }

    public static Personne getPersonne2WithWrongMail() {
        return new Personne.PersoneBuilder()
                .id(Long.valueOf(2))
                .nom("DUPRES")
                .prenom("Martin")
                .age(58)
                .mail("martin.dupres@ma@il.com")
                .categorieSocioProfessionelle(CategorieSocioProfessionnelle.ARTISANS_COMMERCANTS)
                .build();
    }

    public static Personne getPersonne3WithWrongMail() {
        return new Personne.PersoneBuilder()
                .id(Long.valueOf(2))
                .nom("DUPRES")
                .prenom("Martin")
                .age(58)
                .mail("martin.dupres@mailcom")
                .categorieSocioProfessionelle(CategorieSocioProfessionnelle.ARTISANS_COMMERCANTS)
                .build();
    }
}
