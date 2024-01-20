import java.util.List;
import java.util.ArrayList;

public class GestionUtilisateurs {
    private List<Utilisateur> utilisateurs;

    public GestionUtilisateurs() {
        this.utilisateurs = new ArrayList<>();
    }

    public void addUser(String nom, String mdp) {
        this.utilisateurs.add(new Utilisateur(nom, mdp));
    }

    public boolean userExists(String nom) {
        for (Utilisateur utilisateur : this.utilisateurs) {
            if (utilisateur.getNom().equals(nom)) {
                return true;
            }
        }
        return false;
    }

    public Utilisateur getUtilisateur(String nom) {
        for (Utilisateur utilisateur : this.utilisateurs) {
            if (utilisateur.getNom().equals(nom)) {
                return utilisateur;
            }
        }
        return null;
    }
}