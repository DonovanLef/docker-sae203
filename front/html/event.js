let btnSendMsg   = document.querySelector( ".btnSendMsg"   );
let inputSendMsg = document.querySelector( ".inputSendMsg" );

listenerMessageButton = btnSendMsg.addEventListener( 'click', () => {
	envoiNouveauMessage( inputSendMsg.value );
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