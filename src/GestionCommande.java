import com.google.gson.Gson;

public class GestionCommande {


    private GestionCommande() {

    }

    public static String commandeToJson(String commande) {
        Gson gson = new Gson();
        Commande commandeObj = new Commande(commande);
        return gson.toJson(commandeObj);
    }

    public static Commande jsonToCommande(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Commande.class);
    }
}
