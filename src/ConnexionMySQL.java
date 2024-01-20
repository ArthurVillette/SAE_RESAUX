import java.sql.*;
/**
 * Classe ConnexionMySQL permettant de se connecter à une base de données MySQL.
 */
public class ConnexionMySQL {
	private Connection mysql=null;
	private boolean connecte=false;
	/**
	 * Constructeur de la classe ConnexionMySQL.
	 * @throws ClassNotFoundException
	 */
	public ConnexionMySQL() throws ClassNotFoundException{
	}
	/**
	 * Permet de se connecter à une base de données MySQL.
	 * @param nomBase est le nom de la base de données
	 * @param nomLogin est le nom de l'utilisateur
	 * @param motDePasse est le mot de passe de l'utilisateur
	 * @throws SQLException est une exception
	 */
	public void connecter(String nomBase, String nomLogin, String motDePasse) throws SQLException{
		// si tout c'est bien passé la connexion n'est plus nulle
		
		Connection c;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+nomBase, nomLogin, motDePasse);
			this.mysql = c;
			this.connecte = true;
		}
		catch(ClassNotFoundException e) {
			System.out.println("Erreur classe non trouvee");
		}
		this.connecte=this.mysql!=null;
	}
	/**
	 * Permet de fermer la connexion à la base de données.
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		// fermer la connexion
		this.connecte=false;
	}
	/**
	 * Permet de savoir si la connexion est établie.
	 * @return true si la connexion est établie, false sinon
	 */
    	public boolean isConnecte() { return this.connecte;}
	/**
	 * Permet de créer une requête.
	 * @return la requête
	 * @throws SQLException
	 */
	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}
	/**
	 * Permet de préparer une requête.
	 * @param requete est la requête à préparer
	 * @return la requête préparée
	 * @throws SQLException
	 */
	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	
}
