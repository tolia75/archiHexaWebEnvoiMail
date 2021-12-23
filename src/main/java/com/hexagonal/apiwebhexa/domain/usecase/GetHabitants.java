package com.hexagonal.apiwebhexa.domain.usecase;

import com.hexagonal.apiwebhexa.domain.entities.CategorieSocioProfessionnelle;
import com.hexagonal.apiwebhexa.domain.entities.Personne;
import com.hexagonal.apiwebhexa.domain.exception.ValidatorMetierException;
import com.hexagonal.apiwebhexa.domain.port.primaire.GestionHabitants;
import com.hexagonal.apiwebhexa.domain.port.secondaire.HabitantsPort;

import java.util.List;
import java.util.Optional;

public class GetHabitants implements GestionHabitants {

    private HabitantsPort habitantsPort;

    public GetHabitants(HabitantsPort habitantsPort) {
        this.habitantsPort = habitantsPort;
    }

    @Override
    public List<Personne> getAllHabitants() {
        final List<Personne> personnes = habitantsPort.getAllHabitants();
        personnes.forEach(Personne::setNameToUppercase);
        return personnes;
    }

    @Override
    public List<Personne> getAllHabitantsParTrancheAge(int ageMinimum, int ageMaximum) {
        verifyAgeMinimumAndAgeMaximum(ageMinimum, ageMaximum);
        final List<Personne> personnes = habitantsPort.getAllHabitantsParTrancheAge(ageMinimum, ageMaximum);
        personnes.forEach(Personne::setNameToUppercase);
        return personnes;
    }

    @Override
    public List<Personne> getAllHabitantsParCategorieSocioProfessionnelle(int codeCategorieSocioProfesionnelle) {
        final CategorieSocioProfessionnelle categorieSocioProfessionnelle = getCategorieSocioProfesionnelleFromCode(codeCategorieSocioProfesionnelle);
        final List<Personne> personnes = habitantsPort.getAllHabitantsParCategorieSocioProfesionnelle(categorieSocioProfessionnelle);
        personnes.forEach(Personne::setNameToUppercase);
        return personnes;
    }

    private CategorieSocioProfessionnelle getCategorieSocioProfesionnelleFromCode(int codeCategorieSocioProfesionnelle) {
        Optional<CategorieSocioProfessionnelle> categorieSocioProfessionnelleOptional =
                CategorieSocioProfessionnelle.getCategorieSocioProfessionnelleByCode(codeCategorieSocioProfesionnelle);
        categorieSocioProfessionnelleOptional.orElseThrow(() ->
                new ValidatorMetierException("La catégorie socio-professionnelle avec code "+codeCategorieSocioProfesionnelle+" n'existe pas"));
        return categorieSocioProfessionnelleOptional.get();
    }

    private void verifyAgeMinimumAndAgeMaximum(int ageMinimum, int ageMaximum) {
        boolean isAuMoinsUnAgeEnErreur = false;
        StringBuilder stringBuilder = new StringBuilder();

        if (ageMinimum < 0 ) {
            isAuMoinsUnAgeEnErreur = true;
            stringBuilder.append("L'âge minimum reçu est inférieur à 0. Age minimum = "+ageMinimum+". ");
        }

        if (ageMaximum < 0) {
            isAuMoinsUnAgeEnErreur = true;
            stringBuilder.append("L'âge maximum reçu est inférieur à 0. Age maximum = "+ageMaximum+". ");
        }

        if (ageMaximum < ageMinimum) {
            isAuMoinsUnAgeEnErreur = true;
            stringBuilder.append("L'âge maximum reçu est inférieur à l'âge minimum Age minimum = "+ageMinimum+"" +
                    " et âge maximum = "+ageMaximum+". ");
        }

        if (isAuMoinsUnAgeEnErreur) {
            throw new ValidatorMetierException(stringBuilder.toString());
        }
    }
}
