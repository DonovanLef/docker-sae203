package util;

import handle.HandleSend;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import pojo.Music;
import control.Controleur;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.Collections;

public class Mp3Util {

	private static Player player;
	private static Music music;

	/* Méthode play permet de jouer une musique donnée en paramètre */
	public static void play( Music newMusic ) {

		music = newMusic;
		System.out.println( newMusic );
		try {
			FileInputStream fis = new FileInputStream(Mp3Util.class.getResource("/mp3/").getPath()+music.getFileName());
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player( bis );
			HandleSend.sendMajMusic();
			new Thread( () -> {
				try {
					player.play();
					Collections.sort(Controleur.getListMusic());
					Music m = Controleur.getListMusic().get(0);
					Controleur.clearVote();
					HandleSend.sendAllMusic();
					play( m );
				} catch ( JavaLayerException e ) { e.printStackTrace(); };
			} ).start();
		}catch ( FileNotFoundException | JavaLayerException e ) { e.printStackTrace(); };
	
	}

	/* Méthode permettant de récupérer le temps actuel de la musique */
	public static long currentTime() {
		return player.getPosition();
	}
	
	/* Méthode permettant de récupérer la musique jouée actuellement */
	public static Music getMusic() {
		return music;
	}

}
