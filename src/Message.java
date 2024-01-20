/**
 * la classe Message permet de représenter un message
 */
public class Message{
    private int id; 
    private String nomUtilisateur;
    private String content;
    private String date;
    private int likes;

    /**
     * le constructeur de la classe Message crée un message
     * @param id l'ID du message unique
     * @param nomUtilisateur le nom de l'utilisateur qui a envoyé le message
     * @param content le contenu du message
     */
    public Message(int id, String nomUtilisateur, String content){
        this.id=id;
        this.nomUtilisateur=nomUtilisateur;
        this.content=content;
        this.date= new java.util.Date().toString();
        this.likes=0;
    }

    public Message(int id, String nomUtilisateur, String content, String date){
        this.id=id;
        this.nomUtilisateur=nomUtilisateur;
        this.content=content;
        this.date= date;
        this.likes=0;
    }

    /**
     * donne l'ID du message.
     * 
     * @return l'ID du message
     */
    public int getId(){
        return this.id;
    }

    /**
     * donne le nom de l'utilisateur qui a envoyé le message.
     * 
     * @return le nom de l'utilisateur
     */
    public String getNomUtilisateur(){
        return this.nomUtilisateur;
    }

    /**
     * donne le contenu du message.
     * 
     * @return le contenu du message
     */
    public String getContent(){
        return this.content;
    }

    /**
     * donne la date et l'heure d'envoie du message.
     * 
     * @return la date et l'heure du message
     */
    public String getDate(){
        return this.date;
    }

    /**
     * donne le nombre de likes du message.
     * 
     * @return le nombre de likes du message
     */
    public int getLikes(){
        return this.likes;
    }

    /**
     * modifie le nombre de likes du message.
     * 
     * @param likes le nouveau nombre de likes du message
     */
    public void setLikes(int likes){
        this.likes=likes;
    }

    /**
     * modifie le contenu du message.
     * 
     * @param content le nouveau contenu du message
     */
    public void setcontent(String content){
        this.content=content;
        this.date= new java.util.Date().toString();
    }

    /**
     * ajoute un like au message.
     */
    public void addLike(){
        this.likes++;
    }

    /**
     * affiche le message.
     * 
     * @return un string contenant l'ID, le nom de l'utilisateur, le contenu, la date et le nombre de likes du message
     */
    @Override
    public String toString(){
        return this.id + " " + this.nomUtilisateur + " " + this.content + " " + this.date + " " + this.likes;
    }

    /**
     * compare deux messages.
     * regarde si les deux messages ont le même ID.
     * 
     * @param o l'objet à comparer
     * @return true si les deux messages ont le même ID, false sinon
     */
    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Message)) {
            return false;
        }
        Message message = (Message) o;
        return this.id == message.id;
    }
}