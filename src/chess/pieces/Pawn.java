package chess.pieces;

public class Pawn extends ChessPieces {
    public Pawn() {
    }

    public Pawn(Boolean color) {
        setNumberOfSafe(8);
        setNumberOfUnderThreat(0);
        setColor(color);
    }
}
