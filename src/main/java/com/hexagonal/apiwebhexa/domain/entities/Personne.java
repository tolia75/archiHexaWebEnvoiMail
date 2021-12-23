package com.hexagonal.apiwebhexa.domain.entities;

import java.util.Objects;

public class Personne {

    private Long id;
    private String prenom;
    private String nom;
    private int age;
    private String email;
    private CategorieSocioProfessionnelle categorieSocioProfessionnelle;

    public Personne(PersoneBuilder builder) {
        this.id = builder.id;
        this.prenom = builder.prenom;
        this.nom = builder.nom;
        this.age = builder.age;
        this.email = builder.email;
        this.categorieSocioProfessionnelle = builder.categorieSocioProfessionnelle;
    }

    public void setNameToUppercase() {
        this.nom = this.nom.toUpperCase();
    }


    public CategorieSocioProfessionnelle getCategorieSocioProfessionelle() {
        return this.categorieSocioProfessionnelle;
    }

    public int getAge() {
        return this.age;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return age == personne.age &&
                Objects.equals(id, personne.id) &&
                Objects.equals(prenom, personne.prenom) &&
                Objects.equals(nom, personne.nom) &&
                Objects.equals(email, personne.email) &&
                categorieSocioProfessionnelle == personne.categorieSocioProfessionnelle;
    }

    public static class PersoneBuilder {
        private Long id;
        private String prenom;
        private String nom;
        private int age;
        private String email;
        private CategorieSocioProfessionnelle categorieSocioProfessionnelle;

        public PersoneBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PersoneBuilder prenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public PersoneBuilder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public PersoneBuilder age(int age) {
            this.age = age;
            return this;
        }

        public PersoneBuilder mail(String email) {
            this.email = email;
            return this;
        }

        public PersoneBuilder categorieSocioProfessionelle(CategorieSocioProfessionnelle categorieSocioProfessionnelle) {
            this.categorieSocioProfessionnelle = categorieSocioProfessionnelle;
            return this;
        }

        public Personne build() {
            return new Personne(this);
        }
    }
}
