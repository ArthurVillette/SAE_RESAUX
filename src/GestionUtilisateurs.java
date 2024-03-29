import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 * la classe GestionUtilisateurs permet de gérer les utilisateurs.
 */
public class GestionUtilisateurs {
    private ConnexionMySQL connexionMySQL;
    /**
     * Constructeur de la classe GestionUtilisateurs.
     * @param connexionMySQL la connexion à la base de données
     */
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

    /**
     * permet de follow un utilisateur.
     * @param nomUtilisateur le nom de l'utilisateur qui follow
     * @param nomUtilisateurSuivi le nom de l'utilisateur qui est follow
     * @return true si l'utilisateur a été follow, false sinon
     */
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
    /**
     * permet de unfollow un utilisateur.
     * @param nomUtilisateur le nom de l'utilisateur qui unfollow
     * @param nomUtilisateurSuivi le nom de l'utilisateur qui est unfollow
     * @return true si l'utilisateur a été unfollow, false sinon
     */
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
    /**
     * regarde si un utilisateur suit un autre utilisateur.
     * @param nomUtilisateur le nom de l'utilisateur qui suit
     * @param nomUtilisateurSuivi le nom de l'utilisateur qui est suivi
     * @return true si l'utilisateur suit l'autre utilisateur, false sinon
     */
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
    /**
     * donne la liste des followers d'un utilisateur.
     * @param nomUtilisateur le nom de l'utilisateur
     * @return la liste des followers
     */
    public List<String> getFollowers(String nomUtilisateur) {
        try {
            Statement statement = this.connexionMySQL.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nomUtilisateur FROM ABONNEMENT WHERE nomUtilisateurAbonnee = '" + nomUtilisateur + "';");
            List<String> followers = new ArrayList<String>();
            while (resultSet.next()) {
                followers.add(resultSet.getString("nomUtilisateur"));
            }
            return followers;
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la création du statement");
            return null;
        }
    }

    /**
     * donne la liste des utilisateurs suivis par un utilisateur.
     * 
     * @param nomUtilisateur le nom de l'utilisateur
     * @return la liste des utilisateurs suivis
     */
    public List<String> getFollowed(String nomUtilisateur) {
        try {
            Statement statement = this.connexionMySQL.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nomUtilisateurAbonnee FROM ABONNEMENT WHERE nomUtilisateur = '" + nomUtilisateur + "';");
            List<String> followed = new ArrayList<String>();
            while (resultSet.next()) {
                followed.add(resultSet.getString("nomUtilisateurAbonnee"));
            }
            return followed;
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la création du statement");
            return null;
        }
    }
    /**
     * supprime un utilisateur en tant qu'admin.
     * @param nomUtilisateur le nom de l'utilisateur
     * @return true si l'utilisateur a été supprimé, false sinon
     */
    public boolean removeUtilisateur(String nomUtilisateur) {
        if (!this.userExists(nomUtilisateur)) {
            return false;
        }
        try {
            Statement statement = this.connexionMySQL.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT idMessage FROM MESSAGE_U WHERE nomUtilisateur = '" + nomUtilisateur + "';");
            while (resultSet.next()) {
                int idMessage = resultSet.getInt("idMessage");
                Statement updateStatement = this.connexionMySQL.createStatement();
                updateStatement.executeUpdate("DELETE FROM A_LIKE WHERE idMessage = " + idMessage + ";");
            }
            statement.executeUpdate("DELETE FROM MESSAGE_U WHERE nomUtilisateur = '" + nomUtilisateur + "';");
            statement.executeUpdate("DELETE FROM ABONNEMENT WHERE nomUtilisateurAbonnee = '" + nomUtilisateur + "';");
            statement.executeUpdate("DELETE FROM ABONNEMENT WHERE nomUtilisateur = '" + nomUtilisateur + "';");
            statement.executeUpdate("DELETE FROM UTILISATEUR WHERE nomUtilisateur = '" + nomUtilisateur + "';");
    
            return true;
        } catch (Exception e) {
            System.out.println("Une erreur ici");
            e.printStackTrace();
            return false;
        }
    }
}