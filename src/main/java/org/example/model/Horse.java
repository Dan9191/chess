package org.example.model;

import org.example.ChessBoard;

public class Horse extends ChessPiece {

    public Horse(Color color) {
        super(color, PieceType.HORSE);
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
        if (!super.checkAvailable(toLine, toColumn)) return false;
        ChessPiece other = chessBoard.getChessByCoordinates(toLine, toColumn);
        boolean otherEmpty = super.emptyCheck(other);
        boolean otherEnemy = super.enemyCheck(other);
        if (Math.abs(line - toLine) + Math.abs(column - toColumn) == 3
                && (otherEmpty || otherEnemy)) {
            if (otherEnemy) chessBoard.addDestroyedPiece(other);
            super.doFirstMove();
            return true;
        }
        System.out.println("Конь так не ходит");
        return false;
    }

}
