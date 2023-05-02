package util;

import handle.HandleSend;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import pojo.Music;
import control.Controleur;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

public class Mp3Util {

	private static Player player;
	private static Music music;

	/* Methode play permet de jouer une musique donnee en paramètre */
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

	/* Methode permettant de recuperer le temps actuel de la musique */
	public static long currentTime() {
		return player.getPosition();
	}
	
	/* Methode permettant de recuperer la musique jouee actuellement */
	public static Music getMusic() {
		return music;
	}

	public static boolean buildMp3Music( byte[] bytes, String title, String author ){
		boolean isMp3Music = true;

		try
		{

			InputStream fis = new ByteArrayInputStream(bytes);
			BufferedInputStream buffered = new BufferedInputStream(fis);
			Player p = new Player( buffered );
			p.play(20);

			if ( p.getPosition() == 0 || author.isEmpty() || title.isEmpty()) return false;

			p.close();
			FileOutputStream fos = new FileOutputStream("../../mp3/" + author + " - " + title + ".mp3"); 
			fos.write(bytes);
			fos.flush();

			Controleur.addMusic(new Music(title, author, "../../mp3/" + author + " - " + title + ".mp3"));
			
		}
		catch (Exception e) {
			e.printStackTrace();
			isMp3Music = false;
		}

		System.out.println(isMp3Music);
		return isMp3Music;
	}

}
