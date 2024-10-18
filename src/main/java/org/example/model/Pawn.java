package org.example.model;

import org.example.ChessBoard;

public class Pawn extends ChessPiece {

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
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
        if (!super.checkAvailable(toLine, toColumn)) return false;
        ChessPiece other = chessBoard.getChessByCoordinates(toLine, toColumn);
        boolean otherEmpty = super.emptyCheck(other);
        boolean otherEnemy = super.enemyCheck(other);
        if (this.color.equals(Color.WHITE)) {
            if (line + 1 == toLine && column == toColumn && otherEmpty) {
                super.doFirstMove();
                return true;
            } else if (line + 1 == toLine && (column == toColumn + 1 || column == toColumn - 1) && otherEnemy) {
                chessBoard.addDestroyedPiece(other);
                super.doFirstMove();
                return true;
            } else if (line + 2 == toLine && column == toColumn && super.isHasFirstMove() && otherEmpty
                    && super.emptyCheck(chessBoard.getChessByCoordinates(line + 1, column))) {
                super.doFirstMove();
                return true;
            } else {
                return false;
            }
        } else {
            if (line - 1 == toLine && column == toColumn && otherEmpty) {
                super.doFirstMove();
                return true;
            } else if (line - 1 == toLine && (column == toColumn + 1 || column == toColumn - 1) && otherEnemy) {
                chessBoard.addDestroyedPiece(other);
                super.doFirstMove();
                return true;
            } else if (line - 2 == toLine && column == toColumn && super.isHasFirstMove() && otherEmpty
                    && super.emptyCheck(chessBoard.getChessByCoordinates(line - 1, column))) {
                super.doFirstMove();
                return true;
            } else {
                return false;
            }
        }
    }
}
