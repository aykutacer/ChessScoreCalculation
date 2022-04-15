package chess;

public class Knight extends ChessPieces {
    public Knight() {
    }

    public Knight(Boolean color) {
        setNumberOfSafe(2);
        setNumberOfUnderThreat(0);
        setColor(color);
    }
}
