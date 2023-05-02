package handle;


import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.jetty.websocket.api.Session;

import control.Controleur;

import pojo.Message;
import pojo.Music;
import pojo.User;

import util.JsonUtil;
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

	/* Méthode permettant d'appeler la méthode en fonction du type de la requete */
	public void run() {
		switch ( jsonNode.get("type").textValue() ) {
			case "connection"  -> connection () ;
			case "newMsg"      -> newMsg     () ;
			case "newVote"     -> newVote    () ;
		}
	}

	/* Méthode permettant de connecter un utilisateur au serveur, elle vérifie
	   aussi le pseudo entré par l'utilisateur et créer un objet User pour celui-ci */
	private void connection() {
		if ( !Util.verifPseudo( this.jsonNode.get("content").textValue() ) ) return;
		User user = new User( this.jsonNode.get("content").textValue() );
		user.setSession( session );
		SocketUtil.addUser( user );
	};

	/* Méthode permettant d'envoyer le message d'un utilisateur, elle récupère le nom
	   de l'utilisateur et l'heure à laquelle le message a été envoyé et vérifie le message 
	   Cette méthode appelle ensuite la méthode sendMsg de HandleSend pour envoyer le message à tous */
	private void newMsg() {
		String name  = this.user.getName();
		String msg   = this.jsonNode.get("content").textValue();
		String heure = Util.getTime();
		if ( !Util.verifMessage(msg) ) return;
		Message message = new Message( msg, name, heure );
		HandleSend.sendMsg( message );
	};

	/* Méthode permettant à un utilisateur de voter pour une musique, elle vérifie que
	   l'ancien vote de l'utilisateur est pas le même que le nouveau. Cette méthode appelle
	   ensuite la méthode sendAllMusic de HandleSend pour mettre à jour toutes les musiques pour tous */
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
}
