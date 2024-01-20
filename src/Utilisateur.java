/**
 * Classe Utilisateur qui représente un utilisateur.
 */
public class Utilisateur {
    private String nom;
    private String mdp;
    /**
     * Constructeur de la classe Utilisateur.
     * @param nom le nom de l'utilisateur
     * @param mdp le mot de passe de l'utilisateur
     */
    public Utilisateur(String nom, String mdp) {
        this.nom = nom;
        this.mdp = mdp;
    }

    /**
     * donne le nom de l'utilisateur.
     * 
     * @return le nom de l'utilisateur
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * donne le mot de passe de l'utilisateur.
     * 
     * @return le mot de passe de l'utilisateur
     */
    public String getMdp() {
        return this.mdp;
    }
}
