package server;


import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;


public class MyWebSocketServlet extends WebSocketServlet {

	private final int NB_MINUTES = 10;

	/* Méthode permettant de gérer la configuration du serveur */
	@Override
	public void configure( WebSocketServletFactory factory ) {
		factory.getPolicy().setIdleTimeout( 60*1000*NB_MINUTES );
		factory.register( MyWebSocket.class );
	}

}