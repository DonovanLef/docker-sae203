function webSocketCon( a ) {

	websocket = new WebSocket( `ws:${serverAdress}/ws` );

	websocket.addEventListener( 'open', () => {
		websocket.send( `{ "type": "connection", "content": "${a}" }` );
	})

	websocket.addEventListener( 'message', (event) => {
		const jsonObject = JSON.parse( event.data );
		switcher( jsonObject );
	});

}

function switcher( jsonObject ) {

	switch ( jsonObject.type ) {

		case "affichageMusic":
			afficherMusique( jsonObject.content )
			break;

		case "nouveauMessage":
			afficherMessage( jsonObject.content );
			break;
		
		case "changerMusique":
			changerMusique( jsonObject.content, jsonObject.currentTime )
			break;
	}
	
}