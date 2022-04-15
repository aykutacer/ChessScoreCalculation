package chess;

public class Bishop extends ChessPieces{
    public Bishop() {
    }

    public Bishop(Boolean color) {
        setNumberOfSafe(2);
        setNumberOfUnderThreat(0);
        setColor(color);
    }
}
