package server;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import control.Controleur;

import util.Mp3Util;


public class Main {

	/* Première methode a executer permettant de lancer le serveur */    
	public static void main( String[] args ) throws Exception {

		Controleur.genMusic();
		Mp3Util.play( Controleur.getListMusic().get( (int)(Math.random()*Controleur.getListMusic().size()) ) );

		Server server = new Server( 8080 );

		ServletContextHandler context = new ServletContextHandler( ServletContextHandler.SESSIONS );
		context.setContextPath( "/" );
		server.setHandler( context );

		ServletHolder servletHolder = new ServletHolder( "ws-events", MyWebSocketServlet.class );
		context.addServlet( servletHolder, "/*" );
		
		server.start();
		server.join();
	}

}