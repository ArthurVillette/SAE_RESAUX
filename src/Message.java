public class Message{
    private int id; 
    private String nomUtilisateur;
    private String content;
    private String date;
    private int likes;

    public Message(int id, String nomUtilisateur, String content){
        this.id=id;
        this.nomUtilisateur=nomUtilisateur;
        this.content=content;
        this.date= new java.util.Date().toString();
        this.likes=0;

    }
    public int getId(){
        return this.id;
    }
    public String getNomUtilisateur(){
        return this.nomUtilisateur;
    }
    public String getContent(){
        return this.content;
    }
    public String getDate(){
        return this.date;
    }
    public int getLikes(){
        return this.likes;
    }
    public void setLikes(int likes){
        this.likes=likes;
    }
    public void setcontent(String content){
        this.content=content;
        this.date= new java.util.Date().toString();
    }
    public void addLike(){
        this.likes++;
    }

    @Override
    public String toString(){
        return this.id + " " + this.nomUtilisateur + " " + this.content + " " + this.date + " " + this.likes;
    }

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