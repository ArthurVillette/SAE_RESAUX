import java.util.List;
import java.util.ArrayList;

/**
 * la classe GestionUtilisateurs permet de gérer les utilisateurs.
 */
public class GestionUtilisateurs {
    private List<Utilisateur> utilisateurs;

    public GestionUtilisateurs() {
        this.utilisateurs = new ArrayList<>();
    }

    /**
     * ajoute un utilisateur à la liste d'utilisateurs.
     * @param nom le nom de l'utilisateur
     * @param mdp le mot de passe de l'utilisateur
     */
    public void addUser(String nom, String mdp) {
        this.utilisateurs.add(new Utilisateur(nom, mdp));
    }

    /**
     * regarde si un utilisateur existe par son nom.
     * @param nom le nom de l'utilisateur
     * @return true si l'utilisateur existe, false sinon
     */
    public boolean userExists(String nom) {
        for (Utilisateur utilisateur : this.utilisateurs) {
            if (utilisateur.getNom().equals(nom)) {
                return true;
            }
        }
        return false;
    }

    /**
     * donne un utilisateur par son nom.
     * @param nom le nom de l'utilisateur
     * @return l'utilisateur
     */
    public Utilisateur getUtilisateur(String nom) {
        for (Utilisateur utilisateur : this.utilisateurs) {
            if (utilisateur.getNom().equals(nom)) {
                return utilisateur;
            }
        }
        return null;
    }
}