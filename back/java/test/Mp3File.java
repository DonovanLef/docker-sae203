package test;
import util.Mp3Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import handle.HandleReceive;

public class Mp3File {

	public static void main(String[] a) throws IOException{
		FileInputStream fis = new FileInputStream("../../mp3/Dr. Peacock - Trip to Valhalla.mp3");


		HandleReceive.importMusic("title", "author", new String(Base64.getEncoder().encode(fis.readAllBytes())));

		//Mp3Util.buildMp3Music(new String(fis.readAllBytes(), StandardCharsets.UTF_8), "aa", "bb");
	}
	
}
