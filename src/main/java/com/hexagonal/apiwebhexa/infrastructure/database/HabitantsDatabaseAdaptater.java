package com.hexagonal.apiwebhexa.infrastructure.database;

import com.hexagonal.apiwebhexa.domain.entities.CategorieSocioProfessionnelle;
import com.hexagonal.apiwebhexa.domain.entities.Personne;
import com.hexagonal.apiwebhexa.domain.port.secondaire.HabitantsPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HabitantsDatabaseAdaptater implements HabitantsPort {

    private HabitantH2Repository habitantH2Repository;

    public HabitantsDatabaseAdaptater(HabitantH2Repository habitantH2Repository) {
        this.habitantH2Repository = habitantH2Repository;
    }

    @Override
    public List<Personne> getAllHabitants() {
        return habitantH2Repository.findAll()
                .stream().map(this::personneDatabaseToPersonne).collect(Collectors.toList());
    }

    @Override
    public List<Personne> getAllHabitantsParCategorieSocioProfesionnelle(CategorieSocioProfessionnelle categorieSocioProfesionnelle) {
        return habitantH2Repository.findAllByCategorieSocioProfessionnelle(categorieSocioProfesionnelle)
                .stream().map(this::personneDatabaseToPersonne).collect(Collectors.toList());
    }

    @Override
    public List<Personne> getAllHabitantsParTrancheAge(int ageMinimum, int ageMaximum) {
        return habitantH2Repository.findAllByAgeIsBetween(ageMinimum, ageMaximum)
                .stream().map(this::personneDatabaseToPersonne).collect(Collectors.toList());
    }

    public Personne personneDatabaseToPersonne(PersonneDataBase personneDataBase) {
        return new Personne.PersoneBuilder()
                .id(personneDataBase.getId())
                .nom(personneDataBase.getNom())
                .prenom(personneDataBase.getPrenom())
                .age(personneDataBase.getAge())
                .mail(personneDataBase.getEmail())
                .categorieSocioProfessionelle(personneDataBase.getCategorieSocioProfessionnelle())
                .build();
    }

    public PersonneDataBase personneToPersonneDatabase(Personne personne) {
        return new PersonneDataBase(
                personne.getId(), personne.getPrenom(), personne.getNom(), personne.getAge(), personne.getEmail(), personne.getCategorieSocioProfessionelle()
        );
    }
}
