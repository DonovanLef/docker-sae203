package pojo;


public class Message {

	private String message;
	private String user;
	private String heure;

	/* Classe Message permettant de stocker dans un meme objet :
	   la date, l'utlisateur et le contenu du messsage */

	public Message ( String message, String user, String heure ) {
		this.message = message;
		this.user    = user;
		this.heure   = heure;
	}

	/* Méthode getMessage permettant de récuperér le contenu du message */
	public String getMessage() {
		return message;
	}

	/* Méthode getUser permettant de récuperér l'utilisateur qui a envoyé le message */
	public String getUser() {
		return user;
	}
	
	/* Méthode getHeure permettant de récuperér l'heure à laquelle le message a été envoyé */
	public String getHeure() {
		return heure;
	}
}
