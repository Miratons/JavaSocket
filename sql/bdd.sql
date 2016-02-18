-- Création de la table livre
CREATE TABLE livre (
	livre_id serial,
	nom varchar(255),
	prenom varchar(255),
	date_parution date,
	CONSTRAINT pk_livre PRIMARY KEY (livre_id)
);

-- Création de la table bibliotheque
CREATE TABLE bibliotheque (
	bibliotheque_id serial,
	nom varchar(255),
	adresse varchar(255),
	CONSTRAINT pk_bibliotheque PRIMARY KEY (bibliotheque_id)
);

-- Création de la table contenu_bibliotheque
CREATE TABLE contenu_bibliotheque (
	bibliotheque_id integer,
	livre_id integer,
	CONSTRAINT pk_contenu_bibliotheque PRIMARY KEY (bibliotheque_id, livre_id),
	CONSTRAINT fk_contenu_bibliotheque_bibliotheque FOREIGN KEY (bibliotheque_id) 
		REFERENCES bibliotheque (bibliotheque_id),
	CONSTRAINT fk_contenu_bibliotheque_livre FOREIGN KEY (livre_id) 
		REFERENCES livre (livre_id)
);

-- Ajout d'un livre
INSERT INTO livre (nom, prenom, date_parution) VALUES ('NOM', 'PRENOM', '2012-03-04');

-- Ajout d'une bibliothèque
INSERT INTO bibliotheque (nom, adresse) VALUES ('Bibliothèque municipale', '5 Rue de la paix');