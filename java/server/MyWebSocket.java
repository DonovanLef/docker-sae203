package server;


import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import handle.HandleReceive;

import util.SocketUtil;


public class MyWebSocket extends WebSocketAdapter {

	/* Méthode permettant de gérer la connection d'un utilisateur */
	@Override
	public void onWebSocketConnect( Session session ) {
		super.onWebSocketConnect( session );
		System.out.println( "Socket connected: " );
	}

	/* Méthode permettant de gérer le message d'un utilisateur */
	@Override
	public void onWebSocketText( String message ) {
		super.onWebSocketText( message );
		new HandleReceive( message, getSession()).run();
		System.out.println( "Received message: " + message );
	}

	/* Méthode permettant de gérer la déconnection d'un utilisateur */
	@Override
	public void onWebSocketClose( int statusCode, String reason ) {
		SocketUtil.removeUser( getSession() );
		super.onWebSocketClose( statusCode, reason );
		System.out.println( "Socket closed: [" + statusCode + "] " + reason );
	}

	/* Méthode permettant de gérer les erreurs de connection/deconnection d'un utilisateur */
	@Override
	public void onWebSocketError( Throwable cause ) {
		SocketUtil.removeUser( getSession() );
		super.onWebSocketError( cause );
		System.out.println( "Erreur WebSocket: " + cause.getMessage() );
	}


}