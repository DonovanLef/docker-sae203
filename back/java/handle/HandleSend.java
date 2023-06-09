package handle;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.websocket.api.Session;

import control.Controleur;

import pojo.Message;

import util.JsonUtil;
import util.Mp3Util;
import util.SocketUtil;


public class HandleSend
{

	/* Methode permettant d'envoyer un message a tous les utilisateurs actuellement connectes */
	public static void sendMsg( Message message ) 
	{
		Map<String, Object> mapSend = new HashMap<String, Object>();
		mapSend.put( "type", "nouveauMessage"  );
		mapSend.put( "content", message );
		String json = JsonUtil.toJson( mapSend );
		SocketUtil.sendMessages( json );
	}

	/* Methode permettant d'envoyer toutes les musiques aux utilisateurs actuellement connectes */
	public static void sendAllMusic() 
	{
		Collections.sort( Controleur.getListMusic() );
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "type", "affichageMusic" );
		map.put( "content", Controleur.getListMusic() );
		String json = JsonUtil.toJson( map );
		SocketUtil.sendMessages( json );
	}

	/* Methodes permettant de mettre a jour la musique actuellement jouee */
	public static void sendMajMusic() 
	{
		majMusic( true, null );
	}

	public static void sendMajMusic( Session session ) 
	{
		majMusic( false, session );
	}

	private static void majMusic( boolean all, Session session ) 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "type", "changerMusique" );
		map.put( "content", Mp3Util.getMusic() );
		map.put( "currentTime", Mp3Util.currentTime() );
		String json = JsonUtil.toJson( map );
		if ( all )
			SocketUtil.sendMessages( json );
		else
			SocketUtil.sendMessageToOneUser( json, session );
	}
}
