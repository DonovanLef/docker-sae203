package pojo;


import org.eclipse.jetty.websocket.api.Session;


public class User {
	private String  name;
	private Music   voteMusic;
	private Session session;

	/* Classe User permettant de stocker dans un meme objet :
	   le nom, la musique pour laquelle l'utilisateur a voté */

	public User( String name ) {
		this.name = name;
	}
	
	/* Méthode permettant de récupérer le nom de l'utilisateur actuel */
	public String getName() {
		return name;
	}

	/* Méthode permettant d'affecter un nom à l'utilisateur actuel */
	public void setName( String name ) {
		this.name = name;
	}

	/* Méthode permettant de récupérer la musique pour laquelle vote l'utilisateur actuel */
	public Music getVote() {
		return voteMusic;
	}

	/* Méthode permettant d'affecter une musique à l'utilisateur actuel */
	public void setVote( Music voteMusic ) {
		this.voteMusic = voteMusic;
	}

	/* Méthode permettant de récupérer le numéro de session de l'utilisateur actuel */
	public Session getSession() {
		return session;
	}


	/* Méthode permettant d'affecter le numéro de session à l'utilisateur actuel */
	public void setSession( Session session ) {
		this.session = session;
	}

}
