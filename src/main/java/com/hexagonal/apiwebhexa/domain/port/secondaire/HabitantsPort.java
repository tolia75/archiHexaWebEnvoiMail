package com.hexagonal.apiwebhexa.domain.port.secondaire;

import com.hexagonal.apiwebhexa.domain.entities.CategorieSocioProfessionnelle;
import com.hexagonal.apiwebhexa.domain.entities.Personne;

import java.util.List;

public interface HabitantsPort {

    List<Personne> getAllHabitants();

    List<Personne> getAllHabitantsParCategorieSocioProfesionnelle(CategorieSocioProfessionnelle categorieSocioProfessionnelle);

    List<Personne> getAllHabitantsParTrancheAge(int ageMinimum, int ageMaximum);

}
