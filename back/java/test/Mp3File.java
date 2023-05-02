package test;
import util.Mp3Util;

import java.io.FileInputStream;
import java.io.IOException;

public class Mp3File {

	public static void main(String[] a) throws IOException{
		FileInputStream fis = new FileInputStream("../../mp3/aaaa.txt");

		Mp3Util.buildMp3Music(fis.readAllBytes(), "aa", "bb");
	}
	
}
