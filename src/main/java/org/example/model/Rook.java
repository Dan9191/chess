package org.example.model;

import org.example.ChessBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Ладья, тура.
 */
public class Rook extends ChessPiece {

    public Rook(Color color) {
        super(color, PieceType.ROOK);
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
        if (line != toLine && column != toColumn) return false;
        ChessPiece other = chessBoard.getChessByCoordinates(toLine, toColumn);
        boolean otherEmpty = super.emptyCheck(other);
        boolean otherEnemy = super.enemyCheck(other);
        List<ChessPiece> others = new ArrayList<>();
        if (line == toLine) {
            for (int i = column + 1 ; i < toColumn; i ++) {
                others.add(chessBoard.getChessByCoordinates(line, i));
            }
        } else {
            for (int i = line + 1 ; i < toLine; i ++) {
                others.add(chessBoard.getChessByCoordinates(i, column));
            }
        }
        boolean wayIsClean = super.emptyCheckList(others);
        if (!wayIsClean) {
            System.out.println("На пути у Ладьи есть препятствие");
            return false;
        } else if (otherEmpty || otherEnemy) {
            if (otherEnemy) chessBoard.addDestroyedPiece(other);
            super.doFirstMove();
            return true;
        } else {
            System.out.println("Нельзя ходить на локацию, на которой стоит своя фигура фигурой");
            return false;
        }
    }

}
