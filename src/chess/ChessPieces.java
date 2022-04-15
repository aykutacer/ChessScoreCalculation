package chess;

public abstract class ChessPieces {
    private Boolean color = Boolean.FALSE; ///0 beyaz 1 siyah
    private int numberOfUnderThreat;
    private int numberOfSafe;

    public Boolean getColor() {
        return color;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }

    public int getNumberOfUnderThreat() {
        return numberOfUnderThreat;
    }

    public void setNumberOfUnderThreat(int numberOfUnderThreat) {
        this.numberOfUnderThreat = numberOfUnderThreat;
    }

    public int getNumberOfSafe() {
        return numberOfSafe;
    }

    public void setNumberOfSafe(int numberOfSafe) {
        this.numberOfSafe = numberOfSafe;
    }
}
