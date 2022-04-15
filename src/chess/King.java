package chess;

public class King extends ChessPieces{
    public King() {
    }

    public King(Boolean color) {
        setNumberOfSafe(1);
        setNumberOfUnderThreat(0);
        setColor(color);
    }
}