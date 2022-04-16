package chess.services;

import chess.pieces.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface IChessService {
        String[][] getTextToMatris(char[] textToRead);
        List<BigDecimal> createScore(String[][] chessBoard);
        BigDecimal scoreCalculation(Pawn pawnWhite, Knight knightWhite, Bishop bishopWhite, Rook rookWhite, Queen queenWhite, King kingWhite);
        char[] getTextToRead(BufferedReader reader) throws IOException;

}
