DROP TABLE IF EXISTS personne;
DROP TABLE IF EXISTS categorie_socio_professionnelle;

CREATE TABLE CATEGORIE_SOCIO_PROFESSIONNELLE (
  code INT NOT NULL PRIMARY KEY,
  libelle VARCHAR(250) NOT NULL
);


CREATE TABLE PERSONNE (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  prenom VARCHAR(250) NOT NULL,
  nom VARCHAR(250) NOT NULL,
  age INT NOT NULL,
  email VARCHAR(250),
  categorie_socio_professionnelle_fk VARCHAR(250),
  FOREIGN KEY (categorie_socio_professionnelle_fk) references CATEGORIE_SOCIO_PROFESSIONNELLE(code)
);

INSERT INTO CATEGORIE_SOCIO_PROFESSIONNELLE (code, libelle) VALUES
  (1, 'Agriculteurs'),
  (2, 'Artisans, commerçants'),
  (3, 'Cadres et professiones intellectuelles supérieures'),
  (4, 'Professions intermediaires'),
  (5, 'Employes'),
  (6, 'Ouvriers'),
  (7, 'Retaites'),
  (8, 'Sans emplois');

INSERT INTO PERSONNE (prenom, nom, age, email, categorie_socio_professionnelle_fk) VALUES
--   ('Laurent', 'GINA', 28, 'laurentgina@mail.com', 1),
--   ('Sophie', 'FONCEK', 29,'sophiefoncek@mail.com', 1),
--   ('Agathe', 'FEELING', 88,'agathefeeling@mail.com', 7)
--   ('Aurélien', 'DESPLATS', 15,'aureliendesplats@mail.com', 2)
--   ('Martine', 'Martin', 32,'agathefeeling@mail.com', 2)
--   ('Avril', 'Lavigne', 22,'agathefeeling@mail.com', 3)
--   ('Johny', 'DEEP', 55,'agathefeeling@mail.com', 3)
--   ('TOM', 'Cruise', 36,'agathefeeling@mail.com', 4)
--   ('Justin', 'Trudeau', 28,'agathefeeling@mail.com', 4)
--   ('Luther', 'KING', 34,'agathefeeling@mail.com', 5)
--   ('Robert', 'BARATHEON', 44,'agathefeeling@mail.com', 5)
--   ('Justine', 'Madeleine', 18,'agathefeeling@mail.com', 6)
--   ('Albert', 'Einstein', 45,'agathefeeling@mail.com', 6)
--   ('Richard', 'Coeur de Lion', 88,'agathefeeling@mail.com', 7)
--   ('Henry', 'Dupont', 36,'agathefeeling@mail.com', 8)
--   ('Thomas', 'Dubois', 61,'agathefeeling@mail.com', 8)
     ('Galla', 'Topalian', 29, 'galla.topalian@gmail.com', 1);
