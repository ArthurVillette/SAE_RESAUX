import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class GestionUtilisateurs {
    private ConnexionMySQL connexionMySQL;

    public GestionUtilisateurs(ConnexionMySQL connexionMySQL) {
        this.connexionMySQL = connexionMySQL;
    }

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
}