function envoiNouveauMessage( content ) {
	websocket.send( `{ "type": "newMsg", "content": "${content}"}` );
	inputSendMsg.value = "";
}

function envoiVote( idMusique ) {
	websocket.send( `{ "type": "newVote", "content": ${idMusique}}` );
}

function importMusic( title, author, file ) {

	//let str = new TextDecoder().decode(file);
	console.log(file);
	fetch("http://localhost:8080/importMusic", {
		method: 'POST',
		body: JSON.stringify({ title: title, author: author, music: file})
	})
		.then(response => response.text())
		.then(data => console.log(data));
}

