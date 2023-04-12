package util;

import handle.HandleSend;
import org.eclipse.jetty.websocket.api.Session;
import pojo.User;

import java.util.ArrayList;
import java.util.List;

public class SocketUtil {

	public static final List<User> listUser = new ArrayList<User>();

	/* Méthode addUser permettant d'ajouter un utilisateur à la liste listUser */
	public static void addUser( User user ) {
		listUser.add(user);
		HandleSend.sendAllMusic();
		HandleSend.sendMajMusic( user.getSession() );
	}

	/* Méthode removeUser permettant de retirer un utilisateur à la liste listUser.
	   On retire par la même occasion son vote s'il en a un */
	public static void removeUser ( Session session ) {

		User u = getUserBySession(session);
		if (u.getVote() != null) {  u.getVote().retirerVote(); }
		listUser.remove(u);
		HandleSend.sendAllMusic();
	}

	/* Méthode getUserBySession permettant de retourner le User en fonction de
	   la session donnée en paramètres */
	public static User getUserBySession( Session session ) {
		for ( User u : listUser )
			if ( u.getSession() == session )
				return u;
		return null;
	}

	/* Méthode sendMessageToOneUser permettant d'envoyer un message à un User spécifique */
	public static void sendMessageToOneUser( String message, Session session ) {
		session.getRemote().sendStringByFuture( message );
	}

	/* Méthode sendMessages permettant d'envoyer un message à tous les utilisateurs */
	public static void sendMessages( String message ){
		for ( User user : listUser )
			user.getSession().getRemote().sendStringByFuture( message );
	}


}
