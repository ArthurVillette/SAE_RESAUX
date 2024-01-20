import java.sql.*;

public class ConnexionMySQL {
	private Connection mysql=null;
	private boolean connecte=false;
	public ConnexionMySQL() throws ClassNotFoundException{
	}

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
	public void close() throws SQLException {
		// fermer la connexion
		this.connecte=false;
	}

    	public boolean isConnecte() { return this.connecte;}
	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	
}
