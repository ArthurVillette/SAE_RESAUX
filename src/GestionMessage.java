import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import com.google.gson.Gson;

/**
 * la classe GestionMessage permet de gérer les messages
 */
public class GestionMessage {
    private ConnexionMySQL connexionMySQL;


    public GestionMessage( ConnexionMySQL connexionMySQL){
        this.connexionMySQL = connexionMySQL;
    }

    // public GestionMessage() {
    //     this.connexionMySQL = null;
    // }

    public void addMessage(Message message) {
        try {
            Statement statement = this.connexionMySQL.createStatement();
            statement.executeUpdate("INSERT INTO MESSAGE_U VALUES (" + message.getId() + ", '" + message.getNomUtilisateur() + "', '" + message.getContent() + "', '" + message.getDate() + "');");
            System.out.println("Message ajouté");
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la création du statement");
        }

    }

    /**
     *donne l'ID maximum des messages
     * 
     * @return l'ID maximum
     */
    public int getMaximumId() {
        try {
            Statement statement = this.connexionMySQL.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(idMessage) FROM MESSAGE_U;");
            if (resultSet.next()) {
                return resultSet.getInt("MAX(idMessage)");
            }
            else {
                return 0;
            }
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la création du statement");
            return 0;
        }
    }

    /**
     * convertis un message en JSON.
     * @param message le message à convertir
     * @return le message en JSON
     */
    public static String messageToJson(Message message) {
        Gson gson = new Gson();
        return gson.toJson(message);
    }

    /**
     * convertis un JSON en message.
     * @param json le JSON à convertir
     * @return le message
     */
    public static Message jsonToMessage(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Message.class);
    }

    /**
     * donne le message avec l'ID spécifié.
     * 
     * @param id l'ID du message
     * @return le message
     */
    public boolean likeMessage(int id, String nomUtilisateur) {
        try {
            Statement statement = this.connexionMySQL.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MESSAGE_U WHERE idMessage = " + id + ";");
        
            if (resultSet.next()) {
                ResultSet userLiked = statement.executeQuery("SELECT * FROM A_LIKE WHERE nomUtilisateur = '" + nomUtilisateur + "' AND idMessage = " + id + ";");
                if (userLiked.next()) {
                    return false; // L'utilisateur a déjà aimé le message
                } else {
                    statement.executeUpdate("INSERT INTO A_LIKE (nomUtilisateur, idMessage) VALUES ('" + nomUtilisateur + "', " + id + ");");
                    return true;
                }
            } else {
                return false; // Aucun message ne correspond à l'ID fourni

            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du statement");
            return false;
        }
        
    }

    /**
     * donne le nombre de likes du message avec l'ID spécifié.
     * 
     * @param id l'ID du message
     * @return le nombre de likes du message
     */
    public Integer getLikes(int id) {
        try {
            Statement statement = this.connexionMySQL.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM A_LIKE WHERE idMessage = " + id + ";");
            if (resultSet.next()) {
                return resultSet.getInt("COUNT(*)");
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

    public boolean deleteU (int id, String nomUtilisateur) {
        try {
            Statement statement = this.connexionMySQL.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MESSAGE_U WHERE idMessage = " + id + " AND nomUtilisateur = '" + nomUtilisateur + "';");
            if (resultSet.next()) {
                statement.executeUpdate("DELETE FROM MESSAGE_U WHERE idMessage = " + id + " AND nomUtilisateur = '" + nomUtilisateur + "';");
                return true;
            } else {
                return false; // Aucun message ne correspond à l'ID fourni
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du statement");
            return false;
        }
    }

    public List<Message> getMessages(String nomUtilisateur) {
        try {
            Statement statement = this.connexionMySQL.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MESSAGE_U WHERE nomUtilisateur = '" + nomUtilisateur + "';");
            List<Message> messages = new ArrayList<Message>();
            while (resultSet.next()) {
                messages.add(new Message(resultSet.getInt("idMessage"), resultSet.getString("nomUtilisateur"), resultSet.getString("contenu"), resultSet.getString("dateMessage")));
            }
            return messages;
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la création du statement");
            return null;
        }
    }

    public boolean deleteS (int id) {
        try {
            Statement statement = this.connexionMySQL.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM MESSAGE_U WHERE idMessage = " + id + ";");
            if (resultSet.next()) {
                statement.executeUpdate("DELETE FROM MESSAGE_U WHERE idMessage = " + id + ";");
                return true;
            } else {
                return false; 
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du statement");
            return false;
        }
    }
}
