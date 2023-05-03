package pojo;


import org.eclipse.jetty.websocket.api.Session;


public class User 
{
	private String  name;
	private Music   voteMusic;
	private Session session;

	/* Classe User permettant de stocker dans un meme objet :
	   le nom, la musique pour laquelle l'utilisateur a vote */

	public User( String name ) 
	{
		this.name = name;
	}
	
	/* Methode permettant de recuperer le nom de l'utilisateur actuel */
	public String getName() 
	{
		return name;
	}

	/* Methode permettant d'affecter un nom a l'utilisateur actuel */
	public void setName( String name ) 
	{
		this.name = name;
	}

	/* Methode permettant de recuperer la musique pour laquelle vote l'utilisateur actuel */
	public Music getVote() 
	{
		return voteMusic;
	}

	/* Methode permettant d'affecter une musique a l'utilisateur actuel */
	public void setVote( Music voteMusic )
	{
		this.voteMusic = voteMusic;
	}

	/* Methode permettant de recuperer le numero de session de l'utilisateur actuel */
	public Session getSession() 
	{
		return session;
	}


	/* Methode permettant d'affecter le numero de session a l'utilisateur actuel */
	public void setSession( Session session ) 
	{
		this.session = session;
	}

}
