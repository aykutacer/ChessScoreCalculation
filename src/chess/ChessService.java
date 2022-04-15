package chess;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChessService implements IChessService {
    ///beyaz taşlar
    public Pawn pawnWhite = new Pawn(false);//false beyaz olduğunu belirtiyor
    public Knight knightWhite = new Knight(false);
    public Bishop bishopWhite = new Bishop(false);
    public Rook rookWhite = new Rook(false);
    public Queen queenWhite = new Queen(false);
    public King kingWhite = new King(false);

    ///siyah taşlar
    public Pawn pawnBlack = new Pawn(true);//true siyah olduğunu belirtiyor
    public Knight knightBlack = new Knight(true);
    public Bishop bishopBlack = new Bishop(true);
    public Rook rookBlack = new Rook(true);
    public Queen queenBlack = new Queen(true);
    public King kingBlack = new King(true);

    public ChessService() {
    }

    public String[][] getTextToMatris(char[] textToRead) {
        String[][] chessBoard = new String[invariants.LINE][invariants.COLUMN];
        int textIndex = invariants.TEXT_INDEX;

        for (int i = 0; i < invariants.LINE; i++) {
            for (int t = 0; t < invariants.COLUMN; t++) {
                chessBoard[i][t] = String.valueOf(textToRead[textIndex]) + String.valueOf(textToRead[textIndex + 1]);
                textIndex += 2;
            }
        }

        return chessBoard;
    }

    public List<BigDecimal> createScore(String[][] chessBoard) {
        List<BigDecimal> totalScores = new ArrayList<BigDecimal>();

        for (int i = 0; i < invariants.LINE; i++) {
            for (int j = 0; j < invariants.COLUMN; j++) {
                switch (chessBoard[i][j].substring(0, 1)) {
                    case "k":
                        rookCheck(chessBoard, i, j, chessBoard[i][j]);
                        break;
                    case "a":
                        knightCheck(chessBoard, i, j, chessBoard[i][j]);
                        break;
                    case "f":
                        bishopCheck(chessBoard, i, j, chessBoard[i][j]);
                        break;
                    case "v":
                        queenCheck(chessBoard, i, j, chessBoard[i][j]);
                        break;
                    case "s":
                        kingCheck(chessBoard, i, j, chessBoard[i][j]);
                        break;
                    case "p":
                        pawnCheck(chessBoard, i, j, chessBoard[i][j]);
                        break;
                    default:
                        break;
                }
            }
        }

        totalScores.add(scoreCalculation(pawnWhite, knightWhite, bishopWhite, rookWhite, queenWhite, kingWhite));//beyeaz için hesaplama
        totalScores.add(scoreCalculation(pawnBlack, knightBlack, bishopBlack, rookBlack, queenBlack, kingBlack));//siyah için hesaplama
        return totalScores;

    }

    public BigDecimal scoreCalculation(Pawn pawn, Knight knight, Bishop bishop, Rook rook, Queen queen, King king) {
        BigDecimal total = new BigDecimal("0");
        BigDecimal pawnScore = new BigDecimal("0");
        BigDecimal knightScore = new BigDecimal("0");
        BigDecimal bishopScore = new BigDecimal("0");
        BigDecimal rookScore = new BigDecimal("0");
        BigDecimal queenScore = new BigDecimal("0");
        BigDecimal kingScore = new BigDecimal("0");
        pawnScore = new BigDecimal("1").multiply(new BigDecimal(pawn.getNumberOfSafe())).add(new BigDecimal("0.5").multiply(new BigDecimal(pawn.getNumberOfUnderThreat())));
        knightScore = new BigDecimal("3").multiply(new BigDecimal(knight.getNumberOfSafe())).add(new BigDecimal("1.5").multiply(new BigDecimal(knight.getNumberOfUnderThreat())));
        bishopScore = new BigDecimal("3").multiply(new BigDecimal(bishop.getNumberOfSafe())).add(new BigDecimal("1.5").multiply(new BigDecimal(bishop.getNumberOfUnderThreat())));
        rookScore = new BigDecimal("5").multiply(new BigDecimal(rook.getNumberOfSafe())).add(new BigDecimal("2.5").multiply(new BigDecimal(rook.getNumberOfUnderThreat())));
        queenScore = new BigDecimal("9").multiply(new BigDecimal(queen.getNumberOfSafe())).add(new BigDecimal("4.5").multiply(new BigDecimal(queen.getNumberOfUnderThreat())));
        kingScore = new BigDecimal("100").multiply(new BigDecimal(king.getNumberOfSafe())).add(new BigDecimal("50").multiply(new BigDecimal(king.getNumberOfUnderThreat())));
        total = pawnScore.add(knightScore.add(bishopScore.add(rookScore.add(queenScore.add(kingScore)))));
        return total;

    }

    private void whitePiecesScenarios(String chessBoard) {
        if (invariants.WHITE_ROOK.equals(chessBoard)) {
            rookWhite.setNumberOfUnderThreat(rookWhite.getNumberOfUnderThreat() + 1);
            rookWhite.setNumberOfSafe(rookWhite.getNumberOfSafe() - 1);
        } else if (invariants.WHITE_KNIGHT.equals(chessBoard)) {
            knightWhite.setNumberOfUnderThreat(knightWhite.getNumberOfUnderThreat() + 1);
            knightWhite.setNumberOfSafe(knightWhite.getNumberOfSafe() - 1);
        } else if (invariants.WHITE_BISHOP.equals(chessBoard)) {
            bishopWhite.setNumberOfUnderThreat(bishopWhite.getNumberOfUnderThreat() + 1);
            bishopWhite.setNumberOfSafe(bishopWhite.getNumberOfSafe() - 1);
        } else if (invariants.WHITE_QUEEN.equals(chessBoard)) {
            queenWhite.setNumberOfUnderThreat(queenWhite.getNumberOfUnderThreat() + 1);
            queenWhite.setNumberOfSafe(queenWhite.getNumberOfSafe() - 1);
        } else if (invariants.WHITE_KING.equals(chessBoard)) {
            kingWhite.setNumberOfUnderThreat(kingWhite.getNumberOfUnderThreat() + 1);
            kingWhite.setNumberOfSafe(kingWhite.getNumberOfSafe() - 1);
        }else if (invariants.WHITE_PAWN.equals(chessBoard)) {
            pawnWhite.setNumberOfUnderThreat(pawnWhite.getNumberOfUnderThreat() + 1);
            pawnWhite.setNumberOfSafe(pawnWhite.getNumberOfSafe() - 1);
        }
    }

    private void blackPiecesScenarios(String chessBoard) {
        if (invariants.BLACK_ROOK.equals(chessBoard)) {
            rookBlack.setNumberOfUnderThreat(rookBlack.getNumberOfUnderThreat() + 1);
            rookBlack.setNumberOfSafe(rookBlack.getNumberOfSafe() - 1);
        } else if (invariants.BLACK_KNIGHT.equals(chessBoard)) {
            knightBlack.setNumberOfUnderThreat(knightBlack.getNumberOfUnderThreat() + 1);
            knightBlack.setNumberOfSafe(knightBlack.getNumberOfSafe() - 1);
        } else if (invariants.BLACK_BISHOP.equals(chessBoard)) {
            bishopBlack.setNumberOfUnderThreat(bishopBlack.getNumberOfUnderThreat() + 1);
            bishopBlack.setNumberOfSafe(bishopBlack.getNumberOfSafe() - 1);
        } else if (invariants.BLACK_QUEEN.equals(chessBoard)) {
            queenBlack.setNumberOfUnderThreat(queenBlack.getNumberOfUnderThreat() + 1);
            queenBlack.setNumberOfSafe(queenBlack.getNumberOfSafe() - 1);
        } else if (invariants.BLACK_KING.equals(chessBoard)) {
            kingBlack.setNumberOfUnderThreat(kingBlack.getNumberOfUnderThreat() + 1);
            kingBlack.setNumberOfSafe(kingBlack.getNumberOfSafe() - 1);
        }else if (invariants.BLACK_PAWN.equals(chessBoard)) {
            pawnBlack.setNumberOfUnderThreat(pawnBlack.getNumberOfUnderThreat() + 1);
            pawnBlack.setNumberOfSafe(pawnBlack.getNumberOfSafe() - 1);
        }
    }

    private void rookCheck(String[][] chessBoard, int i, int j, String pieces) {
        String color = "";

        if (pieces.equals(invariants.BLACK_ROOK)) {
            color = invariants.BLACK;
        } else {
            color = invariants.WHITE;
        }
        for (int controlToTheRight = j + 1; controlToTheRight < invariants.BOARD_BORDER; controlToTheRight++) {
            if (chessBoard[i][controlToTheRight] != null && chessBoard[i][controlToTheRight].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[i][controlToTheRight] != null && chessBoard[i][controlToTheRight].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[i][controlToTheRight]);
                } else {
                    blackPiecesScenarios(chessBoard[i][controlToTheRight]);
                }
            }
        }

        for (int controlToTheLeft = j - 1; controlToTheLeft >= 0; controlToTheLeft--) {
            if (chessBoard[i][controlToTheLeft] != null && chessBoard[i][controlToTheLeft].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[i][controlToTheLeft] != null && chessBoard[i][controlToTheLeft].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[i][controlToTheLeft]);
                } else {
                    blackPiecesScenarios(chessBoard[i][controlToTheLeft]);
                }
            }
        }
        for (int controlToTheDown = i + 1; controlToTheDown < invariants.BOARD_BORDER; controlToTheDown++) {
            if (chessBoard[controlToTheDown][j] != null && chessBoard[controlToTheDown][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[controlToTheDown][j] != null && chessBoard[controlToTheDown][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[controlToTheDown][j]);
                } else {
                    blackPiecesScenarios(chessBoard[controlToTheDown][j]);
                }
            }
        }

        for (int controlToTheUp = i - 1; controlToTheUp >= 0; controlToTheUp--) {
            if (chessBoard[controlToTheUp][j] != null && chessBoard[controlToTheUp][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[controlToTheUp][j] != null && chessBoard[controlToTheUp][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[controlToTheUp][j]);
                } else {
                    blackPiecesScenarios(chessBoard[controlToTheUp][j]);
                }
            }
        }

    }

    private void bishopCheck(String[][] chessBoard, int i, int j, String pieces) {
        int line = i;
        int column = j;
        String color = "";

        if (pieces.equals(invariants.BLACK_BISHOP)) {
            color = invariants.BLACK;
        } else {
            color = invariants.WHITE;
        }

        for (int rightDownControl = 0; rightDownControl < 7; rightDownControl++) {
            line++;
            column++;

            if (line<invariants.BOARD_BORDER && column<invariants.BOARD_BORDER && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (line<invariants.BOARD_BORDER && column<invariants.BOARD_BORDER && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column]);
                } else {
                    blackPiecesScenarios(chessBoard[line][column]);
                }
            }
        }
        line = i;
        column = j;

        for (int leftUpControl = 0; leftUpControl < 7; leftUpControl++) {
            line--;
            column--;
            if (line >= 0 && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (line >= 0 && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column]);
                } else {
                    blackPiecesScenarios(chessBoard[line][column]);
                }
            }
        }
        line = i;
        column = j;
        for (int leftDownControl = 0; leftDownControl < 7; leftDownControl++) {
            line++;
            column--;
            if (line<invariants.BOARD_BORDER && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (line<invariants.BOARD_BORDER && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column]);
                } else {
                    blackPiecesScenarios(chessBoard[line][column]);
                }
            }
        }
        line = i;
        column = j;
        for (int rightUpControl = 0; rightUpControl < 7; rightUpControl++) {
            line--;
            column++;
            if (column<invariants.BOARD_BORDER && line >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (column<invariants.BOARD_BORDER && line >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column]);
                } else {
                    blackPiecesScenarios(chessBoard[line][column]);
                }
            }
        }
    }

    private void queenCheck(String[][] chessBoard, int i, int j, String pieces) {
        int line = i;
        int column = j;
        String color = "";

        if (pieces.equals(invariants.BLACK_QUEEN)) {
            color = invariants.BLACK;
        } else {
            color = invariants.WHITE;
        }

        for (int rightDownControl = 0; rightDownControl < 7; rightDownControl++) {
            line++;
            column++;

            if (line<invariants.BOARD_BORDER && column<invariants.BOARD_BORDER &&  chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (line<invariants.BOARD_BORDER && column<invariants.BOARD_BORDER && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column]);
                } else {
                    blackPiecesScenarios(chessBoard[line][column]);
                }
            }
        }
        line = i;
        column = j;

        for (int leftUpControl = 0; leftUpControl < 7; leftUpControl++) {
            line--;
            column--;
            if (line >= 0 && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (line >= 0 && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column]);
                } else {
                    blackPiecesScenarios(chessBoard[line][column]);
                }
            }
        }
        line = i;
        column = j;
        for (int leftDownControl = 0; leftDownControl < 7; leftDownControl++) {
            line++;
            column--;
            if (line<invariants.BOARD_BORDER && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (line<invariants.BOARD_BORDER && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column]);
                } else {
                    blackPiecesScenarios(chessBoard[line][column]);
                }
            }
        }
        line = i;
        column = j;
        for (int rightUpControl = 0; rightUpControl < 7; rightUpControl++) {
            line--;
            column++;
            if (column<invariants.BOARD_BORDER && line >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (column<invariants.BOARD_BORDER &&  line >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column]);
                } else {
                    blackPiecesScenarios(chessBoard[line][column]);
                }
            }
        }
        for (int controlToTheRight = j + 1; controlToTheRight < invariants.BOARD_BORDER; controlToTheRight++) {
            if (chessBoard[i][controlToTheRight] != null && chessBoard[i][controlToTheRight].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[i][controlToTheRight] != null && chessBoard[i][controlToTheRight].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[i][controlToTheRight]);
                } else {
                    blackPiecesScenarios(chessBoard[i][controlToTheRight]);
                }
            }
        }

        for (int controlToTheLeft = j - 1; controlToTheLeft >= 0; controlToTheLeft--) {
            if (chessBoard[i][controlToTheLeft] != null && chessBoard[i][controlToTheLeft].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[i][controlToTheLeft] != null && chessBoard[i][controlToTheLeft].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[i][controlToTheLeft]);
                } else {
                    blackPiecesScenarios(chessBoard[i][controlToTheLeft]);
                }
            }
        }
        for (int controlToTheDown = i + 1; controlToTheDown < invariants.BOARD_BORDER; controlToTheDown++) {
            if (chessBoard[controlToTheDown][j] != null && chessBoard[controlToTheDown][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[controlToTheDown][j] != null && chessBoard[controlToTheDown][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[controlToTheDown][j]);
                } else {
                    blackPiecesScenarios(chessBoard[controlToTheDown][j]);
                }
            }
        }

        for (int controlToTheUp = i - 1; controlToTheUp >= 0; controlToTheUp--) {
            if (chessBoard[controlToTheUp][j] != null && chessBoard[controlToTheUp][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[controlToTheUp][j] != null && chessBoard[controlToTheUp][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[controlToTheUp][j]);
                } else {
                    blackPiecesScenarios(chessBoard[controlToTheUp][j]);
                }
            }
        }
    }

    private void knightCheck(String[][] chessBoard, int i, int j, String pieces) {
        String color = "";

        if (pieces.equals(invariants.BLACK_KNIGHT)) {
            color = invariants.BLACK;
        } else {
            color = invariants.WHITE;
        }

        // 2 üst 1 sağ hareketi
        if (i >= 2 && j + 1 < 8 && chessBoard[i - 2][j + 1] != null && chessBoard[i - 2][j + 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i - 2][j + 1]);
            } else {
                blackPiecesScenarios(chessBoard[i - 2][j + 1]);
            }
        }

        // 2 üst 1 sol hareketi
        if (i >= 2 && j >= 1 && chessBoard[i - 2][j - 1] != null && chessBoard[i - 2][j - 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i - 2][j - 1]);
            } else {
                blackPiecesScenarios(chessBoard[i - 2][j - 1]);
            }
        }
        // 1 üst 2 sağ hareketi
        if (i >= 1 && j + 2 < invariants.BOARD_BORDER && chessBoard[i - 1][j + 2] != null && chessBoard[i - 1][j + 2].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i - 1][j + 2]);
            } else {
                blackPiecesScenarios(chessBoard[i - 1][j + 2]);
            }
        }

        // 1 üst 2 sol hareketi
        if (i >= 1 && j >= 2 && chessBoard[i - 1][j - 2] != null && chessBoard[i - 1][j - 2].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i - 1][j - 2]);
            } else {
                blackPiecesScenarios(chessBoard[i - 1][j - 2]);
            }
        }

        // 2 aşağı 1 sağ hareketi
        if (i + 2 < invariants.BOARD_BORDER && chessBoard[i + 2][j + 1] != null && chessBoard[i + 2][j + 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i + 2][j + 1]);
            } else {
                blackPiecesScenarios(chessBoard[i + 2][j + 1]);
            }
        }

        // 2 aşağı 1 sol hareketi
        if (i + 2 < invariants.BOARD_BORDER && j >= 1 && chessBoard[i + 2][j - 1] != null && chessBoard[i + 2][j - 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i + 2][j - 1]);
            } else {
                blackPiecesScenarios(chessBoard[i + 2][j - 1]);
            }
        }
        // 1 aşağı 2 sağ hareketi
        if (i + 1 < invariants.BOARD_BORDER && j + 2 < invariants.BOARD_BORDER && chessBoard[i + 1][j + 2] != null && chessBoard[i + 1][j + 2].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i + 1][j + 2]);
            } else {
                blackPiecesScenarios(chessBoard[i + 1][j + 2]);
            }
        }

        // 1 aşağı 2 sol hareketi
        if (i + 1 < invariants.BOARD_BORDER && j >= 2 && chessBoard[i + 1][j - 2] != null && chessBoard[i + 1][j - 2].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i + 1][j - 2]);
            } else {
                blackPiecesScenarios(chessBoard[i + 1][j - 2]);
            }
        }


    }

    private void kingCheck(String[][] chessBoard, int i, int j, String pieces) {
        String color = "";
        if (pieces.equals(invariants.BLACK_KING)) {
            color = invariants.BLACK;
        } else {
            color = invariants.WHITE;
        }
        //yukarı 1 kare
        if (i - 1 >= 0 && chessBoard[i - 1][j] != null && chessBoard[i - 1][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i - 1][j]);
            } else {
                blackPiecesScenarios(chessBoard[i - 1][j]);
            }
        }
        //aşağı 1 kare
        if (i + 1 < invariants.BOARD_BORDER && chessBoard[i + 1][j] != null && chessBoard[i + 1][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i + 1][j]);
            } else {
                blackPiecesScenarios(chessBoard[i + 1][j]);
            }
        }
        //sağa 1 kare
        if (j + 1 < invariants.BOARD_BORDER && chessBoard[i][j + 1] != null && chessBoard[i][j + 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i][j + 1]);
            } else {
                blackPiecesScenarios(chessBoard[i][j + 1]);
            }
        }
        //sola 1 kare
        if (j - 1 >= 0 && chessBoard[i][j - 1] != null && chessBoard[i][j - 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i][j - 1]);
            } else {
                blackPiecesScenarios(chessBoard[i][j - 1]);
            }
        }
        //sağ üst 1 kare
        if (j + 1 < invariants.BOARD_BORDER && i - 1 >= 0 && chessBoard[i - 1][j + 1] != null && chessBoard[i - 1][j + 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i - 1][j + 1]);
            } else {
                blackPiecesScenarios(chessBoard[i - 1][j + 1]);
            }
        }
        //sol üst 1 kare
        if (i - 1 >= 0 && j - 1 >= 0 && chessBoard[i - 1][j - 1] != null && chessBoard[i - 1][j - 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i - 1][j - 1]);
            } else {
                blackPiecesScenarios(chessBoard[i - 1][j - 1]);
            }
        }
        //sağ alt 1 kare
        if (i + 1 < invariants.BOARD_BORDER && j + 1 < invariants.BOARD_BORDER && chessBoard[i + 1][j + 1] != null && chessBoard[i + 1][j + 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i + 1][j + 1]);
            } else {
                blackPiecesScenarios(chessBoard[i + 1][j + 1]);
            }
        }
        //sol alt 1 kare
        if (i + 1 < invariants.BOARD_BORDER && j - 1 >= 0 && chessBoard[i + 1][j - 1] != null && chessBoard[i + 1][j - 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i + 1][j - 1]);
            } else {
                blackPiecesScenarios(chessBoard[i + 1][j - 1]);
            }
        }
    }

    private void pawnCheck(String[][] chessBoard, int i, int j, String pieces) {
        if (pieces.equals(invariants.BLACK_PAWN)) {
            //eğer piyon siyah renkliyse yukarıdan aşağı hareket edeceği için sadece sağ alt ve sol alttaki taşlara tehdit olacaktır

            //sağ alt 1 kare
            if (i + 1 < invariants.BOARD_BORDER && j + 1 < invariants.BOARD_BORDER && chessBoard[i + 1][j + 1] != null && chessBoard[i + 1][j + 1].substring(1).equals(invariants.WHITE)) {
                whitePiecesScenarios(chessBoard[i + 1][j + 1]);
            }
            //sol alt 1 kare
            if (i + 1 < invariants.BOARD_BORDER && j - 1 >= 0 && chessBoard[i + 1][j - 1] != null && chessBoard[i + 1][j - 1].substring(1).equals(invariants.WHITE)) {
                whitePiecesScenarios(chessBoard[i + 1][j - 1]);
            }
        } else {
            //eğer piyon beyaz renkliyse aşağıdan yukarı hareket edeceği için sadece sağ üst ve sol üstteki taşlara tehdit olacaktır

            //sağ üst 1 kare
            if (j + 1 < invariants.BOARD_BORDER && i - 1 >= 0 && chessBoard[i - 1][j + 1] != null && chessBoard[i - 1][j + 1].substring(1).equals(invariants.BLACK)) {
                blackPiecesScenarios(chessBoard[i - 1][j + 1]);
            }
            //sol üst 1 kare
            if (i - 1 >= 0 && j - 1 >= 0 && chessBoard[i - 1][j - 1] != null && chessBoard[i - 1][j - 1].substring(1).equals(invariants.BLACK)) {
                blackPiecesScenarios(chessBoard[i - 1][j - 1]);
            }
        }
    }

}
