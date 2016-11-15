/*==============================================================*/
/* 													     	    */
/* 		Creation de la base de donnée bdd_librairie      	    */
/* 													     	    */
/*==============================================================*/

USE master;

-- drop if it exists
IF EXISTS(SELECT name FROM sys.databases WHERE name = 'bdd_librairie')
DROP DATABASE bdd_librairie;

-- create if it does not exists
IF NOT EXISTS(SELECT name FROM sys.databases WHERE name = 'bdd_librairie')
CREATE DATABASE bdd_librairie;

-- select database ecole
USE bdd_librairie;

/*==============================================================*/
/* 													     	    */
/* 					Creation des tables  			    	    */
/* 													     	    */
/*==============================================================*/

------------------------------------------------
-- Auteur 
-- DROP TABLE Auteur if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'Auteur')
DROP TABLE Auteur;
-- ok
-- create table Auteur
create table Auteur(
	idAuteur		bigint			identity(1,1)	NOT NULL,
	nom				varchar(150)					NOT NULL,
	prenom			varchar(150),
	date_naissance	date, 
	date_deces		date,

CONSTRAINT PK_Auteur PRIMARY KEY (idAuteur),
CONSTRAINT CK_Auteur_date check (date_naissance<date_deces)
);


------------------------------------------------
-- Ouvrage
-- DROP TABLE Ouvrage if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'Ouvrage')
DROP TABLE Ouvrage;
-- ok
-- create table Ouvrage
create table Ouvrage(
	idOuvrage		bigint 			identity(1,1)	NOT NULL,
	idAuteur		bigint							NOT NULL,
	titre			varchar(200)					NOT NULL,
	sous_titre		varchar(100),
	"resume"		varchar(400)					NOT NULL,

CONSTRAINT PK_Ouvrage PRIMARY KEY (idOuvrage)
);


------------------------------------------------
-- CoAuteur
-- DROP TABLE CoAuteur if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'CoAuteur')
DROP TABLE CoAuteur;
-- ok
-- create table CoAuteur
create table CoAuteur(
	idAuteur		bigint	NOT NULL,
	idOuvrage		bigint	NOT NULL,

CONSTRAINT PK_CoAuteur PRIMARY KEY (idAuteur, idOuvrage)
);


------------------------------------------------
-- Tag 
-- DROP TABLE Tag if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'Tag')
DROP TABLE Tag;
-- ok
-- create table Tag ( Mots_clef )
create table Tag(
	idTag		bigint 			identity(1,1)	NOT NULL,
	libelle		varchar(100)	UNIQUE,				

CONSTRAINT PK_Tag PRIMARY KEY (idTag)
);


------------------------------------------------
-- Referencement
-- DROP TABLE Referencement if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'Referencement')
DROP TABLE Referencement;
-- ok
-- create table Referencement
create table Referencement(
	idTag		bigint 		NOT NULL,
	idOuvrage	bigint 		NOT NULL,

CONSTRAINT PK_Referencement PRIMARY KEY (idTag, idOuvrage)
);


------------------------------------------------
-- Rubrique 
-- DROP TABLE Rubrique if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'Rubrique')
DROP TABLE Rubrique;
-- ok
-- create table Rubrique
create table Rubrique(
	idRubrique	bigint 			identity(1,1)	NOT NULL,
	libelle		varchar(100)	UNIQUE			NOT NULL,
	date_debut	datetime 						NOT NULL,
	date_fin	datetime,
	commentaire varchar(500), -- rajouté

CONSTRAINT PK_Rubrique PRIMARY KEY (idRubrique),
CONSTRAINT CK_Rubrique_date check (date_debut<date_fin)

);


------------------------------------------------
-- MiseEnRubrique
-- DROP TABLE MiseEnRubrique if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'MiseEnRubrique')
DROP TABLE MiseEnRubrique;
-- ok
-- create table MiseEnRubrique
create table MiseEnRubrique(
	idRubrique		bigint NOT NULL,
	idOuvrage		bigint NOT NULL,

CONSTRAINT PK_MiseEnRubrique PRIMARY KEY (idRubrique, idOuvrage)
);


------------------------------------------------
-- Genre 
-- DROP TABLE Genre if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'Genre')
DROP TABLE Genre;
-- ok
-- create table Genre
create table Genre(
	idGenre		bigint 			identity(1,1)	NOT NULL,
	libelle		varchar(100)	UNIQUE			NOT NULL,

CONSTRAINT PK_Genre PRIMARY KEY (idGenre)
);


------------------------------------------------
-- IndexGenre
-- DROP TABLE IndexGenre if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'IndexGenre')
DROP TABLE IndexGenre;
-- ok
-- create table IndexGenre
create table IndexGenre(
	idGenre		bigint NOT NULL,
	idOuvrage	bigint NOT NULL,

CONSTRAINT PK_IndexGenre PRIMARY KEY (idGenre, idOuvrage)
);


------------------------------------------------
-- Theme 
-- DROP TABLE Theme if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'Theme')
DROP TABLE Theme;
-- ok
-- create table Theme
create table Theme(
	idTheme		bigint 			identity(1,1)	NOT NULL,
	libelle		varchar(100)	UNIQUE			NOT NULL,

CONSTRAINT PK_Theme PRIMARY KEY (idTheme)
);


------------------------------------------------
-- Thematique
-- DROP TABLE Thematique if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'Thematique')
DROP TABLE Thematique;
-- ok
-- create table Thematique
create table Thematique(
	idTheme		bigint NOT NULL,
	idOuvrage	bigint NOT NULL,

CONSTRAINT PK_Thematique PRIMARY KEY (idTheme, idOuvrage)
);


------------------------------------------------
-- StatutEntreprise
-- DROP TABLE StatutEntreprise if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'StatutEntreprise')
DROP TABLE StatutEntreprise;
-- ok
-- create table StatutEntreprise
create table StatutEntreprise(
	idStatutEntreprise	bigint identity(1,1)	NOT NULL,
	libelle		varchar(100)					NOT NULL,
	code		char(3)			UNIQUE			NOT NULL,

CONSTRAINT PK_StatutEntreprise PRIMARY KEY (idStatutEntreprise)
);


------------------------------------------------
-- Entreprise
-- DROP TABLE Entreprise if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'Entreprise')
DROP TABLE Entreprise;
-- ok
-- create table Entreprise
-- des champs doivent etre uniques dans cette table
CREATE TABLE Entreprise(
	idEntreprise		bigint 				identity(1,1)	NOT NULL,
	idStatutEntreprise	bigint								NOT NULL,
	nom					varchar(150)		UNIQUE			NOT NULL,
	siren				char(11)			UNIQUE			NOT NULL,
	rcs					char(21)			UNIQUE			NOT NULL,
	formeJuridique		varchar(4)							NOT NULL,
	capital				decimal(10,2)						NOT NULL,
	numTVA				varchar(13)			UNIQUE			NOT NULL, 
	-- FR + cle de 2 chiffres (cle informatique) + N° siren

CONSTRAINT PK_Entreprise PRIMARY KEY (idEntreprise)
);


-- adresseEntreprise
-- DROP TABLE adresseEntreprise if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'adresseEntreprise')
DROP TABLE adresseEntreprise;
-- ok
CREATE TABLE adresseEntreprise(
	idEntreprise		bigint 					NOT NULL,
	idAdresse			bigint					NOT NULL,


CONSTRAINT PK_adresseEntreprise PRIMARY KEY (idEntreprise, idAdresse)
);

--create table Edition
IF EXISTS (SELECT name FROM sys.tables WHERE name ='Edition')
DROP TABLE Edition;
-- ok
CREATE TABLE [Edition](
	--(ISBN[-]*(1[03])*[ ]*(: ){0,1})*(([0-9Xx][- ]*){13}|([0-9Xx][- ]*){10})
	idEdition			BIGINT			 IDENTITY(1,1)
	,isbn				VARCHAR(17)		 UNIQUE				NOT NULL		
	,idOuvrage			BIGINT								NOT NULL
	,idEditeur			BIGINT								NOT NULL
	,idLangue			BIGINT								NOT NULL
	,idStatutEdition	BIGINT								NOT NULL
	,datePubli			DATE								NOT NULL
    ,prixHt				FLOAT								NOT NULL
	,couverture			VARCHAR(100)
	,titre				VARCHAR(200)
	,stock				INT									NOT NULL

	CONSTRAINT PK_edition		     PRIMARY KEY (idEdition)
);

--create table StatutEdition
IF EXISTS (SELECT name FROM sys.tables WHERE name ='StatutEdition')
DROP TABLE StatutEdition;
-- ok
CREATE TABLE [StatutEdition](
	 idStatutEdition	BIGINT			IDENTITY(1,1)	NOT NULL
	,libelle			VARCHAR(100)					NOT NULL		
	,code				CHAR(3)			UNIQUE			NOT NULL,


	CONSTRAINT PK_statuEd		     PRIMARY KEY (idStatutEdition)
);

--create table editeur
IF EXISTS (SELECT name FROM sys.tables WHERE name ='Editeur')
DROP TABLE Editeur;
-- ok
CREATE TABLE [Editeur](
	  idEditeur			BIGINT			IDENTITY(1,1)	NOT NULL
	, idAdresse			BIGINT							NOT NULL
	, libelle			varchar(100)	UNIQUE			NOT NULL,

	CONSTRAINT PK_editeur		     PRIMARY KEY (idEditeur) 
);

--create table promotion
IF EXISTS (SELECT name FROM sys.tables WHERE name ='Promotion')
DROP TABLE Promotion;
-- ok
CREATE TABLE [Promotion](
	 idPromo			BIGINT			IDENTITY(1,1)	NOT NULL
	,dateDebut			DATETIME						NOT NULL
	,dateFin			DATETIME						NOT NULL
	,reduction			FLOAT							NOT NULL
	,"image"			VARCHAR(100)
	,"description"		VARCHAR(200)
	,commentaire		VARCHAR(200)


	CONSTRAINT PK_promotion		     PRIMARY KEY (idPromo)
);


--create table taxe
IF EXISTS (SELECT name FROM sys.tables WHERE name ='Taxe')
DROP TABLE Taxe;
-- ok
CREATE TABLE [Taxe](
	 idTaxe				BIGINT			IDENTITY(1,1)	NOT NULL
	,libelle			VARCHAR(100)	UNIQUE			NOT NULL
	,valeur				FLOAT							NOT NULL,


	CONSTRAINT PK_Taxe		     PRIMARY KEY (idTaxe)
);


-- create table langue
IF EXISTS (SELECT name FROM sys.tables WHERE name ='Langue')
DROP TABLE Langue;
-- ok
CREATE TABLE [Langue](
	 idLangue			BIGINT			IDENTITY(1,1)	NOT NULL
	,libelle			VARCHAR(100)					NOT NULL
	,code				CHAR(3)			UNIQUE			NOT NULL,


	CONSTRAINT PK_langue		     PRIMARY KEY (idLangue)
);


--create table ApplicationTaxe
IF EXISTS (SELECT name FROM sys.tables WHERE name ='ApplicationTaxe')
DROP TABLE ApplicationTaxe;
-- ok
CREATE TABLE [ApplicationTaxe](
	 idTaxe				BIGINT			NOT NULL		
	,idEdition			BIGINT		    NOT NULL,
	


	CONSTRAINT PK_applicationTaxe		     PRIMARY KEY (idTaxe, idEdition)
);

--create table ApplicationPromo

IF EXISTS (SELECT name FROM sys.tables WHERE name ='ApplicationPromo')
DROP TABLE ApplicationPromo;
-- ok
CREATE TABLE [ApplicationPromo](
	 idPromo		BIGINT			NOT NULL		
	,idEdition		BIGINT		    NOT NULL,



	CONSTRAINT PK_applicationPromo		     PRIMARY KEY (idPromo, idEdition)
);


--create table ModeleEdition
/*
IF EXISTS (SELECT name FROM sys.tables WHERE name ='ModeleEdition')
DROP TABLE ModeleEdition;
-- ok
CREATE TABLE [ModeleEdition](
	  idEditeur		BIGINT			NOT NULL		
	, idEdition		BIGINT		    NOT NULL,

	CONSTRAINT PK_modeleEdition		     PRIMARY KEY (idEditeur, idEdition)
);
*/

--create table commande
IF EXISTS (SELECT name FROM sys.tables WHERE name ='Commande')
DROP TABLE Commande;
-- ok
CREATE TABLE [Commande](
	 idCommande					BIGINT			IDENTITY(1,1)	NOT NULL
	,idAdresseLivraison			BIGINT							NOT NULL
	,idAdresseFacturation		BIGINT							NOT NULL
	,idUtilisateur				BIGINT							NOT NULL
	,numero						UNIQUEIDENTIFIER				NOT NULL
	DEFAULT newid()
	,dateCommande				INT							NOT NULL,


	CONSTRAINT PK_commande		     PRIMARY KEY (idCommande)
);


--create table statut commande
IF EXISTS (SELECT name FROM sys.tables WHERE name ='StatutCommande')
DROP TABLE StatutCommande;
-- ok
CREATE TABLE [StatutCommande](
	 idStatutCommande		BIGINT			IDENTITY(1,1)	NOT NULL
	,libelle				VARCHAR(100)					NOT NULL
	,code					CHAR(3)			UNIQUE			NOT NULL,


	CONSTRAINT PK_statutCommande		     PRIMARY KEY (idStatutCommande)
);

--create historiqueStatutCommande
IF EXISTS (SELECT name FROM sys.tables WHERE name ='HistoriqueStatutCommande')
DROP TABLE HistoriqueStatutCommande;
-- ok
CREATE TABLE [HistoriqueStatutCommande](
	 idStatutCommande			BIGINT				NOT NULL		
	,idCommande					BIGINT				NOT NULL
	,datePassage				DATETIME			NOT NULL			
	,commentaire				VARCHAR(200),


	CONSTRAINT PK_historiqueStatutCommande		     PRIMARY KEY (idStatutCommande, idCommande, datePassage) 

);


--create table ligneCommande
IF EXISTS (SELECT name FROM sys.tables WHERE name ='LigneCommande')
DROP TABLE LigneCommande;
-- ok
CREATE TABLE [LigneCommande](
	 idLigneCommande			BIGINT			IDENTITY(1,1)	NOT NULL
	,idEdition					BIGINT						    NOT NULL
	,idCommande					BIGINT							NOT NULL
	,quantite					INT								NOT NULL				
	,prixUnitaire				FLOAT							NOT NULL
	,taxe						FLOAT -- NOT NULL a valider plus tard
	,promo						FLOAT,


	CONSTRAINT PK_ligneCommande		     PRIMARY KEY (idLigneCommande)

);


--create table commentaire
IF EXISTS (SELECT name FROM sys.tables WHERE name ='Commentaire')
DROP TABLE Commentaire;
-- ok
CREATE TABLE [Commentaire](
	 idLigneCommande			BIGINT				NOT NULL
	,idEdition					BIGINT			    NOT NULL
	,idUtilisateur				BIGINT				NOT NULL				
	,titre						VARCHAR(200)
	,contenu					VARCHAR(500)
	,note						INT					NOT NULL,


	CONSTRAINT PK_commentaireUtilisateur		     PRIMARY KEY (idLigneCommande, idEdition, idUtilisateur)

);

-- create table transaction
IF EXISTS (SELECT name FROM sys.tables WHERE name ='Transaction')
DROP TABLE "Transaction";
-- ok
CREATE TABLE [Transaction](
	 idTransaction				BIGINT				IDENTITY(1,1)		NOT NULL
	,idCommande					BIGINT									NOT NULL
	,idStatutTransaction		BIGINT									NOT NULL				
	,dateReception				DATETIME								NOT NULL,


	CONSTRAINT PK_transaction		     PRIMARY KEY (idTransaction)

);

--create table statutransaction 

IF EXISTS (SELECT name FROM sys.tables WHERE name ='StatutTransaction')
DROP TABLE StatutTransaction;
-- ok
CREATE TABLE [StatutTransaction](
	 idStatutTransaction				BIGINT				IDENTITY(1,1)	NOT NULL
	,libelle							VARCHAR(100)						NOT NULL
	,code								CHAR(3)				UNIQUE			NOT NULL,			

	CONSTRAINT PK_StatutTransaction		     PRIMARY KEY (idStatutTransaction)
);


-- DROP TABLE Utilisateur if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'Utilisateur')
DROP TABLE Utilisateur;
-- ok
-- create table Utilisateur
CREATE TABLE Utilisateur (
	  idUtilisateur			BIGINT			IDENTITY(1, 1)	NOT NULL
	, idStatutUtilisateur	BIGINT							NOT NULL
	, nom					VARCHAR(150)					NOT NULL
	, prenom				VARCHAR(150)					NOT NULL
	, date_naissance		DATE							NOT NULL
	, telephone				VARCHAR(20)		
	, mail					VARCHAR(300)	UNIQUE			NOT NULL
	, mot_de_passe			VARCHAR(100)					NOT NULL,

	CONSTRAINT pk_Utilisateur PRIMARY KEY (idUtilisateur)
);

-- StatutUtilisateur
-- ok
-- DROP TABLE StatutUtilisateur if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'StatutUtilisateur')
DROP TABLE StatutUtilisateur;
-- 
CREATE TABLE StatutUtilisateur (
	  idStatutUtilisateur		BIGINT			IDENTITY(1, 1)	NOT NULL
	, libelle					VARCHAR(100)					NOT NULL
	, code						CHAR(3)			UNIQUE			NOT NULL,
	
	CONSTRAINT pk_StatutUtilisateur PRIMARY KEY (idStatutUtilisateur)
);



-- Role

-- DROP TABLE Role if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'Role')
DROP TABLE "Role";
-- ok
-- create table Prise_en_charge
CREATE TABLE "Role" (
	  idRole			BIGINT			IDENTITY(1, 1)	NOT NULL
	, Libelle			VARCHAR(100)					NOT NULL
	, code				CHAR(3)			UNIQUE			NOT NULL,

	CONSTRAINT pk_Role PRIMARY KEY (idRole)
);


-- Remplit

-- DROP TABLE Remplit if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'Remplit')
DROP TABLE Remplit;
-- ok
-- create table Remplit
CREATE TABLE Remplit (
	  idUtilisateur		BIGINT			NOT NULL
	, idRole			BIGINT			NOT NULL,

	CONSTRAINT pk_Remplit PRIMARY KEY (idUtilisateur, idRole)
);


-- Adresse

-- DROP TABLE Adresse if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'Adresse')
DROP TABLE Adresse;
-- ok
-- create table Adresse
CREATE TABLE Adresse (
	  idAdresse			BIGINT			IDENTITY(1, 1)	NOT NULL  
	, idPays			BIGINT							NOT NULL
	, idStatutAdresse	BIGINT							NOT NULL
	, numero			VARCHAR(10)		
	, voie				VARCHAR(400)					NOT NULL
	, codePostal		VARCHAR(10)						NOT NULL
	, ville				VARCHAR(100)					NOT NULL
	, complement		VARCHAR(100),	


	CONSTRAINT pk_adresse PRIMARY KEY (idAdresse)
);


--création table statutAdresse
-- ok
CREATE TABLE StatutAdresse (
	
	idStatutAdresse		BIGINT			IDENTITY(1,1)	NOT NULL,
	libelle				VARCHAR(100)					NOT NULL,
	code				CHAR(3)			UNIQUE			NOT NULL,

CONSTRAINT PK_StatutAdresse PRIMARY KEY(idStatutAdresse)
);


-- dernieres_facturations

-- DROP TABLE Dernieres_facturations if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'DernieresFacturations')
DROP TABLE DernieresFacturations;
-- ok
-- create table Dernieres_facturations
CREATE TABLE DernieresFacturations (
	  idUtilisateur		BIGINT			NOT NULL
	, idAdresse			BIGINT			NOT NULL,

	CONSTRAINT pk_Dernieres_facturations PRIMARY KEY (idUtilisateur, idAdresse)
);

-- dernieres_livraisons

-- DROP TABLE Dernieres_livraisons if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'DernieresLivraisons')
DROP TABLE DernieresLivraisons;
-- ok
-- create table Dernieres_livraisons
CREATE TABLE DernieresLivraisons (
	  idUtilisateur		BIGINT			NOT NULL
	, idAdresse			BIGINT			NOT NULL,

	CONSTRAINT pk_Dernieres_livraisons PRIMARY KEY (idUtilisateur, idAdresse)
);



-- pays

-- DROP TABLE pays if exists
IF EXISTS (SELECT name FROM sys.tables WHERE name = 'Pays')
DROP TABLE Pays;
-- ok
-- create table pays
CREATE TABLE Pays (
	  idPays		BIGINT				identity(1,1)	NOT NULL
	, libelle		VARCHAR(100)						NOT NULL	
	, code			CHAR(3)				UNIQUE			NOT NULL,

	CONSTRAINT pk_pays PRIMARY KEY (idPays)
);


/*==============================================================*/
/* 													     	    */
/* 					Relations entre les tables 		 	   	    */
/* 													     	    */
/*==============================================================*/


ALTER TABLE Ouvrage ADD
-- relation directe entre Ouvrage et Auteur
CONSTRAINT fk_Ouvrage_Auteur			FOREIGN KEY (idAuteur)  	REFERENCES Auteur;


ALTER TABLE CoAuteur ADD
-- relation directe entre CoAuteur et Auteur
CONSTRAINT fk_CoAuteur_Auteur			FOREIGN KEY (idAuteur)  	REFERENCES Auteur,
-- relation directe entre CoAuteur et Ouvrage
CONSTRAINT fk_CoAuteur_Ouvrage			FOREIGN KEY (idOuvrage)  	REFERENCES Ouvrage;


ALTER TABLE Referencement ADD
-- relation directe entre Referencement et MotClef
CONSTRAINT fk_Referencement_Tag			FOREIGN KEY (idTag)  		REFERENCES Tag,
-- relation directe entre Referencement et Ouvrage
CONSTRAINT fk_Referencement_Ouvrage		FOREIGN KEY (idOuvrage)		REFERENCES Ouvrage;


ALTER TABLE MiseEnRubrique ADD
-- relation directe entre MiseEnRubrique et Rubrique
CONSTRAINT fk_MiseEnRubrique_Rubrique	FOREIGN KEY (idRubrique)	REFERENCES Rubrique ON DELETE CASCADE,
-- relation directe entre MiseEnRubrique et Ouvrage
CONSTRAINT fk_MiseEnRubrique_Ouvrage	FOREIGN KEY (idOuvrage)		REFERENCES Ouvrage;


ALTER TABLE IndexGenre ADD
-- relation directe entre IndexGenre et Genre
CONSTRAINT fk_IndexGenre_Genre			FOREIGN KEY (idGenre)		REFERENCES Genre,
-- relation directe entre IndexGenre et Ouvrage
CONSTRAINT fk_IndexGenre_Ouvrage		FOREIGN KEY (idOuvrage)		REFERENCES Ouvrage;


ALTER TABLE Thematique ADD
-- relation directe entre Thematique et Theme
CONSTRAINT fk_Thematique_Theme			FOREIGN KEY (idTheme)		REFERENCES Theme,
-- relation directe entre Thematique et Ouvrage
CONSTRAINT fk_Thematique_Ouvrage		FOREIGN KEY (idOuvrage)		REFERENCES Ouvrage;


ALTER TABLE Entreprise ADD
-- relation directe entre Entreprise et idStatutEntreprise
CONSTRAINT fk_Entreprise_StatutEntreprise	FOREIGN KEY (idStatutEntreprise)	REFERENCES StatutEntreprise;


ALTER TABLE Edition ADD	 
-- relation directe entre Edition et Ouvrage
CONSTRAINT	FK_edition_ouvrage				FOREIGN KEY(idOuvrage)				REFERENCES Ouvrage(idOuvrage),
-- relation directe entre Edition et Langue
CONSTRAINT	FK_edition_langue				FOREIGN KEY(idLangue)				REFERENCES Langue(idLangue),
-- relation directe entre Edition et Editeur
CONSTRAINT	FK_edition_editeur				FOREIGN KEY(idEditeur)				REFERENCES Editeur(idEditeur),
-- relation directe entre Edition et StatutEdition
CONSTRAINT	FK_edition_statut_edition	    FOREIGN KEY(idStatutEdition)		REFERENCES StatutEdition(idStatutEdition)

ALTER TABLE Editeur ADD	 
-- relation directe entre Editeur et Adresse
CONSTRAINT	FK_editeur_adresse				FOREIGN KEY(idAdresse)				REFERENCES Adresse(idAdresse)

ALTER TABLE ApplicationTaxe ADD	 
-- relation directe entre ApplicationTaxe et Taxe
CONSTRAINT	FK_applicationTaxe_taxe			FOREIGN KEY(idTaxe)					REFERENCES Taxe(idTaxe),
-- relation directe entre ApplicationTaxe et Edition
CONSTRAINT	FK_appliTaxe_edition			FOREIGN KEY(idEdition)					REFERENCES Edition(idEdition)


ALTER TABLE ApplicationPromo ADD
-- relation directe entre ApplicationPromo et Promotion
CONSTRAINT FK_appliPromo_promotion			FOREIGN KEY(idPromo)				REFERENCES Promotion(idPromo) ON DELETE CASCADE,
-- relation directe entre ApplicationPromo et Edition
CONSTRAINT FK_appliPromo_edition			FOREIGN KEY(idEdition)					REFERENCES Edition(idEdition)

/*
ALTER TABLE ModeleEdition ADD
-- relation directe entre ModeleEdition et Editeur
CONSTRAINT FK_modeleEdition_editeur			FOREIGN KEY(idEditeur)				REFERENCES Editeur(idEditeur),
-- relation directe entre ModeleEdition et Edition
CONSTRAINT FK_modeleEdition_edition			FOREIGN KEY(idEdition)					REFERENCES Edition(idEdition)
*/

-- ,idAdresseLivraison			BIGINT			NOT NULL
-- 	,idAdresseFacturation		BIGINT			NOT NULL
ALTER TABLE Commande ADD
-- relation directe entre Commande et Adresse
CONSTRAINT FK_commande_adresse_livraison	FOREIGN KEY(idAdresseLivraison)		REFERENCES Adresse(idAdresse),
-- relation directe entre Commande et Adresse
CONSTRAINT FK_commande_adresse_facturation	FOREIGN KEY(idAdresseFacturation)	REFERENCES Adresse(idAdresse),
-- relation directe entre Commande et Utilisateur
CONSTRAINT FK_commande_utilisateur			FOREIGN KEY(idUtilisateur)			REFERENCES Utilisateur(idUtilisateur)


ALTER TABLE HistoriqueStatutCommande ADD
-- relation directe entre HistoriqueStatutCommande et StatutCommande
CONSTRAINT FK_histo_statut					FOREIGN KEY(idStatutCommande)		REFERENCES StatutCommande(idStatutCommande),
-- relation directe entre HistoriqueStatutCommande et Commande
CONSTRAINT FK_histo_commande				FOREIGN KEY(idCommande)				REFERENCES Commande(idCommande)


ALTER TABLE LigneCommande ADD
-- relation directe entre LigneCommande et Edition
CONSTRAINT FK_ligneCommande_edition			FOREIGN KEY(idEdition)					REFERENCES Edition(idEdition),
-- relation directe entre LigneCommande et Commande
CONSTRAINT FK_ligneCommande_commande		FOREIGN KEY(idCommande)				REFERENCES Commande(idCommande)


ALTER TABLE Commentaire ADD
-- relation directe entre Commentaire et LigneCommande
CONSTRAINT FK_commentaire_ligne				FOREIGN KEY(idLigneCommande)		REFERENCES LigneCommande(idLigneCommande),
-- relation directe entre Commentaire et Edition
CONSTRAINT FK_commentaire_edition			FOREIGN KEY (idEdition)					REFERENCES Edition(idEdition),
-- relation directe entre Commentaire et Utilisateur
CONSTRAINT FK_commentaire_utilisateur		FOREIGN KEY(idUtilisateur)			REFERENCES Utilisateur(idUtilisateur)


ALTER  TABLE  "Transaction" ADD
-- relation directe entre "Transaction" et Commande
CONSTRAINT FK_transaction_commande			FOREIGN KEY(idCommande)				REFERENCES Commande(idCommande),
-- relation directe entre "Transaction" et StatutTransaction
CONSTRAINT FK_transaction_statut			FOREIGN KEY(idStatutTransaction)	REFERENCES StatutTransaction(idStatutTransaction)



-- relation directe entre Utilisateur et StatutUtilisateur
ALTER TABLE Utilisateur ADD 
CONSTRAINT fk_utilisateur_statutUtilisateur FOREIGN KEY (idStatutUtilisateur) REFERENCES StatutUtilisateur;

-- Utilisateur -- remplit -- role
ALTER TABLE Remplit ADD
CONSTRAINT fk_remplit_utilisateur FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur,
CONSTRAINT fk_remplit_role		  FOREIGN KEY (idRole)		  REFERENCES "Role";


-- Utilisateur -- dernieres_facturations -- adresse
ALTER TABLE DernieresFacturations ADD 
CONSTRAINT fk_dernieres_facturations_utilisateur FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur,
CONSTRAINT fk_dernieres_facturations_adresse	 FOREIGN KEY (idAdresse)	 REFERENCES Adresse;

-- Utilisateur -- dernieres_livraisons -- adresse
ALTER TABLE DernieresLivraisons ADD 
CONSTRAINT fk_dernieres_livraisons_utilisateur FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur,
CONSTRAINT fk_dernieres_livraisons_adresse	 FOREIGN KEY (idAdresse)	 REFERENCES Adresse;

-- relation directe entre Adresse et Pays
ALTER TABLE Adresse ADD
CONSTRAINT fk_adresse_pays	FOREIGN KEY (idPays) REFERENCES Pays;


-- relation directe entre Adresse et StatutAdresse
ALTER TABLE Adresse ADD
CONSTRAINT fk_adresse_statut_adresse	FOREIGN KEY (idStatutAdresse) REFERENCES StatutAdresse;


-- relation directe entre Entreprise et Adresse
ALTER TABLE adresseEntreprise ADD
CONSTRAINT fk_adresseEntreprise_Entreprise	FOREIGN KEY (idEntreprise) REFERENCES Entreprise,
CONSTRAINT fk_adresseEntreprise_Adresse	FOREIGN KEY (idAdresse) REFERENCES Adresse;


-- *************************************** --
--			Contraintes Check			   --
-- *************************************** --

--TABLE UTILISATEUR--
ALTER TABLE Utilisateur ADD	
--son mail doit être correct
CONSTRAINT ck_mail_utilisateur CHECK(Utilisateur.mail LIKE '%_@_%_.__%'),
--sa date de naissance doit être correcte
CONSTRAINT ck_date_naissance_utilisateur CHECK(CAST(GETDATE() AS DATE) > Utilisateur.date_naissance);


--TABLE AUTEUR--
ALTER TABLE Auteur ADD
--sa date de naissance doit être correcte
CONSTRAINT ck_date_naissance_auteur CHECK(CAST(GETDATE() AS DATE) > Auteur.date_naissance);


--TABLE EDITION --
ALTER TABLE Edition ADD
-- l'isbn doit être correct
CONSTRAINT ck_isbn_edition CHECK(
Edition.isbn LIKE '[0-9][0-9][0-9]-[0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9]'
OR
Edition.isbn LIKE '[0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9]'
);


--TABLE Entreprise --
ALTER  TABLE Entreprise ADD
-- son siren doit etre correct 
CONSTRAINT ck_siren_entreprise CHECK(Entreprise.siren LIKE '[1-2][0-9][0-9] [0-9][0-9][0-9] [0-9][0-9][0-9]'),

-- son numTVA doit etre correct ( FR + cléInformatique + n°siren)
CONSTRAINT ck_numTVA_entreprise CHECK(Entreprise.numTVA LIKE'%_[0-9][0-9]_%'),

-- son num RCS doit etre correct ( RCS + lieu d'immatriculation + n°siren)
CONSTRAINT ck_rcs_entreprise CHECK(Entreprise.rcs LIKE 'RCS % [1-2][0-9][0-9] [0-9][0-9][0-9] [0-9][0-9][0-9]');
