package org.example.model;

import org.example.ChessBoard;

public class EmptyCell extends ChessPiece {

    public EmptyCell(Color color) {
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
     * @return можно ли сделать шаг на указанную точку
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
