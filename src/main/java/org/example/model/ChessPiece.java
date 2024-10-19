package org.example.model;

import org.example.ChessBoard;

import java.util.List;

/**
 * Абстрактный класс для шахматных фигур.
 */
public abstract class ChessPiece {

    Color color;

    PieceType pieceType;
    private boolean hasFirstMove = true;

    public ChessPiece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public Color getColor() {
        return this.color;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    /**
     * Можно ли сходить.
     *
     * @param chessBoard Шахматная доска.
     * @param line       Начальная координата линия.
     * @param column     Начальная координата столбец.
     * @param toLine     Координата для перемещения по линии.
     * @param toColumn   Координата для перемещения по столбцу.
     * @return разрешено ли перемещение.
     */
    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public void doFirstMove() {
        if (this.hasFirstMove) {
            this.hasFirstMove = false;
        }
    }

    public boolean isHasFirstMove() {
        return hasFirstMove;
    }

    public String toString() {
        return pieceType + " " + color + " " +hasFirstMove;
    }

    public String toBoardPrint() {
        return pieceType.getSymbol() + color.getName();
    }

    public boolean checkAvailable(int line, int column) {
        return line >= 0 && line <= 7 && column >= 0 && column <= 7;
    }

    public boolean enemyCheck(ChessPiece other) {
        return (this.getColor().equals(Color.WHITE) && other.getColor().equals(Color.BLACK))
                || (this.getColor().equals(Color.BLACK) && other.getColor().equals(Color.WHITE));
    }

    public boolean emptyCheck(ChessPiece other) {
        return other.pieceType.equals(PieceType.EMPTY_CELL);
    }

    public boolean emptyCheckList(List<ChessPiece> others) {
        return others.stream().allMatch(it -> it.pieceType.equals(PieceType.EMPTY_CELL));
    }

}
