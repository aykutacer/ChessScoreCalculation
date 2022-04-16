package chess.pieces;

public class Pawn extends ChessPieces {
    public Pawn() {
    }

    public Pawn(Boolean color) {
        setNumberOfSafe(0);
        setNumberOfUnderThreat(0);
        setColor(color);
    }
}
