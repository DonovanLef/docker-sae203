package server;


import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import handle.HandleReceive;

import util.SocketUtil;


public class MyWebSocket extends WebSocketAdapter {

	/* Methode permettant de gerer la connection d'un utilisateur */
	@Override
	public void onWebSocketConnect( Session session ) {
		super.onWebSocketConnect( session );
		System.out.println( "Socket connected: " );
	}

	/* Methode permettant de gerer le message d'un utilisateur */
	@Override
	public void onWebSocketText( String message ) {
		super.onWebSocketText( message );
		new HandleReceive( message, getSession()).run();
		System.out.println( "Received message: " + message );
	}

	/* Methode permettant de gerer la deconnection d'un utilisateur */
	@Override
	public void onWebSocketClose( int statusCode, String reason ) {
		SocketUtil.removeUser( getSession() );
		super.onWebSocketClose( statusCode, reason );
		System.out.println( "Socket closed: [" + statusCode + "] " + reason );
	}

	/* Methode permettant de gerer les erreurs de connection/deconnection d'un utilisateur */
	@Override
	public void onWebSocketError( Throwable cause ) {
		SocketUtil.removeUser( getSession() );
		super.onWebSocketError( cause );
		System.out.println( "Erreur WebSocket: " + cause.getMessage() );
	}


}