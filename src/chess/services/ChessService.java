package chess.services;

import chess.*;
import chess.pieces.*;

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
                //matrisimize char dizimizden elemanları yerleştiriyoruz yani aslında burda 8x8 satranç tahtamıza taşlarımızı
                // text dosyasında verilen düzende yerleştiriyoruz
                chessBoard[i][t] = String.valueOf(textToRead[textIndex]) + String.valueOf(textToRead[textIndex + 1]);
                textIndex += 2;
            }
        }


        return chessBoard;
    }

    public List<BigDecimal> createScore(String[][] chessBoard) {
        List<BigDecimal> totalScores = new ArrayList<BigDecimal>();
        changePiecesNumber(chessBoard, pawnWhite, knightWhite, bishopWhite, rookWhite, queenWhite, kingWhite, pawnBlack, knightBlack, bishopBlack, rookBlack, queenBlack, kingBlack);

        for (int i = 0; i < invariants.LINE; i++) {
            for (int j = 0; j < invariants.COLUMN; j++) {
                switch (chessBoard[i][j].substring(0, 1)) {
                    //8*8 satranç tahtamızın her bir karesini gezerek üzerlerindeki taşlara göre işlem yapıyoruz
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

        totalScores.add(scoreCalculation(pawnWhite, knightWhite, bishopWhite, rookWhite, queenWhite, kingWhite));//beyaz için hesaplama
        totalScores.add(scoreCalculation(pawnBlack, knightBlack, bishopBlack, rookBlack, queenBlack, kingBlack));//siyah için hesaplama
        return totalScores;

    }

    private void changePiecesNumber(String[][] chessBoard, Pawn pawnWhite, Knight knightWhite, Bishop bishopWhite, Rook rookWhite, Queen queenWhite, King kingWhite, Pawn pawnBlack, Knight knightBlack, Bishop bishopBlack, Rook rookBlack, Queen queenBlack, King kingBlack) {
        for (int i = 0; i < invariants.LINE; i++) {
            for (int j = 0; j < invariants.COLUMN; j++) {
                switch (chessBoard[i][j]) {
                    case "kb":
                        rookWhite.setNumberOfSafe(rookWhite.getNumberOfSafe() + 1);
                        break;
                    case "ab":
                        knightWhite.setNumberOfSafe(knightWhite.getNumberOfSafe() + 1);
                        break;
                    case "fb":
                        bishopWhite.setNumberOfSafe(bishopWhite.getNumberOfSafe() + 1);
                        break;
                    case "vb":
                        queenWhite.setNumberOfSafe(queenWhite.getNumberOfSafe() + 1);
                        break;
                    case "sb":
                        kingWhite.setNumberOfSafe(kingWhite.getNumberOfSafe() + 1);
                        break;
                    case "pb":
                        pawnWhite.setNumberOfSafe(pawnWhite.getNumberOfSafe() + 1);
                        break;
                    case "ks":
                        rookBlack.setNumberOfSafe(rookBlack.getNumberOfSafe() + 1);
                        break;
                    case "as":
                        knightBlack.setNumberOfSafe(knightBlack.getNumberOfSafe() + 1);
                        break;
                    case "fs":
                        bishopBlack.setNumberOfSafe(bishopBlack.getNumberOfSafe() + 1);
                        break;
                    case "vs":
                        queenBlack.setNumberOfSafe(queenBlack.getNumberOfSafe() + 1);
                        break;
                    case "ss":
                        kingBlack.setNumberOfSafe(kingBlack.getNumberOfSafe() + 1);
                        break;
                    case "ps":
                        pawnBlack.setNumberOfSafe(pawnBlack.getNumberOfSafe() + 1);
                        break;
                    default:
                        break;
                }
            }
        }


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

    private void whitePiecesScenarios(String chessBoard, int underthreatLine, int underthreatColumn) {
        //kontrollerde buraya giriyorsa rakip taştan kendisine bir tehdit var demektir. Bunun için tehdit edilen taşın ne olduğunu bulup
        //tehdit sayısını 1 arttırıp güvende taş sayısını 1 azaltıyoruz.
        if (invariants.WHITE_ROOK.equals(chessBoard)) {
            if (rookWhite.getUnderThreatlocation() == null || !rookWhite.getUnderThreatlocation().contains(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn))) {
                rookWhite.getUnderThreatlocation().add(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn));
                rookWhite.setNumberOfUnderThreat(rookWhite.getNumberOfUnderThreat() + 1);
                rookWhite.setNumberOfSafe(rookWhite.getNumberOfSafe() - 1);
            }
        } else if (invariants.WHITE_KNIGHT.equals(chessBoard)) {
            if (knightWhite.getUnderThreatlocation() == null || !knightWhite.getUnderThreatlocation().contains(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn))) {
                knightWhite.getUnderThreatlocation().add(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn));
                knightWhite.setNumberOfUnderThreat(knightWhite.getNumberOfUnderThreat() + 1);
                knightWhite.setNumberOfSafe(knightWhite.getNumberOfSafe() - 1);
            }
        } else if (invariants.WHITE_BISHOP.equals(chessBoard)) {
            if (bishopWhite.getUnderThreatlocation() == null || !bishopWhite.getUnderThreatlocation().contains(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn))) {
                bishopWhite.getUnderThreatlocation().add(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn));
                bishopWhite.setNumberOfUnderThreat(bishopWhite.getNumberOfUnderThreat() + 1);
                bishopWhite.setNumberOfSafe(bishopWhite.getNumberOfSafe() - 1);
            }
        } else if (invariants.WHITE_QUEEN.equals(chessBoard)) {
            if (queenWhite.getUnderThreatlocation() == null || !queenWhite.getUnderThreatlocation().contains(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn))) {
                queenWhite.getUnderThreatlocation().add(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn));
                queenWhite.setNumberOfUnderThreat(queenWhite.getNumberOfUnderThreat() + 1);
                queenWhite.setNumberOfSafe(queenWhite.getNumberOfSafe() - 1);
            }
        } else if (invariants.WHITE_KING.equals(chessBoard)) {
            if (kingWhite.getUnderThreatlocation() == null || !kingWhite.getUnderThreatlocation().contains(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn))) {
                kingWhite.getUnderThreatlocation().add(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn));
                kingWhite.setNumberOfUnderThreat(kingWhite.getNumberOfUnderThreat() + 1);
                kingWhite.setNumberOfSafe(kingWhite.getNumberOfSafe() - 1);
            }
        } else if (invariants.WHITE_PAWN.equals(chessBoard)) {
            if (pawnWhite.getUnderThreatlocation() == null || !pawnWhite.getUnderThreatlocation().contains(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn))) {
                pawnWhite.getUnderThreatlocation().add(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn));
                pawnWhite.setNumberOfUnderThreat(pawnWhite.getNumberOfUnderThreat() + 1);
                pawnWhite.setNumberOfSafe(pawnWhite.getNumberOfSafe() - 1);
            }
        }
    }

    private void blackPiecesScenarios(String chessBoard, int underthreatLine, int underthreatColumn) {
        //kontrollerde buraya giriyorsa rakip taştan kendisine bir tehdit var demektir. Bunun için tehdit edilen taşın ne olduğunu bulup
        //tehdit sayısını 1 arttırıp güvende taş sayısını 1 azaltıyoruz.
        if (invariants.BLACK_ROOK.equals(chessBoard)) {
            if (rookBlack.getUnderThreatlocation() == null || !rookBlack.getUnderThreatlocation().contains(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn))) {
                rookBlack.getUnderThreatlocation().add(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn));
                rookBlack.setNumberOfUnderThreat(rookBlack.getNumberOfUnderThreat() + 1);
                rookBlack.setNumberOfSafe(rookBlack.getNumberOfSafe() - 1);
            }
        } else if (invariants.BLACK_KNIGHT.equals(chessBoard)) {
            if (knightBlack.getUnderThreatlocation() == null || !knightBlack.getUnderThreatlocation().contains(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn))) {
                knightBlack.getUnderThreatlocation().add(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn));
                knightBlack.setNumberOfUnderThreat(knightBlack.getNumberOfUnderThreat() + 1);
                knightBlack.setNumberOfSafe(knightBlack.getNumberOfSafe() - 1);
            }
        } else if (invariants.BLACK_BISHOP.equals(chessBoard)) {
            if (bishopBlack.getUnderThreatlocation() == null || !bishopBlack.getUnderThreatlocation().contains(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn))) {
                bishopBlack.getUnderThreatlocation().add(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn));
                bishopBlack.setNumberOfUnderThreat(bishopBlack.getNumberOfUnderThreat() + 1);
                bishopBlack.setNumberOfSafe(bishopBlack.getNumberOfSafe() - 1);
            }
        } else if (invariants.BLACK_QUEEN.equals(chessBoard)) {
            if (queenBlack.getUnderThreatlocation() == null || !queenBlack.getUnderThreatlocation().contains(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn))) {
                queenBlack.getUnderThreatlocation().add(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn));
                queenBlack.setNumberOfUnderThreat(queenBlack.getNumberOfUnderThreat() + 1);
                queenBlack.setNumberOfSafe(queenBlack.getNumberOfSafe() - 1);
            }
        } else if (invariants.BLACK_KING.equals(chessBoard)) {
            if (kingBlack.getUnderThreatlocation() == null || !kingBlack.getUnderThreatlocation().contains(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn))) {
                kingBlack.getUnderThreatlocation().add(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn));
                kingBlack.setNumberOfUnderThreat(kingBlack.getNumberOfUnderThreat() + 1);
                kingBlack.setNumberOfSafe(kingBlack.getNumberOfSafe() - 1);
            }
        } else if (invariants.BLACK_PAWN.equals(chessBoard)) {
            if (pawnBlack.getUnderThreatlocation() == null || !pawnBlack.getUnderThreatlocation().contains(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn))) {
                pawnBlack.getUnderThreatlocation().add(String.valueOf(underthreatLine) + String.valueOf(underthreatColumn));
                pawnBlack.setNumberOfUnderThreat(pawnBlack.getNumberOfUnderThreat() + 1);
                pawnBlack.setNumberOfSafe(pawnBlack.getNumberOfSafe() - 1);
            }
        }
    }

    private void rookCheck(String[][] chessBoard, int i, int j, String pieces) {
        //kale bütün tahta boyunca ileri,geri,sağ ve sol gidebilir bunun kontrolünü yapıyoruz. Burada eğer hareket güzergahında kendi renginde taş varsa
        // bir işlem yaptırmamız lazım. Eğer karşı renkte bir taş varsa o taşa tehdit olacaktır.
        String color = "";

        if (pieces.equals(invariants.BLACK_ROOK)) {
            color = invariants.BLACK;
        } else {
            color = invariants.WHITE;
        }
        //sağa doğru kontrolümüzü yapıyoruz
        for (int controlToTheRight = j + 1; controlToTheRight < invariants.BOARD_BORDER; controlToTheRight++) {
            if (chessBoard[i][controlToTheRight] != null && chessBoard[i][controlToTheRight].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[i][controlToTheRight] != null && chessBoard[i][controlToTheRight].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[i][controlToTheRight], i, controlToTheRight);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[i][controlToTheRight], i, controlToTheRight);
                    break;
                }
            }
        }
        //sola doğru kontrolümüzü yapıyoruz
        for (int controlToTheLeft = j - 1; controlToTheLeft >= 0; controlToTheLeft--) {
            if (chessBoard[i][controlToTheLeft] != null && chessBoard[i][controlToTheLeft].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[i][controlToTheLeft] != null && chessBoard[i][controlToTheLeft].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[i][controlToTheLeft], i, controlToTheLeft);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[i][controlToTheLeft], i, controlToTheLeft);
                    break;
                }
            }
        }
        //aşağı doğru kontrolümüzü yapıyoruz
        for (int controlToTheDown = i + 1; controlToTheDown < invariants.BOARD_BORDER; controlToTheDown++) {
            if (chessBoard[controlToTheDown][j] != null && chessBoard[controlToTheDown][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[controlToTheDown][j] != null && chessBoard[controlToTheDown][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[controlToTheDown][j], controlToTheDown, j);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[controlToTheDown][j], controlToTheDown, j);
                    break;
                }
            }
        }
        //yukarı doğru kontrolümüzü yapıyoruz
        for (int controlToTheUp = i - 1; controlToTheUp >= 0; controlToTheUp--) {
            if (chessBoard[controlToTheUp][j] != null && chessBoard[controlToTheUp][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[controlToTheUp][j] != null && chessBoard[controlToTheUp][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[controlToTheUp][j], controlToTheUp, j);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[controlToTheUp][j], controlToTheUp, j);
                    break;
                }
            }
        }

    }

    private void bishopCheck(String[][] chessBoard, int i, int j, String pieces) {
        //fil bütün tahta boyunca çapraz olarak hareket eder
        int line = i;
        int column = j;
        String color = "";

        if (pieces.equals(invariants.BLACK_BISHOP)) {
            color = invariants.BLACK;
        } else {
            color = invariants.WHITE;
        }

        for (int rightDownControl = 0; rightDownControl < invariants.MAX_BISHOP_QUEEN_MOVE; rightDownControl++) {
            line++;
            column++;

            if (line < invariants.BOARD_BORDER && column < invariants.BOARD_BORDER && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (line < invariants.BOARD_BORDER && column < invariants.BOARD_BORDER && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column], line, column);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[line][column], line, column);
                    break;
                }
            }
        }
        line = i;
        column = j;

        for (int leftUpControl = 0; leftUpControl < invariants.MAX_BISHOP_QUEEN_MOVE; leftUpControl++) {
            line--;
            column--;
            if (line >= 0 && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (line >= 0 && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column], line, column);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[line][column], line, column);
                    break;
                }
            }
        }
        line = i;
        column = j;
        for (int leftDownControl = 0; leftDownControl < invariants.MAX_BISHOP_QUEEN_MOVE; leftDownControl++) {
            line++;
            column--;
            if (line < invariants.BOARD_BORDER && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (line < invariants.BOARD_BORDER && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column], line, column);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[line][column], line, column);
                    break;
                }
            }
        }
        line = i;
        column = j;
        for (int rightUpControl = 0; rightUpControl < invariants.MAX_BISHOP_QUEEN_MOVE; rightUpControl++) {
            line--;
            column++;
            if (column < invariants.BOARD_BORDER && line >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (column < invariants.BOARD_BORDER && line >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column], line, column);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[line][column], line, column);
                    break;
                }
            }
        }
    }

    private void queenCheck(String[][] chessBoard, int i, int j, String pieces) {
        //vezir hem fil hemde atın özelliklerine sahiptir
        int line = i;
        int column = j;
        String color = "";

        if (pieces.equals(invariants.BLACK_QUEEN)) {
            color = invariants.BLACK;
        } else {
            color = invariants.WHITE;
        }

        for (int rightDownControl = 0; rightDownControl < invariants.MAX_BISHOP_QUEEN_MOVE; rightDownControl++) {
            line++;
            column++;

            if (line < invariants.BOARD_BORDER && column < invariants.BOARD_BORDER && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (line < invariants.BOARD_BORDER && column < invariants.BOARD_BORDER && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column], line, column);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[line][column], line, column);
                    break;
                }
            }
        }
        line = i;
        column = j;

        for (int leftUpControl = 0; leftUpControl < invariants.MAX_BISHOP_QUEEN_MOVE; leftUpControl++) {
            line--;
            column--;
            if (line >= 0 && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (line >= 0 && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column], line, column);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[line][column], line, column);
                    break;
                }
            }
        }
        line = i;
        column = j;
        for (int leftDownControl = 0; leftDownControl < invariants.MAX_BISHOP_QUEEN_MOVE; leftDownControl++) {
            line++;
            column--;
            if (line < invariants.BOARD_BORDER && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (line < invariants.BOARD_BORDER && column >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column], line, column);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[line][column], line, column);
                    break;
                }
            }
        }
        line = i;
        column = j;
        for (int rightUpControl = 0; rightUpControl < invariants.MAX_BISHOP_QUEEN_MOVE; rightUpControl++) {
            line--;
            column++;
            if (column < invariants.BOARD_BORDER && line >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (column < invariants.BOARD_BORDER && line >= 0 && chessBoard[line][column] != null && chessBoard[line][column].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[line][column], line, column);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[line][column], line, column);
                    break;
                }
            }
        }
        for (int controlToTheRight = j + 1; controlToTheRight < invariants.BOARD_BORDER; controlToTheRight++) {
            if (chessBoard[i][controlToTheRight] != null && chessBoard[i][controlToTheRight].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[i][controlToTheRight] != null && chessBoard[i][controlToTheRight].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[i][controlToTheRight], i, controlToTheRight);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[i][controlToTheRight],i, controlToTheRight);
                    break;
                }
            }
        }

        for (int controlToTheLeft = j - 1; controlToTheLeft >= 0; controlToTheLeft--) {
            if (chessBoard[i][controlToTheLeft] != null && chessBoard[i][controlToTheLeft].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[i][controlToTheLeft] != null && chessBoard[i][controlToTheLeft].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[i][controlToTheLeft], i, controlToTheLeft);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[i][controlToTheLeft], i, controlToTheLeft);
                    break;
                }
            }
        }
        for (int controlToTheDown = i + 1; controlToTheDown < invariants.BOARD_BORDER; controlToTheDown++) {
            if (chessBoard[controlToTheDown][j] != null && chessBoard[controlToTheDown][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[controlToTheDown][j] != null && chessBoard[controlToTheDown][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[controlToTheDown][j], controlToTheDown, j);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[controlToTheDown][j], controlToTheDown, j);
                    break;
                }
            }
        }

        for (int controlToTheUp = i - 1; controlToTheUp >= 0; controlToTheUp--) {
            if (chessBoard[controlToTheUp][j] != null && chessBoard[controlToTheUp][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.BLACK : invariants.WHITE)) {
                break;
            } else if (chessBoard[controlToTheUp][j] != null && chessBoard[controlToTheUp][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
                if (color.equals(invariants.BLACK)) {
                    whitePiecesScenarios(chessBoard[controlToTheUp][j], controlToTheUp, j);
                    break;
                } else {
                    blackPiecesScenarios(chessBoard[controlToTheUp][j], controlToTheUp, j);
                    break;
                }
            }
        }
    }

    private void knightCheck(String[][] chessBoard, int i, int j, String pieces) {
        //at tahtada L şeklinde hareket eder. Hareket alanı üzerinde taş olup olmamasını önemsemez
        String color = "";

        if (pieces.equals(invariants.BLACK_KNIGHT)) {
            color = invariants.BLACK;
        } else {
            color = invariants.WHITE;
        }

        // 2 üst 1 sağ hareketi
        if (i >= 2 && j + 1 < 8 && chessBoard[i - 2][j + 1] != null && chessBoard[i - 2][j + 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i - 2][j + 1], i - 2, j + 1);
            } else {
                blackPiecesScenarios(chessBoard[i - 2][j + 1], i - 2, j + 1);
            }
        }

        // 2 üst 1 sol hareketi
        if (i >= 2 && j >= 1 && chessBoard[i - 2][j - 1] != null && chessBoard[i - 2][j - 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i - 2][j - 1], i - 2, j - 1);
            } else {
                blackPiecesScenarios(chessBoard[i - 2][j - 1], i - 2, j - 1);
            }
        }
        // 1 üst 2 sağ hareketi
        if (i >= 1 && j + 2 < invariants.BOARD_BORDER && chessBoard[i - 1][j + 2] != null && chessBoard[i - 1][j + 2].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i - 1][j + 2], i - 1, j + 2);
            } else {
                blackPiecesScenarios(chessBoard[i - 1][j + 2], i - 1, j + 2);
            }
        }

        // 1 üst 2 sol hareketi
        if (i >= 1 && j >= 2 && chessBoard[i - 1][j - 2] != null && chessBoard[i - 1][j - 2].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i - 1][j - 2], i - 1, j - 2);
            } else {
                blackPiecesScenarios(chessBoard[i - 1][j - 2], i - 1, j - 2);
            }
        }

        // 2 aşağı 1 sağ hareketi
        if (i + 2 < invariants.BOARD_BORDER && chessBoard[i + 2][j + 1] != null && chessBoard[i + 2][j + 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i + 2][j + 1], i + 2, j + 1);
            } else {
                blackPiecesScenarios(chessBoard[i + 2][j + 1], i + 2, j + 1);
            }
        }

        // 2 aşağı 1 sol hareketi
        if (i + 2 < invariants.BOARD_BORDER && j >= 1 && chessBoard[i + 2][j - 1] != null && chessBoard[i + 2][j - 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i + 2][j - 1], i + 2, j - 1);
            } else {
                blackPiecesScenarios(chessBoard[i + 2][j - 1], i + 2, j - 1);
            }
        }
        // 1 aşağı 2 sağ hareketi
        if (i + 1 < invariants.BOARD_BORDER && j + 2 < invariants.BOARD_BORDER && chessBoard[i + 1][j + 2] != null && chessBoard[i + 1][j + 2].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i + 1][j + 2], i + 1, j + 2);
            } else {
                blackPiecesScenarios(chessBoard[i + 1][j + 2], i + 1, j + 2);
            }
        }

        // 1 aşağı 2 sol hareketi
        if (i + 1 < invariants.BOARD_BORDER && j >= 2 && chessBoard[i + 1][j - 2] != null && chessBoard[i + 1][j - 2].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i + 1][j - 2], i + 1, j - 2);
            } else {
                blackPiecesScenarios(chessBoard[i + 1][j - 2], i + 1, j - 2);
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
                whitePiecesScenarios(chessBoard[i - 1][j], i - 1, j);
            } else {
                blackPiecesScenarios(chessBoard[i - 1][j], i - 1, j);
            }
        }
        //aşağı 1 kare
        if (i + 1 < invariants.BOARD_BORDER && chessBoard[i + 1][j] != null && chessBoard[i + 1][j].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i + 1][j], i + 1, j);
            } else {
                blackPiecesScenarios(chessBoard[i + 1][j], i + 1, j);
            }
        }
        //sağa 1 kare
        if (j + 1 < invariants.BOARD_BORDER && chessBoard[i][j + 1] != null && chessBoard[i][j + 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i][j + 1], i, j + 1);
            } else {
                blackPiecesScenarios(chessBoard[i][j + 1], i, j + 1);
            }
        }
        //sola 1 kare
        if (j - 1 >= 0 && chessBoard[i][j - 1] != null && chessBoard[i][j - 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i][j - 1], i, j - 1);
            } else {
                blackPiecesScenarios(chessBoard[i][j - 1], i, j - 1);
            }
        }
        //sağ üst 1 kare
        if (j + 1 < invariants.BOARD_BORDER && i - 1 >= 0 && chessBoard[i - 1][j + 1] != null && chessBoard[i - 1][j + 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i - 1][j + 1], i-1, j+1);
            } else {
                blackPiecesScenarios(chessBoard[i - 1][j + 1], i-1, j+1);
            }
        }
        //sol üst 1 kare
        if (i - 1 >= 0 && j - 1 >= 0 && chessBoard[i - 1][j - 1] != null && chessBoard[i - 1][j - 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i - 1][j - 1], i-1, j-1);
            } else {
                blackPiecesScenarios(chessBoard[i - 1][j - 1], i-1, j-1);
            }
        }
        //sağ alt 1 kare
        if (i + 1 < invariants.BOARD_BORDER && j + 1 < invariants.BOARD_BORDER && chessBoard[i + 1][j + 1] != null && chessBoard[i + 1][j + 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i + 1][j + 1], i+1, j+1);
            } else {
                blackPiecesScenarios(chessBoard[i + 1][j + 1], i+1, j+1);
            }
        }
        //sol alt 1 kare
        if (i + 1 < invariants.BOARD_BORDER && j - 1 >= 0 && chessBoard[i + 1][j - 1] != null && chessBoard[i + 1][j - 1].substring(1).equals(color.equals(invariants.BLACK) ? invariants.WHITE : invariants.BLACK)) {
            if (color.equals(invariants.BLACK)) {
                whitePiecesScenarios(chessBoard[i + 1][j - 1], i + 1, j - 1);
            } else {
                blackPiecesScenarios(chessBoard[i + 1][j - 1], i + 1, j - 1);
            }
        }
    }

    private void pawnCheck(String[][] chessBoard, int i, int j, String pieces) {
        if (pieces.equals(invariants.BLACK_PAWN)) {
            //eğer piyon siyah renkliyse yukarıdan aşağı hareket edeceği için sadece sağ alt ve sol alttaki taşlara tehdit olacaktır

            //sağ alt 1 kare
            if (i + 1 < invariants.BOARD_BORDER && j + 1 < invariants.BOARD_BORDER && chessBoard[i + 1][j + 1] != null && chessBoard[i + 1][j + 1].substring(1).equals(invariants.WHITE)) {
                whitePiecesScenarios(chessBoard[i + 1][j + 1], i + 1, j + 1);
            }
            //sol alt 1 kare
            if (i + 1 < invariants.BOARD_BORDER && j - 1 >= 0 && chessBoard[i + 1][j - 1] != null && chessBoard[i + 1][j - 1].substring(1).equals(invariants.WHITE)) {
                whitePiecesScenarios(chessBoard[i + 1][j - 1], i + 1, j - 1);
            }
        } else {
            //eğer piyon beyaz renkliyse aşağıdan yukarı hareket edeceği için sadece sağ üst ve sol üstteki taşlara tehdit olacaktır

            //sağ üst 1 kare
            if (j + 1 < invariants.BOARD_BORDER && i - 1 >= 0 && chessBoard[i - 1][j + 1] != null && chessBoard[i - 1][j + 1].substring(1).equals(invariants.BLACK)) {
                blackPiecesScenarios(chessBoard[i - 1][j + 1],i-1,j+1);
            }
            //sol üst 1 kare
            if (i - 1 >= 0 && j - 1 >= 0 && chessBoard[i - 1][j - 1] != null && chessBoard[i - 1][j - 1].substring(1).equals(invariants.BLACK)) {
                blackPiecesScenarios(chessBoard[i - 1][j - 1],i-1,j-1);
            }
        }
    }

}
