package com.hexagonal.apiwebhexa.infrastructure.database;

import com.hexagonal.apiwebhexa.domain.entities.CategorieSocioProfessionnelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitantH2Repository extends JpaRepository<PersonneDataBase, Long> {

    List<PersonneDataBase> findAllByCategorieSocioProfessionnelle(CategorieSocioProfessionnelle categorieSocioProfessionnelle);

    List<PersonneDataBase> findAllByAgeIsBetween(int ageMinimum, int ageMaximum);

}
