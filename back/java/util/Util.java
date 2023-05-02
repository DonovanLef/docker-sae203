package util;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class Util {

	/* Méthode getTime permettant de récupérer l'heure exacte en format HH:MM */
	public static String getTime() {
		String sRet = "";
		GregorianCalendar gc = new GregorianCalendar( Locale.FRENCH );
		sRet += String.format( "%02d", gc.get( GregorianCalendar.HOUR_OF_DAY ) ) + ":";
		sRet += String.format( "%02d", gc.get( GregorianCalendar.MINUTE      ) );
		return sRet;
	}

	/* Méthode verifPseudo qui vérifie grace à un Regex si le pseudo donné en paramètre:
	   contient uniquement des lettres ou des chiffres et fait entre 2 et 20 caractères */
	public static boolean verifPseudo( String input ) {
		String regex = "^[a-zA-Z0-9]{2,20}$";
		Pattern pattern = Pattern.compile( regex );
		return pattern.matcher(input).matches();

	}

	/* Méthode verifMessage permettant de verifier que le message n'est pas vide ou 
	   que le message n'est pas trop long */
	public static boolean verifMessage( String input ) {
		String message = "";
		for ( int i=0 ; i<input.length() ; i++ ) { message += " "; }
		return ( !input.equals( message ) ) && ( input.length() < 240 );
	}

}
