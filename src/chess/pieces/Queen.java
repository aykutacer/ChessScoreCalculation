package chess.pieces;

public class Queen extends ChessPieces{
    public Queen() {
    }

    public Queen(Boolean color) {
        setNumberOfSafe(1);
        setNumberOfUnderThreat(0);
        setColor(color);
    }
}
