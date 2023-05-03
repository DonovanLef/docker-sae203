package util;

import handle.HandleSend;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;

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
	private static int position;

	/* Methode play permet de jouer une musique donnee en paramètre */
	public static void play( Music newMusic ) {

		music = newMusic;
		System.out.println( newMusic );
		try {
			String path = Mp3Util.class.getResource("/mp3/").getPath() + music.getFileName();
			//FileInputStream fis = new FileInputStream(Mp3Util.class.getResource("/mp3/").getPath() + music.getFileName());
			//BufferedInputStream bis = new BufferedInputStream(fis);
			//player = new Player( bis );
			HandleSend.sendMajMusic();

			new Thread( () -> {
				try {
					//player.play();
					position = 0;
					for ( int i = 0 ; i < getMusicDuration(path) ; i++ ){
						Thread.sleep(1000);
						position = position + 1;
					}
					Collections.sort(Controleur.getListMusic());
					Music m = Controleur.getListMusic().get(0);
					Controleur.clearVote();
					HandleSend.sendAllMusic();
					play( m );
				} catch ( Exception e ) { e.printStackTrace(); };
			} ).start();
		}catch ( Exception e ) { e.printStackTrace(); };
	
	}

	/* Methode permettant de recuperer le temps actuel de la musique */
	public static int currentTime() {
		return position;
	}
	
	/* Methode permettant de recuperer la musique jouee actuellement */
	public static Music getMusic() {
		return music;
	}

	public static boolean buildMp3Music( byte[] bytes, String title, String author ){
		boolean isMp3Music = true;

		try
		{

			System.out.println("build Musique");
			InputStream fis = new ByteArrayInputStream(bytes);
			long duration = getMusicDuration(fis);
			System.out.println( "Ajout musique, temps : " + getMusicDuration(fis));
			if ( duration == 0 || author.isEmpty() || title.isEmpty()) return false;
			/*BufferedInputStream buffered = new BufferedInputStream(fis);
			Player p = new Player( buffered );
			p.play(20);

			System.out.println(p.getPosition());
			

			p.close();*/
			FileOutputStream fos = new FileOutputStream(Controleur.class.getResource("/mp3/").getPath() + author + " - " + title + ".mp3"); 
			fos.write(bytes);
			fos.flush();

			Controleur.addMusic(new Music(title, author, author + " - " + title + ".mp3"));
			
		}
		catch (Exception e) {
			e.printStackTrace();
			isMp3Music = false;
		}

		System.out.println(isMp3Music);
		return isMp3Music;
	}

	private static long getMusicDuration(InputStream inputStream) throws Exception{
		Bitstream bitstream = new Bitstream(inputStream);
        long totalFrames = 0;
        double totalTime = 0.0;

        Header header;
        while ((header = bitstream.readFrame()) != null) {
            totalFrames++;
            totalTime += header.ms_per_frame();
            bitstream.closeFrame();
        }

        inputStream.close();
        bitstream.close();

        return (long) totalTime / 1000;
	}

	private static long getMusicDuration(String filePath) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(filePath);
		return getMusicDuration(fileInputStream);
    }

}
