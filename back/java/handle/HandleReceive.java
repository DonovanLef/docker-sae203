package handle;


import com.fasterxml.jackson.databind.JsonNode;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.eclipse.jetty.websocket.api.Session;

import control.Controleur;

import pojo.Message;
import pojo.Music;
import pojo.User;

import util.JsonUtil;
import util.Mp3Util;
import util.SocketUtil;
import util.Util;


public class HandleReceive {

	private final JsonNode jsonNode;
	private final Session  session;
	private final User     user;

	/* Classe HandleReceive permettant de stocker dans un meme objet :
	   les requetes reçues par le socket */
	
	public HandleReceive( String request, Session session ) {
		this.jsonNode = JsonUtil.toJsonNode( request );
		this.session  = session;
		this.user = SocketUtil.getUserBySession( session );
	}

	/* Methode permettant d'appeler la methode en fonction du type de la requete */
	public void run() {
		switch ( jsonNode.get("type").textValue() ) {
			case "connection"  -> connection () ;
			case "newMsg"      -> newMsg     () ;
			case "newVote"     -> newVote    () ;
		}
	}

	/* Methode permettant de connecter un utilisateur au serveur, elle verifie
	   aussi le pseudo entre par l'utilisateur et creer un objet User pour celui-ci */
	private void connection() {
		if ( !Util.verifPseudo( this.jsonNode.get("content").textValue() ) ) return;
		User user = new User( this.jsonNode.get("content").textValue() );
		user.setSession( session );
		SocketUtil.addUser( user );
	};

	/* Methode permettant d'envoyer le message d'un utilisateur, elle recupère le nom
	   de l'utilisateur et l'heure a laquelle le message a ete envoye et verifie le message 
	   Cette methode appelle ensuite la methode sendMsg de HandleSend pour envoyer le message a tous */
	private void newMsg() {
		String name  = this.user.getName();
		String msg   = this.jsonNode.get("content").textValue();
		String heure = Util.getTime();
		if ( !Util.verifMessage(msg) ) return;
		Message message = new Message( msg, name, heure );
		HandleSend.sendMsg( message );
	};

	/* Methode permettant a un utilisateur de voter pour une musique, elle verifie que
	   l'ancien vote de l'utilisateur n'est pas le même que le nouveau. Cette methode appelle
	   ensuite la methode sendAllMusic de HandleSend pour mettre a jour toutes les musiques pour tous */
	private void newVote() {

		Music newMusicVote = Controleur.getMusicById( jsonNode.get("content").intValue() );
		if ( newMusicVote == null ) return;

		Music lastMusicVote = user.getVote();
		if ( lastMusicVote != null ) if ( lastMusicVote.equals(newMusicVote) ) return;
		if ( lastMusicVote != null ) lastMusicVote.retirerVote();

		newMusicVote.ajouterVote();
		user.setVote( newMusicVote );

		HandleSend.sendAllMusic();
	};

	public static void importMusic( String title, String author, String music){
		byte[] bytes = Base64.getDecoder().decode(music);

		Mp3Util.buildMp3Music(bytes, title, author);

		// HandleSend.sendAllMusic();
	}
}
