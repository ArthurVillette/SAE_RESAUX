import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 * la classe GestionUtilisateurs permet de gérer les utilisateurs.
 */
public class GestionUtilisateurs {
    private ConnexionMySQL connexionMySQL;

    public GestionUtilisateurs(ConnexionMySQL connexionMySQL) {
        this.connexionMySQL = connexionMySQL;
    }

    /**
     * ajoute un utilisateur à la liste d'utilisateurs.
     * @param nom le nom de l'utilisateur
     * @param mdp le mot de passe de l'utilisateur
     */
    public void addUser(String nom, String mdp) {
        try {
            Statement statement = this.connexionMySQL.createStatement();
            statement.executeUpdate("INSERT INTO UTILISATEUR VALUES ('" + nom + "', '" + mdp + "');");
            System.out.println("Utilisateur ajouté");
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la création du statement");
        }
    }

    /**
     * regarde si un utilisateur existe par son nom.
     * @param nom le nom de l'utilisateur
     * @return true si l'utilisateur existe, false sinon
     */
    public boolean userExists(String nom) {
        try {
            Statement statement = this.connexionMySQL.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM UTILISATEUR WHERE nomUtilisateur = '" + nom + "';");
            if (resultSet.next()) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la création du statement");
            return false;
        }
    }

    /**
     * donne un utilisateur par son nom.
     * @param nom le nom de l'utilisateur
     * @return l'utilisateur
     */
    public Utilisateur getUtilisateur(String nom) {
        try {
            Statement statement = this.connexionMySQL.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM UTILISATEUR WHERE nomUtilisateur = '" + nom + "';");
            if (resultSet.next()) {
                return new Utilisateur(resultSet.getString("nomUtilisateur"), resultSet.getString("motDePasse"));
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la création du statement");
            return null;
        }
    }

    public boolean follow(String nomUtilisateur, String nomUtilisateurSuivi) {
        try {
            Statement statement = this.connexionMySQL.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ABONNEMENT WHERE nomUtilisateur = '" + nomUtilisateur + "' AND nomUtilisateurAbonnee = '" + nomUtilisateurSuivi + "';");
            if (resultSet.next()) {
                return false; // L'utilisateur le suit déjà
            } else {
                statement.executeUpdate("INSERT INTO ABONNEMENT VALUES ('" + nomUtilisateur + "', '" + nomUtilisateurSuivi + "');");
                return true;
            }
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la création du statement");
            return false;
        }
    }

    public boolean unfollow(String nomUtilisateur, String nomUtilisateurSuivi) {
        try {
            Statement statement = this.connexionMySQL.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ABONNEMENT WHERE nomUtilisateur = '" + nomUtilisateur + "' AND nomUtilisateurAbonnee = '" + nomUtilisateurSuivi + "';");
            if (resultSet.next()) {
                statement.executeUpdate("DELETE FROM ABONNEMENT WHERE nomUtilisateur = '" + nomUtilisateur + "' AND nomUtilisateurAbonnee = '" + nomUtilisateurSuivi + "';");
                return true;
            } else {
                return false; // L'utilisateur ne le suit pas déjà
            }
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la création du statement");
            return false;
        }
    }

    public boolean isFollowing(String nomUtilisateur, String nomUtilisateurSuivi) {
        try {
            Statement statement = this.connexionMySQL.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ABONNEMENT WHERE nomUtilisateur = '" + nomUtilisateur + "' AND nomUtilisateurAbonnee = '" + nomUtilisateurSuivi + "';");
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la création du statement");
            return false;
        }
    }
}