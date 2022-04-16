package chess.pieces;

public class Queen extends ChessPieces{
    public Queen() {
    }

    public Queen(Boolean color) {
        setNumberOfSafe(0);
        setNumberOfUnderThreat(0);
        setColor(color);
    }
}
