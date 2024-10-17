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
     * @param chessBoard
     * @param line     начальная координата линия.
     * @param column   начальная коодрината столбец.
     * @param toLine   координата для перемещения по линии.
     * @param toColumn координата для перемещения по столбцу.
     * @return можно ли сделать шаг на указанную точку
     */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return false;
    }
}
