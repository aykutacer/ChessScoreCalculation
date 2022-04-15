package chess;

public class Rook extends ChessPieces{
    public Rook() {
    }

    public Rook(Boolean color) {
        setNumberOfSafe(2);
        setNumberOfUnderThreat(0);
        setColor(color);
    }
}
