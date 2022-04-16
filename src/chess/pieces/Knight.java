package chess.pieces;

public class Knight extends ChessPieces {
    public Knight() {
    }

    public Knight(Boolean color) {
        setNumberOfSafe(0);
        setNumberOfUnderThreat(0);
        setColor(color);
    }
}
