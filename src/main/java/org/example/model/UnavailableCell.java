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
