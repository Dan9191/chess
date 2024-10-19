package org.example.model;

import org.example.ChessBoard;

import static org.example.model.Color.EMPTY;

/**
 * Пустая шахматная ячейка.
 */
public class EmptyCell extends ChessPiece {

    private static EmptyCell INSTANCE;

    private EmptyCell(Color color) {
        super(color, PieceType.EMPTY_CELL);
    }

    public static EmptyCell getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new EmptyCell(EMPTY);
        }

        return INSTANCE;
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
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return false;
    }
}
