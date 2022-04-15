package chess.services;

import chess.pieces.*;

import java.math.BigDecimal;
import java.util.List;

public interface IChessService {
        String[][] getTextToMatris(char[] textToRead);
        List<BigDecimal> createScore(String[][] chessBoard);
        BigDecimal scoreCalculation(Pawn pawnWhite, Knight knightWhite, Bishop bishopWhite, Rook rookWhite, Queen queenWhite, King kingWhite);

}
