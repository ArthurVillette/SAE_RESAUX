import java.util.concurrent.CopyOnWriteArrayList;
import com.google.gson.Gson;

/**
 * la classe GestionMessage permet de gérer les messages
 */
public class GestionMessage {
    private CopyOnWriteArrayList<Message> messages;

    /**
     * le constructeur de la classe GestionMessage crée une liste de messages
     */
    public GestionMessage() {
        this.messages = new CopyOnWriteArrayList<>();
    }

    /**
     * ajoute un message à la liste de messages.
     * 
     * @param message le message à ajouter
     */
    public void addMessage(Message message) {
        this.messages.add(message);
    }

    /**
     * affiche la liste de messages
     * @return la liste de messages
     */
    public CopyOnWriteArrayList<Message> getMessages() {
        return this.messages;
    }

    /**
     *donne l'ID maximum des messages
     * 
     * @return l'ID maximum
     */
    public int getMaximumId() {
        int max = 0;
        for (Message message : this.messages) {
            if (message.getId() > max) {
                max = message.getId();
            }
        }
        return max;
    }

    /**
     * convertis un message en JSON.
     * 
     * @param message le message à convertir
     * @return le message en JSON
     */
    public String messageToJson(Message message) {
        Gson gson = new Gson();
        return gson.toJson(message);
    }

    /**
     * convertis un JSON en message.
     * 
     * @param json le JSON à convertir en message
     * @return le message
     */
    public Message jsonToMessage(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Message.class);
    }

    /**
     * ajoute un like au message avec l'ID spécifié.
     * 
     * @param id l'ID du message
     * @return true si le message est trouvé, false sinon
     */
    public boolean likeMessage(int id) {
        for (Message message : this.messages) {
            if (message.getId() == id) {
                message.addLike();
                return true;
            }
        }
        return false;
    }

    /**
     * donne le nombre de likes du message avec l'ID spécifié.
     * 
     * @param id l'ID du message
     * @return le nombre de likes du message
     */
    public Integer getLikes(int id) {
        for (Message message : this.messages) {
            if (message.getId() == id) {
                return message.getLikes();
            }
        }
        return null;
    }
}
