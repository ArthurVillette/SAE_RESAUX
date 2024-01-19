import com.google.gson.Gson;

public class GestionCommande {


    public GestionCommande() {

    }

    public String commandeToJson(String commande) {
        Gson gson = new Gson();
        Commande commandeObj = new Commande(commande);
        return gson.toJson(commandeObj);
    }

    public Commande jsonToCommande(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Commande.class);
    }
}
