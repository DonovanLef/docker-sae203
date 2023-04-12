let content_music = document.querySelector( ".content_music" );
let contentMsg    = document.querySelector( ".content_msg"   );

function afficherMusique( musics ) {
	let s = "";
	musics.forEach( element => {
		s += `
		<div data-id="${element.id}" class="music_item">
			<div class="main_data">
				<span class="title">${element.titre}</span>
				<span><span data-music-id-nb-vote="${element.id}" class="votes">${element.nbVote}</span><span> votes</span></span>
			</div>
			<div class="meta_data">
				<span>${element.artiste}</span>
			</div>
		</div>`;
	});
	content_music.innerHTML = s;
	startEventButtonsVote();
}

function afficherMessage( msg ) {
	contentMsg.innerHTML += `<p><span class="author">${msg.heure} ${msg.user} :</span> <span>${msg.message}</span></p>`;
	contentMsg.scrollTop = contentMsg.scrollHeight;
}

function changerMusique( musique, currentTime ) {
	console.log( musique );
	let element = document.querySelector( ".contentAudio" ); // est l'élément source de audio
	let s = `				
	<audio controls>
		<source class="srcAudio" src="mp3/${musique.fileName}" type="audio/mpeg">
	</audio>`;
	element.innerHTML = s;
	let audio = document.querySelector( "audio" ); // est l'élément source de audio

	
	function onCanPlayThrough() {
		audio.currentTime = currentTime / 1000;
		audio.play();
		audio.removeEventListener( "canplaythrough", onCanPlayThrough );
	}

	audio.addEventListener( "canplaythrough", onCanPlayThrough );

}