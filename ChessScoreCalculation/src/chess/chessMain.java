package chess;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class chessMain {

	public static void main(String[] args) throws Exception {

		//chess.txt adl� dosyam�z� i�indeki verileri �ekmek i�in okuyoruz
		File readText = new File("chess.txt");
		try {
		BufferedReader reader = new BufferedReader(new FileReader(readText));
		}catch (FileNotFoundException e) {
			//e�er chess.txt adl� dosyam�z yoksa hata f�rlat�yoruz
			throw new Exception(Errors.DOSYA_BULUNAMADI.getKod());				
		}		
	}

}
