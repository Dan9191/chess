package org.example.model;

import org.example.ChessBoard;

import static org.example.model.Color.EMPTY;

/**
 * Недоступная шахматная ячейка(за пределами доски).
 */
public class UnavailableCell extends ChessPiece {

    private static UnavailableCell INSTANCE;

    private UnavailableCell(Color color) {
        super(color, PieceType.UNAVAILABLE_CELL);
    }

    public static UnavailableCell getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UnavailableCell(EMPTY);
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

    @Override
    public String getSymbol() {
        return "N";
    }
}
