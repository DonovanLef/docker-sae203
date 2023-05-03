package pojo;


public class Message
{

	private String message;
	private String user;
	private String heure;

	/* Classe Message permettant de stocker dans un meme objet :
	   la date, l'utlisateur et le contenu du messsage */

	public Message ( String message, String user, String heure ) 
	{
		this.message = message;
		this.user    = user;
		this.heure   = heure;
	}

	/* Methode getMessage permettant de recuperer le contenu du message */
	public String getMessage() 
	{
		return message;
	}

	/* Methode getUser permettant de recuperer l'utilisateur qui a envoye le message */
	public String getUser() 
	{
		return user;
	}
	
	/* Methode getHeure permettant de recuperer l'heure a laquelle le message a ete envoye */
	public String getHeure() 
	{
		return heure;
	}
}
