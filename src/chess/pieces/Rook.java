package chess.pieces;

public class Rook extends ChessPieces{
    public Rook() {
    }

    public Rook(Boolean color) {
        setNumberOfSafe(0);
        setNumberOfUnderThreat(0);
        setColor(color);
    }
}
