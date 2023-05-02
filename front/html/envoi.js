function envoiNouveauMessage( content ) {
	websocket.send( `{ "type": "newMsg", "content": "${content}"}` );
	inputSendMsg.value = "";
}

function envoiVote( idMusique ) {
	websocket.send( `{ "type": "newVote", "content": ${idMusique}}` );
}

function importMusic( title, author, file ) {
	websocket.send( `{"type" : "importMusic", "title": ${title}, "author": ${author}, "music": ${file}}`);	
}

