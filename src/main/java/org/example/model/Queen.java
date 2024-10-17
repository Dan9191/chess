package org.example.model;

import org.example.ChessBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Ферзь, королева.
 */
public class Queen extends ChessPiece {

    public Queen(Color color) {
        super(color, PieceType.QUEEN);
    }

    /**
     * Можно ли сходить.
     *
     * @param chessBoard шахматная доска.
     * @param line     начальная координата линия.
     * @param column   начальная коодрината столбец.
     * @param toLine   координата для перемещения по линии.
     * @param toColumn координата для перемещения по столбцу.
     * @return
     */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!super.checkAvailable(toLine, toColumn)) return false;
        if ((Math.abs(line - toLine) != Math.abs(column-toColumn))
        && (line != toLine && column != toColumn)) {
            System.out.println("Королева так не ходит");
            return false;
        }
        ChessPiece other = chessBoard.getChessByCoordinates(toLine, toColumn);
        boolean otherEmpty = super.emptyCheck(other);
        boolean otherEnemy = super.enemyCheck(other);
        List<ChessPiece> others = new ArrayList<>();

        if (Math.abs(line - toLine) == Math.abs(column-toColumn)) {
            int lineStep = (line - toLine) < 0 ? 1 : -1;
            int columnStep = (column - toColumn) < 0 ? 1 : -1;
            int columnIndex = column + columnStep;
            for (int i = line + lineStep; i < toLine; i= i + lineStep) {
                others.add(chessBoard.getChessByCoordinates(i, columnIndex));
                columnIndex = columnIndex + columnStep;
            }
        } else {
            if (line == toLine) {
                for (int i = column + 1 ; i < toColumn; i ++) {
                    others.add(chessBoard.getChessByCoordinates(line, i));
                }
            } else {
                for (int i = line + 1 ; i < toLine; i ++) {
                    others.add(chessBoard.getChessByCoordinates(i, column));
                }
            }
        }

        boolean wayIsClean = super.emptyCheckList(others);
        if (!wayIsClean) {
            System.out.println("На пути у королевы есть препятствие");
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
