package chess;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class chessMain {

	public static void main(String[] args) throws Exception {

		//chess.txt adlý dosyamýzý içindeki verileri çekmek için okuyoruz
		File readText = new File("chess.txt");
		try {
		BufferedReader reader = new BufferedReader(new FileReader(readText));
		}catch (FileNotFoundException e) {
			//eðer chess.txt adlý dosyamýz yoksa hata fýrlatýyoruz
			throw new Exception(Errors.DOSYA_BULUNAMADI.getKod());				
		}		
	}

}
