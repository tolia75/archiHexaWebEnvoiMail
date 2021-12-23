package com.hexagonal.apiwebhexa.domain.usecase;

import com.hexagonal.apiwebhexa.domain.entities.CategorieSocioProfessionnelle;
import com.hexagonal.apiwebhexa.domain.entities.Personne;
import com.hexagonal.apiwebhexa.fixture.HabitantsFixture;
import com.hexagonal.apiwebhexa.infrastructure.database.HabitantH2Repository;
import com.hexagonal.apiwebhexa.infrastructure.database.HabitantsDatabaseAdaptater;

import java.util.List;
import java.util.stream.Collectors;

public class HabitantsDatabaseAdaptaterStub extends HabitantsDatabaseAdaptater {

    public HabitantsDatabaseAdaptaterStub(HabitantH2Repository habitantH2Repository) {
        super(habitantH2Repository);
    }

    @Override
    public List<Personne> getAllHabitants() {
        return HabitantsFixture.getAllHabitants();
    }

    @Override
    public List<Personne> getAllHabitantsParCategorieSocioProfesionnelle(CategorieSocioProfessionnelle categorieSocioProfessionnelle) {
        return HabitantsFixture.getAllHabitants().stream().filter(habitant ->
                habitant.getCategorieSocioProfessionelle().getCodeValue() == categorieSocioProfessionnelle.getCodeValue())
                .collect(Collectors.toList());
    }

    @Override
    public List<Personne> getAllHabitantsParTrancheAge(int ageMinimum, int ageMaximum) {
        return HabitantsFixture.getAllHabitants().stream().filter(habitant ->
                ageMinimum <= habitant.getAge() && habitant.getAge() <= ageMaximum )
                .collect(Collectors.toList());
    }
}
