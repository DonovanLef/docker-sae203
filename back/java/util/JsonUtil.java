package util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;


public class JsonUtil
{

	private static final ObjectMapper objectMapper = new ObjectMapper();

	/* Methode permettant de transformer un Objet en Json */
	public static String toJson( Object object ) 
	{
		try 
		{
			return objectMapper.writeValueAsString( object );
		} 
		catch ( JsonProcessingException e ) { throw new RuntimeException(e); }
	}

	/* Methode permettant de transformer une liste d'Objet en Json (par exemple les HashMap) */
	public static String toJson( ArrayList<Object> object ) 
	{
		try 
		{
			return objectMapper.writeValueAsString( object );
		} 
		catch ( JsonProcessingException e ) { throw new RuntimeException(e); }
	}

	/* Methode permettant de transformer un Json en Objet */
	public static <T> T toObject( String json, Class<T> clazz ) 
	{
		try 
		{
			return objectMapper.readValue( json, clazz );
		} 
		catch ( JsonProcessingException e ) { throw new RuntimeException(e); }
	}

	/* Methode permettant de transformer un Json en objet special quand la structure
	   de donnee est inconnue */
	public static JsonNode toJsonNode( String json ) 
	{
		try 
		{
			return objectMapper.readTree( json );
		} 
		catch ( JsonProcessingException e ) { throw new RuntimeException(e); }
	}

}
