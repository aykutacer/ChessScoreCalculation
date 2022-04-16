package chess;

import chess.errors.Errors;
import chess.services.ChessService;
import chess.services.IChessService;

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
    public static String readLines;

    public static void main(String[] args) throws Exception {

        try {
            //Dosyayı okumak için BufferedReader kullanıyoruz
            BufferedReader reader = new BufferedReader(new FileReader(invariants.FILE_NAME));
            //okuduğumuz stringi char dizisine çeviriyoruz
            for (int i = 0; i < 8; i++) {
                if (readLines == null) {
                    readLines = reader.readLine();
                } else {
                    readLines = readLines + reader.readLine();
                }
            }
            textToRead = readLines.replace(" ","").toCharArray();
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
