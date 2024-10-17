package org.example.model;

import org.example.ChessBoard;

public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    /**
     * Можно ли сходить.
     *
     * @param chessBoard
     * @param line     начальная координата линия.
     * @param column   начальная коодрината столбец.
     * @param toLine   координата для перемещения по линии.
     * @param toColumn координата для перемещения по столбцу.
     * @return
     */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
