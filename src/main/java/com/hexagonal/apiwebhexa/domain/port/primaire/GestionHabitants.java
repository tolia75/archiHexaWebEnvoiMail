package com.hexagonal.apiwebhexa.domain.port.primaire;

import com.hexagonal.apiwebhexa.domain.entities.Personne;

import java.util.List;

public interface GestionHabitants {

    List<Personne> getAllHabitants() ;

    List<Personne> getAllHabitantsParTrancheAge(int ageMinimum, int ageMaximum);

    List<Personne> getAllHabitantsParCategorieSocioProfessionnelle(int codeCategorieSocioProfesionnelle);


}
