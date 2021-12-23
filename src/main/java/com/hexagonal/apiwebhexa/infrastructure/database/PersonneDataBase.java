package com.hexagonal.apiwebhexa.infrastructure.database;

import com.hexagonal.apiwebhexa.domain.entities.CategorieSocioProfessionnelle;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "personne")
public class PersonneDataBase {

    @Id
    private Long id;
    private String prenom;
    private String nom;
    private int age;
    private String email;
    private CategorieSocioProfessionnelle categorieSocioProfessionnelle;

    public PersonneDataBase(Long id, String prenom, String nom, int age, String email, CategorieSocioProfessionnelle categorieSocioProfessionnelle) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.email = email;
        this.categorieSocioProfessionnelle = categorieSocioProfessionnelle;
    }

    public Long getId() {
        return this.id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public CategorieSocioProfessionnelle getCategorieSocioProfessionnelle() {
        return categorieSocioProfessionnelle;
    }
}
