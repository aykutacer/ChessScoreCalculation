package chess.pieces;

public class Bishop extends ChessPieces{
    public Bishop() {
    }

    public Bishop(Boolean color) {
        setNumberOfSafe(0);
        setNumberOfUnderThreat(0);
        setColor(color);
    }
}
