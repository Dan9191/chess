package org.example.model;

import org.example.ChessBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Слон.
 */
public class Bishop extends ChessPiece {

    public Bishop(Color color) {
        super(color, PieceType.BISHOP);
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
        if (Math.abs(line - toLine) != Math.abs(column-toColumn)) {
            System.out.println("Слон так не ходит");
            return false;
        }
        ChessPiece other = chessBoard.getChessByCoordinates(toLine, toColumn);
        boolean otherEmpty = super.emptyCheck(other);
        boolean otherEnemy = super.enemyCheck(other);
        List<ChessPiece> others = new ArrayList<>();
        int lineStep = (line - toLine) < 0 ? 1 : -1;
        int columnStep = (column - toColumn) < 0 ? 1 : -1;
        int columnIndex = column + columnStep;
        for (int i = line + lineStep; i < toLine; i= i + lineStep) {
            others.add(chessBoard.getChessByCoordinates(i, columnIndex));
            columnIndex = columnIndex + columnStep;
        }
        boolean wayIsClean = super.emptyCheckList(others);
        if (!wayIsClean) {
            System.out.println("На пути у слона есть препятствие");
            return false;
        } else if (otherEmpty || otherEnemy) {
            if (otherEnemy) chessBoard.addDestroyedPiece(other);
            super.doFirstMove();
            return true;
        } else {
            System.out.println("Нельзя ходить на локацию, на которой стоит своя фигура");
            return false;
        }
    }
}
