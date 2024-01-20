CREATE DATABASE IF NOT EXISTS `sae_reseaux` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sae_reseaux`;
CREATE table UTILISATEUR (
    nomUtilisateur varchar(255),
    motDePasse varchar(255),
    constraint pk_utilisateur primary key (nomUtilisateur)
);

CREATE table MESSAGE_U (
    idMessage int(11),
    nomUtilisateur varchar(255),
    contenu varchar(255),
    dateMessage VARCHAR(255),
    constraint pk_message_u primary key (idMessage),
    constraint fk_message_u foreign key (nomUtilisateur) references UTILISATEUR(nomUtilisateur)
);

CREATE table ABONNEMENT (
    nomUtilisateur varchar(255), # utilisateur qui s'abonne
    nomUtilisateurAbonnee varchar(255), # uilisateur a qui il s'abonne
    constraint pk_abonnement primary key (nomUtilisateur, nomUtilisateurAbonnee),
    constraint fk_abonnement foreign key (nomUtilisateur) REFERENCES UTILISATEUR(nomUtilisateur),
    constraint fk_abonnement2 foreign key (nomUtilisateurAbonnee) REFERENCES UTILISATEUR(nomUtilisateur)
);

CREATE table A_LIKE (
    nomUtilisateur varchar(255),
    idMessage int(11),
    constraint pk_a_like primary key (nomUtilisateur, idMessage),
    constraint fk_a_like foreign key (nomUtilisateur) REFERENCES UTILISATEUR(nomUtilisateur),
    constraint fk_a_like2 foreign key (idMessage) REFERENCES MESSAGE_U(idMessage)
);