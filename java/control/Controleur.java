package control;


import java.io.File;
import java.util.ArrayList;

import pojo.Music;
import pojo.User;

import util.SocketUtil;


public class Controleur
{
	private static ArrayList<Music> tabMusic = new ArrayList<Music>();

	/* Méthode genMusic permettant de récuperer toutes les musiques contenues dans 
	   le dossier mp3 et de les stocker dans la liste tabMusic d'objets Music*/
	public static void genMusic() {
		tabMusic.clear();
		String path = Controleur.class.getResource("/mp3/").getPath();
		File   repertoire = new File( path );
		File[] decksfiles = repertoire.listFiles();

		for ( int i=0 ; i<decksfiles.length ; i++ )
		{
			String auteur = DecomposeurMusic( decksfiles[i].getName() )[0];
			String titre  = DecomposeurMusic( decksfiles[i].getName() )[1];
			tabMusic.add( new Music( titre, auteur, decksfiles[i].getName() ) );
		}

	}

	/* Méthode clearVote permettant de reinitialiser tous les votes à 0 */
	public static void clearVote() {
		for ( Music m : tabMusic )
			m.clearVote();
		for ( User u : SocketUtil.listUser )
			u.setVote(null);
	}

	/* Méthode DecomposeurMusic permet de séparer le titre de la musique de son artiste,
	   suivant le formatage choisi au départ. On a ici [ ARTISTE - TITRE ] */
	
	public static String[] DecomposeurMusic( String musique ) {
		String sTemp  = "";
		String[] sRet = new String[2];
		for ( int ind=0 ; ind<musique.length() ; ind++ )
		{
			if ( musique.charAt(ind) == '-' )
			{
				sRet[0] = sTemp;
				sTemp   = "";
			}
			else 
			{
				sTemp += musique.charAt( ind );
			}
		}
		sRet[1] = sTemp.substring( 0, sTemp.length()-4 );
		return sRet;
	}

	/* Méthode getMusicById permettant de récupérer une musique par son emplacement
	   dans tabMusic */
	public static Music getMusicById( int id ) {
		for ( Music m : tabMusic )
			if ( m.getId() == id )
				return m;
		return null;
	}

	/* Méthode getListMusic permettant de récupérer tabMusic, la liste de toutes les Musiques */
	public static ArrayList<Music> getListMusic() {
		return tabMusic;
	}

}