package com.hexagonal.apiwebhexa.domain.entities;

import java.util.Optional;

public enum CategorieSocioProfessionnelle {
    AGRICULTEURS(1),ARTISANS_COMMERCANTS(2), CADRES_ET_PROFESSIONS_INTELLECTUELLES_SUPERIEURES(3),
    PROFESSIONS_INTERDMEDIAIRES(4), EMPLOYES(5), OUVRIERS(6), RETRAITES(7), SANS_EMPLOIS(8);

    private int code;

    private CategorieSocioProfessionnelle(int code) {
        this.code = code;
    }

    public int getCodeValue() {
        return code;
    }

    public static Optional<CategorieSocioProfessionnelle> getCategorieSocioProfessionnelleByCode(int code) {
        for (CategorieSocioProfessionnelle categorieSocioProfessionnelle : values()) {
            if (categorieSocioProfessionnelle.getCodeValue() == code) {
                return Optional.of(categorieSocioProfessionnelle);
            }
        }
        return Optional.empty();
    }


}
