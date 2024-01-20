# SAE_RESAUX


## Lancement :


- pour lancer le serveur client, il faut que votre ordinateur soit sous Linux.
  
- avoir un serveur mysql

### crée un serveur mysql

- si vous n'avez pas de serveur sql faites les commandes
  -  ``` sudo apt update```
  -  ``` sudo apt install mysql-server ```
- crée un utilisateur  si vous n'en avez pas
  - ``` sudo mysql ```
  -  dans mysql faites ```CREATE USER 'nom_utilisateur'@'localhost' IDENTIFIED BY 'mot_de_passe' ```
  -  ensuite ```GRANT ALL PRIVILEGES ON . TO 'nom_utilisateur'@'localhost' WITH GRANT OPTION;```
  -  et enfin ```FLUSH PRIVILEGES;```

## lancement serveur clients

- il faut aller dans *src/Serveur* a la ligne **connexionMysql.connecter('nomBase','nomLogin','motdepasse')**
  changez 'nomLogin' par votre nom d'utilisateur SQL et mettez votre mots de passe SQL dans 'motdepasse'
- après vous mettez les droits nécessaires au lancement des scripts sh, ``` chmod 700 (les droits que vous voulez mettre) fichier.sh``` . Faire cette commande pour **scriptClient.sh**, **scriptCompil.sh**,**scriptServeur.sh**.


- Ensuite lancer d'abord le scriptClient.sh avec la commande ```./scriptCompil.sh```


- Après ouvrer un terminal pour lancer le serveur avec la commande ```./scriptServeur.sh```


- Après ouvrer autant de terminal que de client que vous voulez créer et dans chaque terminale lancer la commande ```./scriptClient.sh```



VILLETTE Arthur
MECHAIN Romain
