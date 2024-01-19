import java.util.concurrent.CopyOnWriteArrayList;
import com.google.gson.Gson;

public class GestionMessage {
    private CopyOnWriteArrayList<Message> messages;

    public GestionMessage() {
        this.messages = new CopyOnWriteArrayList<>();
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public CopyOnWriteArrayList<Message> getMessages() {
        return this.messages;
    }

    public int getMaximumId() {
        int max = 0;
        for (Message message : this.messages) {
            if (message.getId() > max) {
                max = message.getId();
            }
        }
        return max;
    }

    public String messageToJson(Message message) {
        Gson gson = new Gson();
        return gson.toJson(message);
    }

    public Message jsonToMessage(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Message.class);
    }

    public boolean likeMessage(int id) {
        for (Message message : this.messages) {
            if (message.getId() == id) {
                message.addLike();
                return true;
            }
        }
        return false;
    }

}
