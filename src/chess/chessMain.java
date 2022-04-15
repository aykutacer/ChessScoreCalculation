package chess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class chessMain {
    public static char[] textToRead;
    public static IChessService chessService = new ChessService();
    public static List<BigDecimal> totalScores = new ArrayList<BigDecimal>();

    public static void main(String[] args) throws Exception {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(invariants.FILE_NAME));
            textToRead = reader.readLine().toCharArray();
            if (textToRead.length > 128) {
                throw new Exception(Errors.FAZLA_BOYUT_HATASI.getKod());
            } else if (textToRead.length < 128) {
                throw new Exception(Errors.EKSIK_BOYUT_HATASI.getKod());
            } else {
                totalScores =  chessService.createScore(chessService.getTextToMatris(textToRead));
            }
            System.out.println("Beyaz = "+totalScores.get(0));
            System.out.println("Siyah = "+totalScores.get(1));

        } catch (FileNotFoundException e) {
            //eğer chess.txt adlı dosyamız yoksa hata fırlatıyoruz
            throw new Exception(Errors.DOSYA_BULUNAMADI.getKod());
        }
    }



}
