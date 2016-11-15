
USE bdd_librairie;

SELECT * FROM Ouvrage;
SELECT * FROM Tag;
SELECT * FROM Ouvrage;
SELECT * FROM Auteur;
SELECT * FROM CoAuteur;
SELECT * FROM Auteur;
SELECT * FROM Ouvrage;
SELECT * FROM Editeur;
SELECT * FROM Edition;


SELECT idAuteur FROM Auteur WHERE nom ='WERBER' and prenom = 'Bernard'

-- selectionner tous les ouvrages écrits par Victor Pruneau le boloss de Notre-Dame
SELECT titre, sous_titre, "resume" 
FROM Ouvrage 
WHERE idAuteur = (
	SELECT idAuteur 
	FROM Auteur 
	WHERE nom = 'HUGO' and prenom = 'Victor'
);

-- version moins pourrie
SELECT titre, sous_titre, "resume"
FROM Ouvrage AS o
join Auteur AS a ON o.idAuteur = a.idAuteur
WHERE nom = 'HUGO' and prenom = 'Victor'


-- verification pré requete
SELECT * FROM Referencement;
SELECT * FROM Tag;
-- requete de gros sale sale pour faire une recherche par tag, exemple "Ricard"
SELECT titre, sous_titre, "resume" 
FROM Ouvrage 
WHERE idOuvrage = (
	SELECT idOuvrage 
	FROM Referencement 
	WHERE idTag = (
		SELECT idTag 
		FROM Tag 
		WHERE libelle = 'Ricard'
	)
);

-- version moins pourrie
SELECT titre, sous_titre, "resume"
FROM Ouvrage AS o
JOIN Referencement AS r ON o.idOuvrage = r.idOuvrage
JOIN Tag AS t ON r.idTag = t.idTag
WHERE libelle = 'Ricard'


-- recherche de livre par isbn
SELECT titre, sous_titre, "resume" 
FROM Ouvrage 
WHERE idOuvrage = (
	SELECT idOuvrage 
	FROM Edition 
	WHERE isbn = '978-2-0001-0001-0'
);

-- version moins pourrie
SELECT o.titre, sous_titre, "resume"
FROM Ouvrage AS o
JOIN Edition AS e ON o.idOuvrage = e.idOuvrage
WHERE isbn = '978-2-0001-0001-0'



-- rechercher un utilisateur par mail
SELECT * FROM Utilisateur
--
SELECT nom, prenom, date_naissance, telephone
FROM Utilisateur as u
WHERE mail = 'petitjean.robin@email.com'



-- trouver les commentaires d'un utilisateur
SELECT * FROM Commentaire;
SELECT * FROM Utilisateur;

--
SELECT u.nom, u.prenom, c.isbn, c.titre, c.contenu, c.note
FROM Commentaire as c
JOIN Utilisateur AS u ON c.idUtilisateur = u.idUtilisateur
WHERE u.prenom = 'Lancelot' AND u.nom = 'Dulac'




-- recherches d'informations avec le numero de commande
SELECT * FROM Commande;
SELECT * FROM LigneCommande;
SELECT * FROM Adresse;

--
SELECT u.nom, u.prenom, u.telephone, u.mail, a.numero, a.voie, a.complement, a.codePostal, a.ville, c.numero, c.dateCommande, lc.quantite, lc.prixUnitaire, lc.isbn, sc.libelle
FROM Utilisateur AS u
JOIN dernieresFacturations AS df ON u.idUtilisateur = dF.idUtilisateur
JOIN Adresse AS a ON df.idAdresse = a.idAdresse
JOIN Commande AS c ON a.idAdresse = c.idAdresseLivraison
JOIN StatutCommande AS sc ON c.idCommande = sc.idStatutCommande
JOIN ligneCommande AS lc ON c.idCommande = lc.idCommande
WHERE c.numero = 'DGR12345DG'



-- tri par statut des commandes d'un utilisateur
SELECT * FROM Commande;
SELECT * FROM ligneCommande;
SELECT * FROM historiqueStatutCommande;
SELECT * FROM statutCommande;
SELECT * FROM utilisateur;

-- 
SELECT c.numero, c.dateCommande, sc.libelle, hsc.commentaire, lc.isbn, lc.quantite, lc.prixUnitaire
FROM Utilisateur AS u
JOIN Commande AS c ON u.idUtilisateur = c.idUtilisateur
JOIN ligneCommande AS lc ON c.idCommande = lc.idCommande
JOIN historiqueStatutCommande AS hsc ON c.idCommande = hsc.idCommande
JOIN statutCommande AS sc ON hsc.idStatutCommande = sc.idStatutCommande
WHERE sc.Code = 'lv1' AND u.prenom = 'Lancelot' AND u.nom = 'Dulac'


-- selectionner un statut d'une commande à partir de l'id de cette derniere
SELECT sc.idStatutCommande, sc.libelle, sc.code
FROM Commande AS c
-- JOIN ligneCommande AS lc ON c.idCommande = lc.idCommande
JOIN historiqueStatutCommande AS hsc ON c.idCommande = hsc.idCommande
JOIN statutCommande AS sc ON hsc.idStatutCommande = sc.idStatutCommande
WHERE c.idCommande = 1