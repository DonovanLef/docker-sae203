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

	public Music ( String titre, String artiste, String fileName ) {
		this.id        = ++count;
		this.titre     = titre;
		this.artiste   = artiste;
		this.fileName  = fileName;
		this.nbVote    = 0;
	}

	/* Méthode getTitre permettant de récupérer le titre de la musique */
	public String getTitre() {
		return this.titre;
	}

	/* Méthode getFileName permettant de récuperér le nom du fichier musique */
	public String getFileName() {
		return this.fileName;
	}
	
	/* Méthode getArtiste permettant de récupérer l'artiste de la musique */
	public String getArtiste() {
		return this.artiste;
	}

	/* Méthode getId permettant de récuperér l'ID unique généré de la musique */
	public int getId() {
		return this.id;
	}

	/* Méthode getNbVote permettant de récuperér le nombre de vote qu'a recu la musique (0 par défaut) */
	public int getNbVote() {
		return this.nbVote;
	}

	/* Méthode ajouterVote permettant d'ajouter un vote à la musique */
	public void ajouterVote() {
		this.nbVote ++;
	}

	/* Méthode retirerVote permettant de retirer 1 vote à la musique */
	public void retirerVote() {
		this.nbVote --;
	}

	/* Méthode clearVote permettant de remettre à 0 les votes de la musique */
	public void clearVote() {
		this.nbVote = 0;
	}

	/* Méthode toString retournant une version textuelle la musique */
	public String toString() {
		return this.getTitre()
		       +  "\n\tArtiste(s) : " + this.getArtiste();
	}
	
	/* Méthode compareTo permettant de comparer les votes des musiques pour pouvoir les trier */
	public int compareTo( Music music ) {
		return music.getNbVote() - this.nbVote; 
	}

}
