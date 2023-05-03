package pojo;


public class Music implements Comparable<Music>
{
	private static int     count;
	private        int     id;
	private        String  titre;
	private        String  artiste;
	private        int     nbVote;
	private        String  fileName;


	/* Classe Music permettant de stocker dans un meme objet :
	   le titre, l'auteur, le nombre de vote et le chemin relatif de la musique */

	public Music ( String titre, String artiste, String fileName ) 
	{
		this.id        = ++count;
		this.titre     = titre;
		this.artiste   = artiste;
		this.fileName  = fileName;
		this.nbVote    = 0;
	}

	/* Methode getTitre permettant de recuperer le titre de la musique */
	public String getTitre() 
	{
		return this.titre;
	}

	/* Methode getFileName permettant de recuperer le nom du fichier musique */
	public String getFileName() 
	{
		return this.fileName;
	}
	
	/* Methode getArtiste permettant de recuperer l'artiste de la musique */
	public String getArtiste() 
	{
		return this.artiste;
	}

	/* Methode getId permettant de recuperer l'ID unique genere de la musique */
	public int getId()
	{
		return this.id;
	}

	/* Methode getNbVote permettant de recuperer le nombre de vote qu'a recu la musique (0 par defaut) */
	public int getNbVote() 
	{
		return this.nbVote;
	}

	/* Methode ajouterVote permettant d'ajouter un vote a la musique */
	public void ajouterVote() 
	{
		this.nbVote ++;
	}

	/* Methode retirerVote permettant de retirer 1 vote a la musique */
	public void retirerVote() 
	{
		this.nbVote --;
	}

	/* Methode clearVote permettant de remettre a 0 les votes de la musique */
	public void clearVote() 
	{
		this.nbVote = 0;
	}

	/* Methode toString retournant une version textuelle la musique */
	public String toString() 
	{
		return this.getTitre()
		       +  "\n\tArtiste(s) : " + this.getArtiste();
	}
	
	/* Methode compareTo permettant de comparer les votes des musiques pour pouvoir les trier */
	public int compareTo( Music music ) 
	{
		return music.getNbVote() - this.nbVote; 
	}

}
