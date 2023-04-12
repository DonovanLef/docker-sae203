var websocket;

function connection() {
	let a = prompt( "Entrez votre nom d'utilisateur [a-z/A-Z/0-9]" );
	webSocketCon(a);
}

connection();