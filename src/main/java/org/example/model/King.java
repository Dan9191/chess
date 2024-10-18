package org.example.model;

import org.example.ChessBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.util.Constants.IS_BLACK_PAWN;
import static org.example.util.Constants.IS_PAWN_OR_KING;
import static org.example.util.Constants.IS_WHITE_PAWN;

public class
King extends ChessPiece {

    public King(Color color) {
        super(color, PieceType.KING);
    }

    private final List<PieceType> VERTICAL_AND_HORIZONTAL_PIECES =
            List.of(PieceType.ROOK, PieceType.QUEEN, PieceType.KING);
    private final List<PieceType> DIAGONALLY_PIECES =
            List.of(PieceType.BISHOP, PieceType.QUEEN, PieceType.KING, PieceType.PAWN);

    /**
     * Можно ли сходить.
     *
     * @param chessBoard шахматная доска.
     * @param line       начальная координата линия.
     * @param column     начальная координата столбец.
     * @param toLine     координата для перемещения по линии.
     * @param toColumn   координата для перемещения по столбцу.
     * @return Можно ли сходить.
     */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        List<Integer> range = List.of(0, 1);
        if (!super.checkAvailable(toLine, toColumn)) return false;
        if (!range.contains(Math.abs(line - toLine))
                || !range.contains(Math.abs(column - toColumn))) {
            System.out.println("Король так не ходит");
            return false;
        }
        ChessPiece other = chessBoard.getChessByCoordinates(toLine, toColumn);
        boolean otherEmpty = super.emptyCheck(other);
        boolean otherEnemy = super.enemyCheck(other);
        List<ChessPiece> nextStepUnderAttackList = isUnderAttackList(chessBoard, toLine, toColumn);
        if (otherEmpty || otherEnemy) {
            if (!nextStepUnderAttackList.isEmpty()) {
                System.out.println("Король находится под атакой:");
                System.out.println(nextStepUnderAttackList.stream().map(ChessPiece::toString).collect(Collectors.joining(", ")));
                return false;
            } else {
                if (otherEnemy) chessBoard.addDestroyedPiece(other);
                super.doFirstMove();
                return true;
            }
        } else {
            System.out.println("Нельзя ходить на локацию, на которой стоит своя фигура");
            return false;
        }
    }

    /**
     * Список фигур, которые могут атаковать короля.
     *
     * @param board  Шахматная доска.
     * @param line   Координата линии.
     * @param column Координата столбца.
     * @return список атакующих фигур.
     */
    public List<ChessPiece> isUnderAttackList(ChessBoard board, int line, int column) {
        List<ChessPiece> enemy = new ArrayList<>();
        enemy.addAll(getEnemyHorse(board, line, column));
        enemy.addAll(getByVerticalAndHorizontal(board, line, column));
        enemy.addAll(getByDiagonally(board, line, column));
        return enemy;
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        List<ChessPiece> enemy = new ArrayList<>();
        enemy.addAll(getEnemyHorse(board, line, column));
        enemy.addAll(getByVerticalAndHorizontal(board, line, column));
        enemy.addAll(getByDiagonally(board, line, column));
        return enemy.isEmpty();
    }


    private List<ChessPiece> getEnemyHorse(ChessBoard board, int line, int column) {
        Color enemyColor = this.color.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
        List<ChessPiece> horseStep = new ArrayList<>(8);
        horseStep.add(board.getChessByCoordinates(line + 1, column + 2));
        horseStep.add(board.getChessByCoordinates(line + 2, column + 1));
        horseStep.add(board.getChessByCoordinates(line + 1, column - 2));
        horseStep.add(board.getChessByCoordinates(line + 2, column - 1));
        horseStep.add(board.getChessByCoordinates(line - 1, column + 2));
        horseStep.add(board.getChessByCoordinates(line - 2, column + 1));
        horseStep.add(board.getChessByCoordinates(line - 1, column - 2));
        horseStep.add(board.getChessByCoordinates(line - 2, column - 1));
        return horseStep.stream()
                .filter(piece -> piece.pieceType.equals(PieceType.HORSE))
                .filter(piece -> piece.getColor().equals(enemyColor))
                .collect(Collectors.toList());
    }

    private List<ChessPiece> getByVerticalAndHorizontal(ChessBoard board, int line, int column) {
        Color enemyColor = this.color.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
        List<ChessPiece> pieces = new ArrayList<>(4);
        for (int i = line + 1; i < 7; i++) {
            ChessPiece upPiece = board.getChessByCoordinates(i, column);
            if (!upPiece.getPieceType().equals(PieceType.EMPTY_CELL)) {
                if (upPiece.getPieceType().equals(PieceType.KING)) {
                    if (i == line + 1) {
                        pieces.add(upPiece);
                        break;
                    } else {
                        break;
                    }
                } else {
                    pieces.add(upPiece);
                    break;
                }
            }
        }
        for (int i = line - 1; i > 0; i--) {
            ChessPiece downPiece = board.getChessByCoordinates(i, column);
            if (!downPiece.getPieceType().equals(PieceType.EMPTY_CELL)) {
                if (downPiece.getPieceType().equals(PieceType.KING)) {
                    if (i == line - 1) {
                        pieces.add(downPiece);
                        break;
                    } else {
                        break;
                    }
                } else {
                    pieces.add(downPiece);
                    break;
                }
            }
        }
        for (int i = column + 1; i < 7; i++) {
            ChessPiece rightPiece = board.getChessByCoordinates(line, i);
            if (!rightPiece.getPieceType().equals(PieceType.EMPTY_CELL)) {
                if (rightPiece.getPieceType().equals(PieceType.KING)) {
                    if (i == column + 1) {
                        pieces.add(rightPiece);
                        break;
                    } else {
                        break;
                    }
                } else {
                    pieces.add(rightPiece);
                    break;
                }
            }
        }
        for (int i = column - 1; i > 0; i--) {
            ChessPiece upPiece = board.getChessByCoordinates(line, i);
            if (!upPiece.getPieceType().equals(PieceType.EMPTY_CELL)) {
                if (upPiece.getPieceType().equals(PieceType.KING)) {
                    if (i == column - 1) {
                        pieces.add(upPiece);
                        break;
                    } else {
                        break;
                    }
                } else {
                    pieces.add(upPiece);
                    break;
                }
            }
        }
        return pieces.stream()
                .filter(piece -> VERTICAL_AND_HORIZONTAL_PIECES.contains(piece.getPieceType()))
                .filter(piece -> piece.getColor().equals(enemyColor))
                .collect(Collectors.toList());
    }

    private List<ChessPiece> getByDiagonally(ChessBoard board, int line, int column) {
        Color enemyColor = this.color.equals(Color.WHITE) ? Color.BLACK : Color.WHITE;
        List<ChessPiece> pieces = new ArrayList<>(4);
        boolean topRight = true;
        boolean downRight = true;
        boolean downLeft = true;
        boolean topLeft = true;
        int currentLine = line;
        int currentColumn = column;

        while (topRight) {
            currentLine++;
            currentColumn++;
            ChessPiece piece = board.getChessByCoordinates(currentLine, currentColumn);
            if (!piece.getPieceType().equals(PieceType.EMPTY_CELL)) {
                if (IS_PAWN_OR_KING.test(piece)) {
                    if (currentLine == line + 1 && currentColumn == column + 1) {
                        if ((this.color.equals(Color.WHITE) && IS_BLACK_PAWN.test(piece)) || piece.getPieceType().equals(PieceType.KING)) {
                            pieces.add(piece);
                        }
                    }
                } else {
                    pieces.add(piece);
                }
                topRight = false;
            }
        }

        currentLine = line;
        currentColumn = column;

        while (downRight) {
            currentLine--;
            currentColumn++;
            ChessPiece piece = board.getChessByCoordinates(currentLine, currentColumn);
            if (!piece.getPieceType().equals(PieceType.EMPTY_CELL)) {
                if (IS_PAWN_OR_KING.test(piece)) {
                    if (currentLine == line -1 && currentColumn == column + 1) {
                        if ((this.color.equals(Color.BLACK) && IS_WHITE_PAWN.test(piece))
                                || piece.getPieceType().equals(PieceType.KING)) {
                            pieces.add(piece);
                        }
                    }
                } else {
                    pieces.add(piece);
                }
                downRight = false;
            }
        }

        currentLine = line;
        currentColumn = column;
        while (downLeft) {
            currentLine--;
            currentColumn--;
            ChessPiece piece = board.getChessByCoordinates(currentLine, currentColumn);
            if (!piece.getPieceType().equals(PieceType.EMPTY_CELL)) {
                if (IS_PAWN_OR_KING.test(piece)) {
                    if (currentLine == line -1 && currentColumn == column - 1) {
                        if ((this.color.equals(Color.BLACK) && IS_WHITE_PAWN.test(piece))
                                || piece.getPieceType().equals(PieceType.KING)) {
                            pieces.add(piece);
                        }
                    }
                } else {
                    pieces.add(piece);
                }
                downLeft = false;
            }
        }

        currentLine = line;
        currentColumn = column;
        while (topLeft) {
            currentLine++;
            currentColumn--;
            ChessPiece piece = board.getChessByCoordinates(currentLine, currentColumn);
            if (!piece.getPieceType().equals(PieceType.EMPTY_CELL)) {
                if (IS_PAWN_OR_KING.test(piece)) {
                    if (currentLine == line + 1 && currentColumn == column - 1) {
                        if ((this.color.equals(Color.WHITE) && IS_BLACK_PAWN.test(piece))
                                || piece.getPieceType().equals(PieceType.KING)) {
                            pieces.add(piece);
                        }
                    }
                } else {
                    pieces.add(piece);
                }
                topLeft = false;
            }
        }
        return pieces.stream()
                .filter(piece -> DIAGONALLY_PIECES.contains(piece.getPieceType()))
                .filter(piece -> piece.getColor().equals(enemyColor))
                .collect(Collectors.toList());
    }

}
