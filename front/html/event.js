let btnSendMsg     = document.querySelector( ".btnSendMsg"     );
let inputSendMsg   = document.querySelector( ".inputSendMsg"   );

let btnImportMusic = document.querySelector( "#btnImportMusic" );
let inputAuthor    = document.querySelector( ".inputAuthor"    );
let inputTitle     = document.querySelector( ".inputTitle"     );
let inputMusic     = document.querySelector( ".inputFile"      );
 
listenerMessageButton = btnSendMsg.addEventListener( 'click', () => {
	envoiNouveauMessage( inputSendMsg.value );
})

listenerImportMusic = btnImportMusic.addEventListener( 'click', () => {

	let fichier = inputMusic.files[0];
	let lecteur = new FileReader();
	let utf8Encoder = new TextEncoder();

	lecteur.addEventListener("load", function() {
		
		console.log(btoa(lecteur.result));
		importMusic(inputTitle.value, inputAuthor.value, btoa(lecteur.result));
	});	

	lecteur.readAsBinaryString(fichier);
	
})


function startEventButtonsVote() {
	let buttonsVote = document.querySelectorAll( '.music_item' );
	buttonsVote.forEach( element => {
		element.addEventListener( 'click', () => {
			envoiVote( element.dataset.id );
		})
	});
}


document.addEventListener( 'keydown', function( event ) {
	if ( event.key === 'Enter' || event.keyCode === 13 ) {
		envoiNouveauMessage( inputSendMsg.value );
	}
});