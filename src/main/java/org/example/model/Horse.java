package org.example.model;

import org.example.ChessBoard;

/**
 * Конь.
 */
public class Horse extends ChessPiece {

    public Horse(Color color) {
        super(color, PieceType.HORSE);
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
