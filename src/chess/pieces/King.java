package chess.pieces;

public class King extends ChessPieces{
    public King() {
    }

    public King(Boolean color) {
        setNumberOfSafe(0);
        setNumberOfUnderThreat(0);
        setColor(color);
    }
}