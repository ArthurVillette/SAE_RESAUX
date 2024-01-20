import com.google.gson.Gson;

/**
 * la classe GestionCommande permet de gérer les commandes
 */
public class GestionCommande {

    /**
     * Constructeur de la classe GestionCommande.
     */
    private GestionCommande() {

    }

    /**
     * convertis une commande en JSON.
     *
     * @param commande la commande à convertir
     * @return la commande en JSON
     */
    public static String commandeToJson(String commande) {
        Gson gson = new Gson();
        Commande commandeObj = new Commande(commande);
        return gson.toJson(commandeObj);
    }

    /**
     * convertis un JSON en commande.
     *
     * @param json le JSON à convertir
     * @return la commande
     */
    public static Commande jsonToCommande(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Commande.class);
    }
}
