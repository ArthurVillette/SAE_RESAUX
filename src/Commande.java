/**
 * la classe Commande permet de représenter une commande
 */
public class Commande {
    private String commande;

    /**
     * Constructeur de la classe Commande.
     * 
     * @param commande la commande à envoyer
     */
    public Commande(String commande) {
        this.commande = commande;
    }

    /**
     * retourne la commande.
     * 
     * @return la commande
     */
    public String getCommande() {
        return commande;
    }

    /**
     * modifie la commande.
     * 
     * @param commande la nouvelle commande
     */
    public void setCommande(String commande) {
        this.commande = commande;
    }
}
