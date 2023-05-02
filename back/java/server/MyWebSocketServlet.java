package server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import handle.HandleReceive;
import util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;


public class MyWebSocketServlet extends WebSocketServlet {

	private final int NB_MINUTES = 10;

	/* Methode permettant de gerer la configuration du serveur */
	@Override
	public void configure( WebSocketServletFactory factory ) {
		factory.getPolicy().setIdleTimeout( 60*1000*NB_MINUTES );
		factory.register( MyWebSocket.class );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.setHeader("Access-Control-Allow-Origin", "localhost");
		
		if ( request.getRequestURI().equals("/importMusic")){

			StringBuilder requestBody = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line = null;
			while ((line = reader.readLine()) != null) {
				requestBody.append(line);
			}
			String requestBodyString = requestBody.toString();
			JsonNode jsonNode = JsonUtil.toJsonNode(requestBodyString);
			System.out.println((jsonNode.get("music").textValue()));
			HandleReceive.importMusic(jsonNode.get("title").textValue(), jsonNode.get("author").textValue(), jsonNode.get("music").textValue());

		}
	}

}